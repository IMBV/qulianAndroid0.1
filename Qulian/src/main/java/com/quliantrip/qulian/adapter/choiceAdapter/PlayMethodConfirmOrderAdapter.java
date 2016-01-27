package com.quliantrip.qulian.adapter.choiceAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMehtodOrderConfirmBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.view.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Qulian5 on 2016/1/21.
 */
public class PlayMethodConfirmOrderAdapter extends BasicAdapter<PlayMehtodOrderConfirmBean.DataEntity> {

    public PlayMethodConfirmOrderAdapter(ArrayList<PlayMehtodOrderConfirmBean.DataEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_choice_play_method_confirm_order_good_list, null);
        }
        Holder holder = Holder.getHolder(convertView);
        PlayMehtodOrderConfirmBean.DataEntity bean = list.get(position);
        holder.orderName.setText(bean.getOrdershop().getName());
        holder.taocan.setText(bean.getAttribute());
        holder.data.setText(bean.getOrdershop().getDate());
//        holder.time.setText(QulianApplication.getInstance().getLoginUser().getEmail());
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_order_confirm_name)
        TextView orderName;
        @Bind(R.id.tv_order_confirm_taocan)
        TextView taocan;
        @Bind(R.id.tv_order_confirm_data)
        TextView data;
        @Bind(R.id.tv_order_confirm_time)
        TextView time;

        public Holder(View convertView) {
            super();
            ButterKnife.bind(this, convertView);
        }

        public static Holder getHolder(View convertView) {
            Holder holder = (Holder) convertView.getTag();
            if (holder == null) {
                holder = new Holder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
