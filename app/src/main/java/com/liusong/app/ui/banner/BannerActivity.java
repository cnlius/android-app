package com.liusong.app.ui.banner;

import android.databinding.DataBindingUtil;
import android.net.Uri;
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
        initBanner();
        mBinding.bv.setData(banners);

//        mBinding.vp.setPageTransformer(true, new AlphaPageTransformer()); //????弘扬的库

    }

    private void initBanner() {
        banners = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            String url="file:///android_asset/banner_"+(i+1)+".jpg";
            imageView.setImageResource(imgRes[i]);
//            imageView.setImageURI(Uri.parse(url));
            banners.add(imageView);
        }
    }


}
