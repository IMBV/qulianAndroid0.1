package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.popAdapter.HotGoodChildAdapter;
import com.quliantrip.qulian.adapter.popAdapter.HotGoodGroupAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.ui.activity.GoodDetailActivity;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yuly on 2015/12/8.
 * www.quliantrip.com
 */
public class HotGoodsFragment extends BasePageCheckFragment {

    @Bind(R.id.ll_route_sift)
    LinearLayout siftCondition;
    @Bind(R.id.tv_recommend_route_text)
    TextView siftText;
    @Bind(R.id.iv_recommend_route_img)
    ImageView siftImg;
    @Bind(R.id.v_consume_list_bottom_line)
    View bottomLine;

    @Bind(R.id.rg_hot_good_classfy)
    RadioGroup radioGroup;
    //这构造函数中赋值

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 20:
                    childAdapter.setChildData(quanArray.get(msg.arg1).getQuan_sub());
                    childAdapter.notifyDataSetChanged();
                    groupAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_hot_good, null);
        ButterKnife.bind(this, view);
        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        initRadioButton();
        return view;
    }

    //添加选着条件按钮
    private void initRadioButton() {
        View view = View.inflate(mContext, R.layout.view_hot_good_classfy_item, null);
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.rb_hot_good_classfy_item);
        radioButton.setText("美食");
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "我被点击了");
            }
        });
        //设置被点击了
        radioButton.setChecked(true);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(CommonHelp.dip2px(mContext, 57), CommonHelp.dip2px(mContext, 27));
        view.setLayoutParams(params);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(CommonHelp.dip2px(mContext, 15), CommonHelp.dip2px(mContext, 27));
        View view2 = new View(mContext);
        view2.setLayoutParams(params2);
        radioGroup.addView(view2);
        radioGroup.addView(view);
    }

    private QuestBean questBean;

    @Override
    protected QuestBean requestData() {
        if (questBean == null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("ctl", "tuan");
            map.put("r_type", "1");
            return new QuestBean(map, new TuanBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
        } else {
            return questBean;
        }
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            //想mode添加数据
            TuanBean tuanbean = (TuanBean) bean;
            quanArray = tuanbean.getQuan_list();
        }
    }


    private boolean isShowSift = true;

    @OnClick(R.id.ll_route_sift)
    void showSiftRoute() {
        setSelector(isShowSift);
    }

    //显示和隐藏筛选条件
    public void setSelector(boolean b) {
        if (b) {
            siftText.setTextColor(CommonHelp.getColor(R.color.app_main_collor));
            siftImg.setImageResource(R.mipmap.nav_shaixuan_press);
            showSiftCondition();
        } else {
            siftText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
            siftImg.setImageResource(R.mipmap.nav_shaixuan_normal);
            hidePopupWindow();
        }
        isShowSift = !isShowSift;
    }

    List<TuanBean.QuanListEntity> quanArray;
    private PopupWindow siftConditionPop;
    ListView groupListView = null;
    ListView childListView = null;
    HotGoodGroupAdapter groupAdapter = null;
    HotGoodChildAdapter childAdapter = null;
    private PopupWindow siftPopupWindow;

    //显示筛选条件
    public void showSiftCondition() {
        View popView = null;
        if (siftConditionPop == null) {
            popView = View.inflate(mContext, R.layout.popupwindow_sift_double_list, null);

            groupListView = (ListView) popView.findViewById(R.id.lv_group);
            childListView = (ListView) popView.findViewById(R.id.lv_child);

            groupAdapter = new HotGoodGroupAdapter(mContext, quanArray);
            groupListView.setAdapter(groupAdapter);
        }

        groupListView.setOnItemClickListener(new MyItemClick());
        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TuanBean.QuanListEntity.QuanSubEntity bean =
                        (TuanBean.QuanListEntity.QuanSubEntity) parent.getAdapter().getItem(position);
                ToastUtil.showToast(mContext, bean.getName());
                //请求数据后发送给主线程中
            }
        });

        siftPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, CommonHelp.dip2px(mContext, 285));
        siftPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //显示的筛选popupwinsow的坐标位置
        int[] location = new int[2];
        bottomLine.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        siftPopupWindow.showAtLocation(bottomLine, Gravity.LEFT | Gravity.TOP, x, y + 1);
    }

    class MyItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            groupAdapter.setSelectedPosition(position);

            if (childAdapter == null) {
                childAdapter = new HotGoodChildAdapter(mContext);
                childListView.setAdapter(childAdapter);
            } else {
                childListView.setAdapter(childAdapter);
            }
            Message msg = new Message();
            msg.what = 20;
            msg.arg1 = position;
            handler.sendMessage(msg);
        }
    }

    //隐藏pouwindow
    public void hidePopupWindow() {
        //在onsrcll中的方法在oncreate会调用,所以判断是否为空
        if (siftPopupWindow != null) {
            siftPopupWindow.dismiss();
            siftPopupWindow = null;
            siftText.setTextColor(CommonHelp.getColor(R.color.app_main_title_text));
            siftImg.setImageResource(R.mipmap.nav_shaixuan_normal);
        }
    }

    @OnClick(R.id.ll_hot_good_best_item)
    void showBestHotGoodDetail(){
        Intent intent = new Intent(mContext,GoodDetailActivity.class);
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }
}