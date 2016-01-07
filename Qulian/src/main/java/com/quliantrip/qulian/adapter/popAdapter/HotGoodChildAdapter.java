package com.quliantrip.qulian.adapter.popAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.TuanBean;

import java.util.List;


public class HotGoodChildAdapter extends BaseAdapter {

    Context mContext;
    List<TuanBean.QuanListEntity.QuanSubEntity> mChildArr;// 子item标题数组

    public HotGoodChildAdapter(Context context) {
        mContext = context;
    }

    public void setChildData(List<TuanBean.QuanListEntity.QuanSubEntity> childArr) {
        this.mChildArr = childArr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_child_layout, null);
            holder.childText = (TextView) convertView
                    .findViewById(R.id.child_textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.childText.setText(mChildArr.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView childText;
    }

    @Override
    public int getCount() {
        if (mChildArr == null) {
            return 0;
        }
        return mChildArr.size();
    }

    @Override
    public Object getItem(int position) {
        return mChildArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
