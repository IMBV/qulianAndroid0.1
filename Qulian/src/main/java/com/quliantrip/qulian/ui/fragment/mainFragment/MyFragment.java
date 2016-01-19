package com.quliantrip.qulian.ui.fragment.mainFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseFragment;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.ui.activity.meActivity.FeedbackActivity;
import com.quliantrip.qulian.ui.activity.meActivity.MyCollectActivity;
import com.quliantrip.qulian.ui.activity.meActivity.MyOrderActivity;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.util.UIHelper;
import com.quliantrip.qulian.view.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yuly on 2015/11/9.
 */

public class MyFragment extends BaseFragment {
    //用户的头像
    @Bind(R.id.ll_user_loading)
    LinearLayout ll_user_loading;
    @Bind(R.id.tv_me_userImage)
    CircleImageView userImage;
    //用户的名称
    @Bind(R.id.tv_me_username)
    TextView userName;

    private Bundle bundle = new Bundle();
    private View view;

    @Override
    public View initView() {
        view = View.inflate(mContext, R.layout.fragment_main_me, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 41) {
            initDate();
        }
        if(requestCode == 42){
            initDate();
        }
    }

    //加载显示数据
    @Override
    public void initDate() {
        //已经登录进行初始化值
        if(QulianApplication.getInstance().isLogin()){
            ll_user_loading.setClickable(false);
            userName.setText(QulianApplication.getInstance().getLoginUser().getUsername());
        }else {
            userName.setText("点击登录");
            ll_user_loading.setClickable(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getView() != null){
            initDate();
        }
    }

    //设置
    @OnClick(R.id.iv_me_seeting)
    void showMySetting() {
        if(QulianApplication.getInstance().isLogin()){
            UIHelper.showMeSetting(this,42);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this,41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //登录界面
    @OnClick(R.id.ll_user_loading)
    void showMylogin() {
        UIHelper.showMyLogin(this,41);
        ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
    }

    //我的收藏
    @OnClick(R.id.ll_me_collect)
    void showCollect() {
        if(QulianApplication.getInstance().isLogin()){
            Intent intent = new Intent(mContext, MyCollectActivity.class);
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的积分
    @OnClick(R.id.ll_me_integral)
    void showIntegral() {
        if(QulianApplication.getInstance().isLogin()){
            UIHelper.showIntegral(mContext, null);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的订单
    @OnClick(R.id.ll_me_orderList)
    void showOrderList() {
        if(QulianApplication.getInstance().isLogin()){
            Intent intent = new Intent(mContext, MyOrderActivity.class);
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //常用联系人
    @OnClick(R.id.ll_me_offen_lianxiren)
    void showOffLianxiren() {
        if(QulianApplication.getInstance().isLogin()){
            UIHelper.showMyCommonInfo(mContext, null);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的优惠券
    @OnClick(R.id.ll_me_youhuijuan)
    void showYouhuijuan() {
        if(QulianApplication.getInstance().isLogin()){
            UIHelper.showMyDisCount(mContext, null);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }

    //我的优惠券
    @OnClick(R.id.ll_me_fankui)
    void showFankui() {
        if(QulianApplication.getInstance().isLogin()){
            Intent intent = new Intent(mContext,FeedbackActivity.class);
            mContext.startActivity(intent);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }else{
            UIHelper.showMyLogin(this, 41);
            ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_next, R.anim.setup_exit_next);
        }
    }
}
