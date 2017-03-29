package com.liusong.app.ui.activity.softkeyboard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityKeyboardInBinding;

/**
 * Created by liu song on 2017/3/29.
 */

public class KeyboardInActivity extends AppCompatActivity {
    private ActivityKeyboardInBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_keyboard_in);
    }
}
