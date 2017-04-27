package com.ls.test.ipc;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ls.test.R;
import com.ls.test.databinding.ActivityIpcTargetBinding;

/**
 * Created by liu song on 2017/4/27.
 */

public class IPCTargetActivity extends AppCompatActivity {
    private ActivityIpcTargetBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ipc_target);
    }
}
