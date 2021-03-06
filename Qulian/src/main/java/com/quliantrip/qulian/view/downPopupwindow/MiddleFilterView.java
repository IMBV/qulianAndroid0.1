package com.quliantrip.qulian.view.downPopupwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.quliantrip.qulian.R;

public class MiddleFilterView extends RelativeLayout {

    private ListView mListView;
    private String[] items = null;
    private OnSelectListener mOnSelectListener;
    private TextAdapter adapter;
    private String showText = "item1";

    public String getShowText() {
        return showText;
    }

    public MiddleFilterView(Context context, String items[]) {
        super(context);
        this.items = items;
        init(context);
    }

    public MiddleFilterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public MiddleFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_distance, this, true);
        mListView = (ListView) findViewById(R.id.listView);
        adapter = new TextAdapter(context, items, R.drawable.choose_eara_item_selector);
        adapter.setTextSize(14);

        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                if (mOnSelectListener != null) {
                    showText = items[position];
                    mOnSelectListener.getValue(items[position], items[position]);
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        public void getValue(String distance, String showText);
    }
}