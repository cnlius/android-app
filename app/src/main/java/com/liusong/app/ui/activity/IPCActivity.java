package com.liusong.app.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityIpcBinding;

/**
 * Created by liu song on 2017/4/27.
 */

public class IPCActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityIpcBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ipc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_target_activity:
                break;
            default:
                break;
        }
    }
}
