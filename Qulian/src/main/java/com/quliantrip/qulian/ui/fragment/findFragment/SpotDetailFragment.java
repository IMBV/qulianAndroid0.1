package com.quliantrip.qulian.ui.fragment.findFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.finderAdapter.SpotDetailVoiceAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.find.SpotDetailBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.service.Iservice;
import com.quliantrip.qulian.ui.activity.choiceActivity.GoodDetailActivity;
import com.quliantrip.qulian.ui.activity.findActivity.SpotDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.CircleImageView;
import com.quliantrip.qulian.view.HorizontalScroll.HorizontalScrollViewAdapter;
import com.quliantrip.qulian.view.HorizontalScroll.MyHorizontalScrollView;
import com.quliantrip.qulian.view.MyListView;
import com.quliantrip.qulian.view.RollViewPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 景点详情页,内部有点击语音的播放
 */
public class SpotDetailFragment extends BasePageCheckFragment {
    private View view;
    private AlertDialog dialog;
    private SpotDetailVoiceAdapter spotDetailVoiceAdapter;
    private List<SpotDetailBean.DataEntity.VoicInfoEntity> voiceList;
    private Boolean isPlayMusic;

    //轮播图与下面的小点
    private RollViewPage rollViewPage;
    @Bind(R.id.top_news_viewpager)
    LinearLayout top_news_viewpager;//轮播的viewpage
    //下面的小点
    @Bind(R.id.dots_ll)
    LinearLayout dots_ll;
    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    //附近实惠
    @Bind(R.id.id_horizontalScrollView)
    MyHorizontalScrollView mHorizontalScrollView;
    private HorizontalScrollViewAdapter mAdapter;

    //音频播放
    @Bind(R.id.civ_spot_voice_item_img)
    CircleImageView voiceImg;
    @Bind(R.id.tv_spot_voice_item_name)
    TextView voiceName;

    //景点语音的列表
    @Bind(R.id.spot_introduct_voice)
    MyListView listView;
    @Bind(R.id.iv_spot_play_detail_munic)
    ImageView municImg;

    //音乐有关的数据
    private Iservice iservice;//就是我们的中间人对象的实现
    private static SeekBar seekBar;

    //定义一个handler
    public static Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //(1)获取我们封装的数据
            Bundle data = msg.getData();
            int duration = data.getInt("duration");
            int currentPosition = data.getInt("currentPosition");

            //(2)设置seekbar的最大进度
            seekBar.setMax(duration);
            seekBar.setProgress(currentPosition); //设置当前进度
        }
    };

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_find_spot_method_detail, null);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar_progress);
        ButterKnife.bind(this, view);
        listView.setFocusable(false);
        //添加音频播放时的滑动监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当拖动停止的时候调用
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //调用播放的指定位置的方法
                if (iservice == null)
                    iservice = ((SpotDetailActivity) mContext).getIservice();
                iservice.callSetSeekPosition(seekBar.getProgress());
            }

            //刚开始拖动调用
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //当进度发生改变的时候调用
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }
        });
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        return new QuestBean(map, new SpotDetailBean().setTag(getClass().getName()), HttpConstants.VOICE_SQUARE_DETAIL);
    }

    @Override
    public void onEventMainThread(final BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            SpotDetailBean spotDetailBean = (SpotDetailBean) bean;
            if (spotDetailBean.getCode() == 200) {
                final SpotDetailBean.DataEntity dataEntity = spotDetailBean.getData();
                ((SpotDetailActivity) mContext).showOrHideBack(false);
                //轮播图
                initRollView(dataEntity.getAttraction().getImgs());

                //添加附近优惠的条目
                ArrayList<SpotDetailBean.DataEntity.NearPorEntity> listdaasdfasdf = (ArrayList<SpotDetailBean.DataEntity.NearPorEntity>) dataEntity.getNearPor();
                SpotDetailBean.DataEntity.NearPorEntity nearPorEntity = new SpotDetailBean.DataEntity.NearPorEntity();
                mAdapter = new HorizontalScrollViewAdapter(mContext, listdaasdfasdf);
                //横向scrollView的条目点击事件
                mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {

                    @Override
                    public void onClick(View view, int position) {
                        SpotDetailBean.DataEntity.NearPorEntity bean = dataEntity.getNearPor().get(position);
                        Intent intent = new Intent(mContext, GoodDetailActivity.class);
                        intent.putExtra("goodId", bean.getId());
                        mContext.startActivity(intent);
                        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
                    }
                });
                mHorizontalScrollView.initDatas(mAdapter);

                //可以播放语音的列表
                voiceList = dataEntity.getVoicInfo();
                if (spotDetailVoiceAdapter == null) {
                    spotDetailVoiceAdapter = new SpotDetailVoiceAdapter((ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity>) voiceList);
                    listView.setAdapter(spotDetailVoiceAdapter);
                } else {
                    spotDetailVoiceAdapter.updataList((ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity>) voiceList);
                }

                //初始化播放的第一条数据
                spotDetailVoiceAdapter.setCheckId(0);
                SpotDetailBean.DataEntity.VoicInfoEntity voicInfoEntity = voiceList.get(0);
                ImageLoader.getInstance().displayImage(voicInfoEntity.getImg_url().split(",")[0].trim(), voiceImg, ImageLoaderOptions.pager_options);
                voiceName.setText(voicInfoEntity.getName());
                if (iservice == null)
                    iservice = ((SpotDetailActivity) mContext).getIservice();
                iservice.callPlayMusic(voicInfoEntity.getUpurl());
                iservice.callPauseMusic();
                isPlayMusic = false;
                municImg.setImageResource(R.mipmap.icon_play_bofang);
                //添加点击事件
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        spotDetailVoiceAdapter.setCheckId(position);
                        SpotDetailBean.DataEntity.VoicInfoEntity voicInfoEntity = voiceList.get(position);
                        ImageLoader.getInstance().displayImage(voicInfoEntity.getImg_url().split(",")[0].trim(), voiceImg, ImageLoaderOptions.pager_options);
                        voiceName.setText(voicInfoEntity.getName());
                        iservice.callPlayMusic(voicInfoEntity.getUpurl());
                    }
                });
            } else {
                ToastUtil.showToast(mContext, spotDetailBean.getMsg());
                ((SpotDetailActivity) mContext).showOrHideBack(true);
            }
        }
    }

    //点击显示播放的语音的内容的文字部分
    @OnClick(R.id.iv_spot_detail_voice_text_des)
    void showSpotDetailText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        View view = View.inflate(mContext, R.layout.layout_find_voice_play_text_content, null);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }

    //初始化轮播图
    private void initRollView(String s) {
        imageList.clear();
        dotList.clear();
        String[] imges = s.split(",");
        for (String img : imges) {
            imageList.add(img);
        }
        if (imageList.size() > 0) {
            //初始化小点
            initDoc();
            //添加之定义的viewPage带有滚动效果的
            rollViewPage = new RollViewPage(QulianApplication.getContext(), imageList, dotList);
            rollViewPage.roll();
            rollViewPage.setCurrentItem(imageList.size() * 50);
            rollViewPage.setOnTouchImage(new RollViewPage.OnTouchImage() {

                @Override
                public void touchImage(String url) {
                }
            });
            top_news_viewpager.removeAllViews();
            top_news_viewpager.addView(rollViewPage);
        }
    }

    //初始化小点的个数
    private void initDoc() {
        dots_ll.removeAllViews();
        dotList.clear();
        for (int i = 0; i < imageList.size(); i++) {
            View view = new View(QulianApplication.getContext());
            if (i == 0) {
                view.setBackgroundResource(R.drawable.shape_point_all_white);
            } else {
                view.setBackgroundResource(R.drawable.shape_point_half_white);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonHelp.dip2px(QulianApplication.getContext(), 6),
                    CommonHelp.dip2px(QulianApplication.getContext(), 6));
            params.setMargins(CommonHelp.dip2px(QulianApplication.getContext(), 5), 0, 0, 0);
            dots_ll.addView(view, params);
            dotList.add(view);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rollViewPage != null) {
            rollViewPage.roll();
        }
    }

    @OnClick(R.id.iv_detail_back)
    void finishActivity() {
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
    }


    //点击播放音乐的操作
    @OnClick(R.id.iv_spot_play_detail_munic)
    void playDetail() {
        if (!isPlayMusic) {
            if (iservice == null)
                iservice = ((SpotDetailActivity) mContext).getIservice();
            iservice.callPauseMusic();
            municImg.setImageResource(R.mipmap.icon_play_zanting);
        }else{
            if (iservice == null)
                iservice = ((SpotDetailActivity) mContext).getIservice();
            iservice.callPlayMusic();
            municImg.setImageResource(R.mipmap.icon_play_bofang);
        }
        isPlayMusic = !isPlayMusic;
    }
}
