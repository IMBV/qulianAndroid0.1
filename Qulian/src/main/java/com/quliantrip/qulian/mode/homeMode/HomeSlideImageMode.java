package com.quliantrip.qulian.mode.homeMode;

import android.view.View;
import android.widget.LinearLayout;

import com.quliantrip.qulian.R;
import com.quliantrip.qulian.domain.home.HomeShowBean;
import com.quliantrip.qulian.global.QulianApplication;
import com.quliantrip.qulian.mode.BaseMode;
import com.quliantrip.qulian.util.CommonHelp;
import com.quliantrip.qulian.view.RollViewPage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页的图片的滑动模块
 */
public class HomeSlideImageMode extends BaseMode<List<HomeShowBean.DataEntity.BannerEntity>> {

    private View view;
    private RollViewPage rollViewPage;
    @Bind(R.id.top_news_viewpager)
    LinearLayout top_news_viewpager;//轮播的viewpage
    @Bind(R.id.dots_ll)
    LinearLayout dots_ll;//下面的小点

    private ArrayList<HomeShowBean.DataEntity.BannerEntity> list;

    //添加图片和小点的集合
    private List<String> imageList = new ArrayList<String>();
    private List<View> dotList = new ArrayList<View>();

    public HomeSlideImageMode() {
        view = View.inflate(QulianApplication.getContext(), R.layout.mode_home_slideimage, null);
    }

    @Override
    public View getModelView() {
        ButterKnife.bind(this, view);
        return view;
    }

    //在这里进行数据的添加,o表示传如的类，进行空间的加载布局
    @Override
    public void setData(List<HomeShowBean.DataEntity.BannerEntity> list) {
        this.list = (ArrayList<HomeShowBean.DataEntity.BannerEntity>) list;
        initRollView();
    }

    private void initRollView() {
        imageList.clear();
        dotList.clear();
        for (HomeShowBean.DataEntity.BannerEntity bannerEntities:list) {
            imageList.add(bannerEntities.getImage());
        }
        if (imageList.size() > 0) {
            //初始化小点
            initDoc();
            //添加之定义的viewPage带有滚动效果的
            rollViewPage = new RollViewPage(QulianApplication.getContext(), imageList, dotList);
            rollViewPage.roll();
            rollViewPage.setCurrentItem(imageList.size() * 50);
            rollViewPage.setOnTouchImage(new RollViewPage.OnTouchImage() {

                @Override
                public void touchImage(String url) {
                    //这里是进行点击图片是的操作
                }
            });
            top_news_viewpager.removeAllViews();
            top_news_viewpager.addView(rollViewPage);
        }
    }

    //初始化小点的个数
    private void initDoc() {
        dots_ll.removeAllViews();
        dotList.clear();
        for (int i = 0; i < imageList.size(); i++) {
            View view = new View(QulianApplication.getContext());
            if (i == 0) {
                view.setBackgroundResource(R.drawable.shape_point_all_white);
            } else {
                view.setBackgroundResource(R.drawable.shape_point_half_white);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CommonHelp.dip2px(QulianApplication.getContext(), 6),
                    CommonHelp.dip2px(QulianApplication.getContext(), 6));
            params.setMargins(CommonHelp.dip2px(QulianApplication.getContext(), 5), 0, 0, 0);
            dots_ll.addView(view, params);
            dotList.add(view);
        }
    }
}
