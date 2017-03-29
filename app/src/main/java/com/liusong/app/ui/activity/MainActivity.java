package com.liusong.app.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityMainBinding;
import com.liusong.app.ui.activity.softkeyboard.KeyBoardMainActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn_keyboard_main:
                intent.setClass(this, KeyBoardMainActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
