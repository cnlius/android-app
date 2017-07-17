package com.liusong.app.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by liusong on 2017/7/17.
 */

public class BannerAdapter extends PagerAdapter {
    private ArrayList<ImageView> viewList;

    public BannerAdapter(ArrayList<ImageView> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView view = new ImageView(mContext);
//        view.setScaleType(ImageView.ScaleType.FIT_XY);
//        view.setImageResource(mImages.get(position));
//
//        container.addView(view);
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //Warning：不要在这里调用removeView
    }
}
