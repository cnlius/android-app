package com.liusong.app.ui.thirdparty;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityTpJpushBinding;
import com.liusong.app.utils.Constants;

/**
 * Created by liusong on 2017/8/1.
 */

public class JPushActivity extends BaseActivity {
    private ActivityTpJpushBinding mBinding;
    private static final int REQUEST_CODE_ASK_WRITE_SETTINGS = 0x16;
    private static final int REQUEST_CODE_ASK_SYSTEM_ALERT_WINDOW = 0x17;

    private void requestMustPermissions() {
        String[] permissions = {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
        };
        if (!hasPermission(permissions)) {
            requestPermission(Constants.GRANT_ALL_CODE, getUnGrantPermissions(permissions));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tp_jpush);
        requestMustPermissions();
        requestWriteSettings();
        requestOverlay();
    }

    private void requestOverlay() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_ASK_SYSTEM_ALERT_WINDOW);
                showToast("到设置页面，请允许显示悬浮窗");
            }
        }
    }

    private void requestWriteSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, REQUEST_CODE_ASK_WRITE_SETTINGS);
                showToast("到设置页面，请允许对手机设置进行修改");
            } else {
                //有了权限，你要做什么呢？具体的动作
            }
        }
    }

}
