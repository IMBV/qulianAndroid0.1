package com.quliantrip.qulian.ui.fragment.choicenessFragment.playMethod;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.choiceAdapter.playMethod.PlayMethodOrderGoodlistAdapter;
import com.quliantrip.qulian.base.BasePageCheckFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.domain.choice.PlayMethodOrderSubmitItemBean;
import com.quliantrip.qulian.domain.choice.playMethod.OrderSubmitResultBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;
import com.quliantrip.qulian.domain.common.HintInfoBean;
import com.quliantrip.qulian.net.constant.HttpConstants;
import com.quliantrip.qulian.net.volleyManage.PacketStringReQuest;
import com.quliantrip.qulian.net.volleyManage.QuestBean;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 玩法的订单
 */
public class SubmitOrderPlayMethodFragment extends BasePageCheckFragment {
    @Bind(R.id.lv_play_method_good_check_list)
    ListView listView;
    private ArrayList<PlayMethodOrderSubmitBean.DataEntity> goodList;

    //玩法点单属性的集合
    private HashMap<Integer, PlayMethodOrderSubmitItemBean> resuleMap = new HashMap<>();
    //玩法单品的数据适配器
    private PlayMethodOrderGoodlistAdapter playMethodOrderGoodlistAdapter;

    @Override
    protected View getSuccessView() {
        View view = View.inflate(mContext, R.layout.fragment_choice_play_method_order_check, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected QuestBean requestData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        return new QuestBean(map, new PlayMethodOrderSubmitBean().setTag(getClass().getName()), HttpConstants.PLAY_METHOD_ORDER);
    }

    @Override
    public void onEventMainThread(BaseJson bean) {
        //获取进入界面的数据
        if (bean != null && this.getClass().getName().equals(bean.getTag())) {
            PlayMethodOrderSubmitBean playMethodOrderSubmitBean = (PlayMethodOrderSubmitBean) bean;
            if (playMethodOrderSubmitBean.getCode() == 200) {
                //对数据进行
                goodList = (ArrayList<PlayMethodOrderSubmitBean.DataEntity>) playMethodOrderSubmitBean.getData();
                playMethodOrderGoodlistAdapter = new PlayMethodOrderGoodlistAdapter(goodList, mContext);
                listView.setAdapter(playMethodOrderGoodlistAdapter);

                //设置listView之间的间距
                listView.setDivider(new ColorDrawable(CommonHelp.getColor(R.color.app_main_bg)));
                listView.setDividerHeight(CommonHelp.dip2px(mContext, 10));
            } else {
                ToastUtil.showToast(mContext, playMethodOrderSubmitBean.getMsg());
            }
        }
        if (bean != null && (this.getClass().getName() + "check").equals(bean.getTag())) {
            HintInfoBean hintInfoBean = (HintInfoBean) bean;
            if (hintInfoBean.getCode() == 200){

            }else {
                ToastUtil.showToast(mContext,hintInfoBean.getMsg());
            }
        }

        //检测订单提交是否成功
        if (bean != null && (this.getClass().getName() + "submit").equals(bean.getTag())) {
            OrderSubmitResultBean goodOrderSubmitBean = (OrderSubmitResultBean) bean;
            if (goodOrderSubmitBean.getCode() == 200) {
                ToastUtil.showToast(mContext, goodOrderSubmitBean.getMsg()+goodOrderSubmitBean.getData().getId());
                Bundle bundle = new Bundle();
                bundle.putString("orderId", "74");
                UIHelper.showPlayMethodOrderConfirm(mContext, bundle);
                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
            } else {
                ToastUtil.showToast(mContext, goodOrderSubmitBean.getMsg());
            }
        }
    }

    //提交订单
    @OnClick(R.id.bt_order_submi_topay)
    void toConfirmOrder() {
//        System.out.println(getDataString());
//        ToastUtil.showToast(mContext,playMethodOrderGoodlistAdapter.getResuleMap().get(0).getDate()+"");
        Map<String, String> map = new HashMap<String, String>();
        map.put("proid", "1");
        map.put("key", "-14KirwNmSQQCMiuYBEXtJBWLllbs7Ma");
        map.put("total_price", "122");
        map.put("type", "2");
        map.put("data", getDataString());
        new PacketStringReQuest(HttpConstants.PLAY_METHOD_ORDER_SUBMIT, new OrderSubmitResultBean().setTag(getClass().getName() + "submit"), map);
//        Bundle bundle = new Bundle();
//        bundle.putString("orderId", "53");
//        UIHelper.showPlayMethodOrderConfirm(mContext, bundle);
//        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    private String getDataString() {
        resuleMap = playMethodOrderGoodlistAdapter.getResuleMap();
        String string = "[";
        for (int i = 0; i <goodList.size(); i++) {
            PlayMethodOrderSubmitItemBean playMethodOrderSubmitItemBean = resuleMap.get(i);
            string += playMethodOrderSubmitItemBean.toString();
            if (i != goodList.size()-1)
                string += ",";
        }
        string += "]";
        return string;
    }
}