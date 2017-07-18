package com.liusong.app.widget.banner;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by liusong on 2017/7/17.
 */

public class BannerAdapter extends PagerAdapter {
    private List<ImageView> viewList;
    private OnBannerItemClickListener mClickListener;

    public BannerAdapter(List<ImageView> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = viewList.get(position);
        if (mClickListener != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Banner", "position=" + position);
                    mClickListener.call(position);
                }
            });
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        this.mClickListener = listener;
    }
}
