package com.liusong.app.ui.banner;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liusong.app.R;
import com.liusong.app.adapter.BannerAdapter;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityBannerItem3Binding;

import java.util.ArrayList;

/**
 * Created by liusong on 2017/7/17.
 */

public class Item3Activity extends BaseActivity {
    private ActivityBannerItem3Binding mBinding;
    //    private Item3Adapter mAdapter;
    private BannerAdapter mAdapter;
    int[] imgRes = {R.mipmap.banner_1, R.mipmap.banner_2, R.mipmap.banner_3, R.mipmap.banner_4};
    private ArrayList<Integer> banners;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_banner_item3);
        mBinding.vp.setPageMargin(20);
        mBinding.vp.setOffscreenPageLimit(3);
//        mAdapter = new Item3Adapter(this, mBinding.vp, imgRes);


        initBanner();
//        mAdapter = new BannerAdapter(this, mBinding.vp, banners);
//        mBinding.vp.setAdapter(mAdapter);
//        mBinding.vp.setPageTransformer(true, new AlphaPageTransformer()); //????弘扬的库
    }

    private void initBanner() {
        banners = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            banners.add(imgRes[i]);
        }
    }


}
