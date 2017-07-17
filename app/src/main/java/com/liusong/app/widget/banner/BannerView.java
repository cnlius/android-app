package com.liusong.app.widget.banner;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.liusong.app.R;
import com.liusong.app.databinding.BannerViewBinding;

import java.util.ArrayList;

/**
 * Created by liusong on 2017/7/17.
 */

public class BannerView extends FrameLayout {
    private BannerViewBinding mBinding;
    private BannerAdapter mAdapter;
    private ViewPager mViewPager;


    public BannerView(@NonNull Context context) {
        this(context, null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.banner_view, this, true);

    }

    /**
     * 定制BannerView属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        /*TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.xxx);
        typeArray.recycle();*/
    }

    // 处理逻辑

    public void setData(ArrayList<ImageView> views) {
        mAdapter = new BannerAdapter(views);
        mBinding.vp.setPageMargin(20);
        mBinding.vp.setOffscreenPageLimit(3);
        mBinding.vp.setAdapter(mAdapter);

    }


}































