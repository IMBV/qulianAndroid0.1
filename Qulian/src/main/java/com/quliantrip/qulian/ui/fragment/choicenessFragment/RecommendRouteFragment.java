package com.quliantrip.qulian.ui.fragment.choicenessFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.popAdapter.NavsGroupAdapter;
import com.quliantrip.qulian.adapter.popAdapter.SingleListAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.HomePageBean;
import com.quliantrip.qulian.domain.SingleListBean;
import com.quliantrip.qulian.domain.TuanBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yuly on 2015/12/7.
 * www.quliantrip.com
 */
public class RecommendRouteFragment extends BasePageCheckFragment {
    @Bind(R.id.v_consume_list_bottom_line)
    View bottomLine;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choiceness_play_menthod, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ctl", "tuan");
        map.put("r_type", "1");
        return new QuestBean(map, new TuanBean().setTag(getClass().getName()), HttpConstants.HOST_ADDR_ROOT_NET);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {

    }

    private boolean isShowTheme = true;

    @OnClick(R.id.ll_paly_method_specialty_theme)
    void showSpacialtyTheme() {
        List<SingleListBean> list = new ArrayList<SingleListBean>();
        list.add(new SingleListBean("1", "nihasdfh"));
        list.add(new SingleListBean("2", "nihasdfh2"));
        list.add(new SingleListBean("3", "nihasdfh3"));
        list.add(new SingleListBean("4", "nihasdfh4"));
        list.add(new SingleListBean("5", "nihasdfh5"));
        list.add(new SingleListBean("1", "nihasdfh"));
        list.add(new SingleListBean("2", "nihasdfh2"));
        list.add(new SingleListBean("3", "nihasdfh3"));
        list.add(new SingleListBean("4", "nihasdfh4"));
        list.add(new SingleListBean("5", "nihasdfh5"));
        if (isShowTheme)
            showSingleListPopuWindow(list);
        else
            hidePopupWindow();
        isShowTheme = !isShowTheme;
    }

    private PopupWindow singlePopupWindow;
    MyListView singleListView = null;
    SingleListAdapter singleListAdapter = null;

    public void showSingleListPopuWindow(List<SingleListBean> singleList) {

        View popView = null;
        if (singlePopupWindow == null) {
            popView = View.inflate(mContext, R.layout.popupwindow_sift_single_list, null);

            singleListView = (MyListView) popView.findViewById(R.id.mlv_single_list);
            singleListAdapter = new SingleListAdapter(mContext, singleList);
            singleListView.setAdapter(singleListAdapter);
        }

        singleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //获取数据适配的bean对象
                SingleListBean bean = (SingleListBean) parent.getAdapter().getItem(position);
            }
        });
        singlePopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, CommonHelp.dip2px(mContext,285));
        singlePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //popupwindow显示的坐标的位置
        int[] location = new int[2];
        bottomLine.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        singlePopupWindow.showAtLocation(bottomLine, Gravity.LEFT | Gravity.TOP, x, y + 1);
    }

    //隐藏pouwindow
    public void hidePopupWindow() {
        //在onsrcll中的方法在oncreate会调用,所以判断是否为空
        if (singlePopupWindow != null) {
            singlePopupWindow.dismiss();
            singlePopupWindow = null;
        }
    }
}

