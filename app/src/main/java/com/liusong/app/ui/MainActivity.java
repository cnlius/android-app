package com.liusong.app.ui;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityMainBinding;
import com.liusong.app.ui.ipc.ContentProviderActivity;
import com.liusong.app.ui.ipc.IntentActivity;
import com.liusong.app.ui.keyboard.ShowHideActivity;
import com.liusong.app.ui.net.NetActivity;
import com.liusong.app.ui.db.SQLiteActivity;
import com.liusong.app.ui.update.DmActivity;
import com.liusong.app.utils.Constants;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        requestMustPermissions();
    }

    /**
     * 申请app必须的权限
     */
    private void requestMustPermissions() {
        String[] permissions = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
        };
        if (!hasPermission(permissions)) {
            requestPermission(Constants.GRANT_ALL_CODE, getUnGrantPermissions(permissions));
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_update:
                intent.setClass(this, DmActivity.class);
                break;
            case R.id.btn_ipc:
                intent.setClass(this, IntentActivity.class);
                break;
            case R.id.btn_keyboard_main:
                intent.setClass(this, ShowHideActivity.class);
                break;
            case R.id.btn_content_resolver:
                intent.setClass(this, ContentProviderActivity.class);
                break;
            case R.id.btn_sqlite:
                intent.setClass(this, SQLiteActivity.class);
                break;
            case R.id.btn_net:
                intent.setClass(this, NetActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
