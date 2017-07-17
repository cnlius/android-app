package com.liusong.app.ui.banner;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityBannerBinding;
import com.liusong.app.widget.banner.BannerAdapter;

import java.util.ArrayList;

/**
 * Created by liusong on 2017/7/17.
 */

public class BannerActivity extends BaseActivity {
    private int[] imgRes = {R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4};
    private ActivityBannerBinding mBinding;
    private BannerAdapter mAdapter;
    private ArrayList<ImageView> banners;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_banner);
//        mBinding.vp.setPageMargin(20);
//        mBinding.vp.setOffscreenPageLimit(3);
        initBanner();
//        mAdapter = new BannerAdapter(banners);
//        mAdapter.setOnBannerItemClickListener(new OnBannerItemClickListener() {
//            @Override
//            public void call(int position) {
//
//            }
//        });
//        mBinding.vp.setAdapter(mAdapter);

//        mBinding.vp.setPageTransformer(true, new AlphaPageTransformer()); //????弘扬的库
        mBinding.bv.setData(banners);
    }

    private void initBanner() {
        banners = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imgRes[i]);
            banners.add(imageView);
        }
    }


}
