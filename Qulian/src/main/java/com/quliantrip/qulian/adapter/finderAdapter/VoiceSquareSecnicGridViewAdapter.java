package com.quliantrip.qulian.adapter.finderAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.quliantrip.qulian.R;
import com.quliantrip.qulian.adapter.BasicAdapter;
import com.quliantrip.qulian.domain.find.VoiceSquareBean;
import com.quliantrip.qulian.global.ImageLoaderOptions;
import com.quliantrip.qulian.global.QulianApplication;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 城市分类的适配器对象
 */
public class VoiceSquareSecnicGridViewAdapter extends BasicAdapter<VoiceSquareBean.DataEntity.ChildEntity> {

    public VoiceSquareSecnicGridViewAdapter(ArrayList<VoiceSquareBean.DataEntity.ChildEntity> list) {
        super(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(QulianApplication.getContext(), R.layout.adapter_secnic_voice_list_item, null);
        }
        Holder holder = Holder.getHolder(convertView);
        VoiceSquareBean.DataEntity.ChildEntity bean = list.get(position);
        holder.name.setText(bean.getScenic());
        ImageLoader.getInstance().displayImage(bean.getImgs().split(",")[0], holder.img, ImageLoaderOptions.pager_options);
        return convertView;
    }

    static class Holder {
        @Bind(R.id.tv_secnic_voice_name)
        TextView name;
        @Bind(R.id.tiv_scenic_voice_item_img)
        ImageView img;

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
