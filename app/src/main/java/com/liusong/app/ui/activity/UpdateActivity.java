package com.liusong.app.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityUpdateBinding;
import com.liusong.library.updateapp.UpdateManager;
import com.liusong.library.updateapp.UpdateUtils;
import com.liusong.library.utils.ToastUtils;

/**
 * apk dowloadManager下载更新
 * Created by liu song on 2017/5/10.
 */

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityUpdateBinding mBinding;
    private static final String apkurl = "https://raw.githubusercontent.com/cnlius/resource/master/apk/collections.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simple_update:
                simpleUpdate();
                break;
            case R.id.btn_progressbar_update:
//                simpleUpdate();
                mBinding.pbUpdate.setProgress(15);
                break;
        }
    }

    /**
     * 一般的下载更新方法
     */
    private void simpleUpdate() {
        if(UpdateUtils.checkUpdateByVersionName("1.0.1","2.1.1")<0){
            ToastUtils.showToast(this,"开始下载！");
            UpdateManager.getInstance(this).downloadApk(apkurl);
        }else{
            ToastUtils.showToast(this,"已是最新，无需下载！");
        }
    }
}
