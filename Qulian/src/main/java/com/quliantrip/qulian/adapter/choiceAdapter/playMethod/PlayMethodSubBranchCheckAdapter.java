package com.quliantrip.qulian.adapter.choiceAdapter.playMethod;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.choice.good.OrderSubmitBean;
import com.quliantrip.qulian.domain.choice.playMethod.PlayMethodOrderSubmitBean;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选着门店
 */
public class PlayMethodSubBranchCheckAdapter extends BasicAdapter<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> {
    public PlayMethodSubBranchCheckAdapter(ArrayList<PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_dialog_subbranch_check_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        final PlayMethodOrderSubmitBean.DataEntity.BranchnameEntity bean = list.get(position);
//        ImageLoader.getInstance().displayImage((String) bean.getImages(), holder.img, ImageLoaderOptions.pager_options);
        holder.name.setText(bean.getName());
        holder.address.setText(bean.getAddress());
        return convertView;
    }

    static class Holder {
//        @Bind(R.id.iv_sub_branch_img)
//        ImageView img;
        @Bind(R.id.iv_sub_branch_name)
        TextView name;
        @Bind(R.id.iv_sub_branch_address)
        TextView address;

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
