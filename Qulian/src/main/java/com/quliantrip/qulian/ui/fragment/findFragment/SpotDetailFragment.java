package com.quliantrip.qulian.ui.fragment.findFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.finderAdapter.SpotDetailVoiceAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.find.SpotDetailBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.findActivity.SpotDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
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
 * 玩法详情页
 */
public class SpotDetailFragment extends BasePageCheckFragment {
    private View view;
    private AlertDialog dialog;//

    //轮播图与下面的小点
    private RollViewPage rollViewPage;
    @Bind(R.id.top_news_viewpager)
    LinearLayout top_news_viewpager;//轮播的viewpage
    @Bind(R.id.dots_ll)
    LinearLayout dots_ll;//下面的小点
    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    //景点语音的列表
    @Bind(R.id.spot_introduct_voice)
    MyListView listView;

    @Bind(R.id.seekbar_progress)
    SeekBar seekBar;

    @Override
    protected View getSuccessView() {
        view = View.inflate(mContext, R.layout.fragment_find_spot_method_detail, null);
        ButterKnife.bind(this, view);
        seekBar.setMax(100);
        initRollView();
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
                SpotDetailBean.DataEntity dataEntity = spotDetailBean.getData();
                ((SpotDetailActivity) mContext).showOrHideBack(false);
                listView.setAdapter(new SpotDetailVoiceAdapter((ArrayList<SpotDetailBean.DataEntity.VoicInfoEntity>) dataEntity.getVoicInfo()));
            } else {
                ToastUtil.showToast(mContext, spotDetailBean.getMsg());
                ((SpotDetailActivity) mContext).showOrHideBack(true);
            }
        }
    }

    //点击显示播放的语音的内容的文字部分
    @OnClick(R.id.iv_spot_detail_voice_text_des)
    void showSpotDetailText(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        View view = View.inflate(mContext, R.layout.layout_find_voice_play_text_content, null);
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }

    //初始化轮播图
    private void initRollView() {
        imageList.clear();
        dotList.clear();
        imageList.add("http://www.quliantrip.com/public/attachment/201511/20/16/564ed3a64400b.png");
        imageList.add("http://www.quliantrip.com/public/attachment/201511/20/16/564ed3762c8e5.png");
        imageList.add("http://www.quliantrip.com/public/attachment/201511/20/16/564ed327a6642.png");
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
}
