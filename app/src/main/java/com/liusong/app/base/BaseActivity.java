package com.liusong.app.base;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.liusong.app.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by liu song on 2017/5/3.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * 判断是否有权限
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取未授权的权限
     *
     * @param permissions
     * @return
     */
    public String[] getUnGrantPermissions(String... permissions) {
        List<String> ps = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                ps.add(permission);
            }
        }
        return ps.toArray(new String[ps.size()]);
    }

    /**
     * 请求权限
     *
     * @param code
     * @param permissions
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.CALL_PHONE_CODE:
                doCallPhone();
                break;
            case Constants.GRANT_ALL_CODE:
                doGrantAll();
                break;
        }
    }

    /**
     * 授权所有权限
     */
    private void doGrantAll() {
        showToast("授权所有成功！");
    }

    /**
     * 打电话权限申请成功回调
     */
    public void doCallPhone() {
    }

    public void showToast(String msg) {
        if(Constants.LOG_SWITCH) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
