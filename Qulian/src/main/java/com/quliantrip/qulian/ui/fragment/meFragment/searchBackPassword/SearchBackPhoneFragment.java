package com.quliantrip.qulian.ui.fragment.meFragment.searchBackPassword;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.base.BaseDialogFragment;
import com.quliantrip.qulian.domain.BaseJson;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.util.TimeCountUtil;
import com.quliantrip.qulian.util.ToastUtil;
import com.quliantrip.qulian.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手机找回
 */
public class SearchBackPhoneFragment extends BaseDialogFragment {
    private View view;

    //手机验证下一次的时间
    @Bind(R.id.bt_get_phone_check)
    Button phone_check;

    @Bind(R.id.bt_searck_back_password_phone)
    ClearEditText phoneNum;
    @Bind(R.id.bt_searck_back_password_phone_check_code)
    ClearEditText checkNum;
    @Bind(R.id.bt_submit_search_back_password_phone)
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_me_search_back_password_phone, null);
        ButterKnife.bind(this, view);
        button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
        initListener();
        return view;
    }
    private void initListener() {
        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(phoneNum.getText())) {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_sub_bg));
                    button.setTextColor(CommonHelp.getColor(R.color.login_bg_color));
                } else {
                    button.setBackgroundColor(CommonHelp.getColor(R.color.app_main_collor));
                    button.setTextColor(CommonHelp.getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onEventMainThread(BaseJson bean) {
        if (bean != null && (this.getClass().getName() + "mobile").equals(bean.getTag())) {
//            MoBileBean moBileBean = (MoBileBean) bean;
//            if (moBileBean.getCode()==200)
//                checkNum.setText(moBileBean.getData());
//            else
//                ToastUtil.showToast(mContext, moBileBean.getMsg());
        }
        if (bean != null && (this.getClass().getName() + "psignup").equals(bean.getTag())) {
//            UserInfoBean userInfoBean = (UserInfoBean) bean;
//            if (userInfoBean.getCode() == 200) {
//                QulianApplication.getInstance().saveUserInfo(userInfoBean.getData());
//                Intent intent = new Intent(mContext, MainActivity.class);
//                ((SimpleBackActivity) mContext).setResult(((SimpleBackActivity) mContext).RESULT_OK, intent);
//                ((SimpleBackActivity) mContext).finish();
//                ((Activity) mContext).overridePendingTransition(R.anim.setup_enter_pre, R.anim.setup_exit_pre);
//            } else {
//                ToastUtil.showToast(mContext, userInfoBean.getMsg());
//            }
        }
    }

    @OnClick(R.id.bt_submit_search_back_password_phone)
    void searchBackPasswordSubmit() {
        String phone = phoneNum.getText().toString();
        String num = checkNum.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(mContext, "请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(num)) {
            ToastUtil.showToast(mContext, "请输入验证码");
            return;
        }
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("SignupForm[mobile]", phone);
//        map.put("SignupForm[code]", num);
//        new PacketStringReQuest(HttpConstants.MOBILE_REGISTER, new UserInfoBean().setTag(getClass().getName() + "psignup"), map);
    }

    //手机号码的验证
    @OnClick(R.id.bt_get_phone_check)
    void phoneCheck() {
        String phone = phoneNum.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast(mContext, "请输入手机号");
            return;
        }

        if (!CommonHelp.isMobileNO(phone)) {
            ToastUtil.showToast(mContext, "请输入正确的手机号");
            return;
        }
        TimeCountUtil timeCountUtil = new TimeCountUtil((Activity) mContext, 60000, 1000, phone_check);
        timeCountUtil.start();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("mobile", phone);
//        new PacketStringReQuest(HttpConstants.CHECK_MOBILE_NUMBER, new MoBileBean().setTag(getClass().getName() + "mobile"), map, null);
    }
}
