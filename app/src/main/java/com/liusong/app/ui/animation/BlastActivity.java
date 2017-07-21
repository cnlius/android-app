package com.liusong.app.ui.animation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityBlastBinding;

import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by liusong on 2017/7/21.
 */

public class BlastActivity extends BaseActivity implements View.OnClickListener{
    private ActivityBlastBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_blast);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test:
                blast();
                break;
        }
    }

    private void blast() {
        // 先初始化载入ExplosionField
        ExplosionField explosionField = ExplosionField.attach2Window(this);
        explosionField.explode(mBinding.image);
    }
}
