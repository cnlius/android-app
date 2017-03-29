package com.liusong.app.ui.activity.softkeyboard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityKeyboardMainBinding;

/**
 * Created by liu song on 2017/3/29.
 */

public class KeyBoardMainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityKeyboardMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_keyboard_main);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.btn_in_activity:
                intent.setClass(this,KeyboardInActivity.class);
                break;
            case R.id.btn_in_fragment:
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
