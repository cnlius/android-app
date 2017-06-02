package com.liusong.app.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityIpcBinding;
import com.liusong.app.utils.Constants;
import com.liusong.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨进程通信-Activity：Intent隐式跳转
 *
 * 注意：需要先安装test
 * Created by liu song on 2017/4/27.
 */

public class IPCActivity extends BaseActivity implements View.OnClickListener {
    private ActivityIpcBinding mBinding;
    private Uri uri = Uri.parse("content://com.ls.test.provider/temp");
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ipc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_phone:
                callPhone();
                break;
            case R.id.btn_to_target_activity:
                goOtherApp();
                break;
            case R.id.btn_provider_query:
                query();
                break;
            default:
                break;
        }
    }

    /**
     * 打电话
     */
    private void callPhone() {
        if (hasPermission(Manifest.permission.CALL_PHONE)) {
            doCallPhone();
        } else {
            requestPermission(Constants.CALL_PHONE_CODE, Manifest.permission.CALL_PHONE);
        }
    }

    @Override
    public void doCallPhone() {
        showToast("授权成功！");
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:18811223687"));
        //为了去除startActivity(...)警告
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            showToast("授权失败！");
            return;
        }
        startActivity(callIntent);
    }

    /**
     * 去其它应用
     */
    private void goOtherApp() {
        //需要使用Intent类的第2个参数指定Uri
        Intent intent = new Intent("com.ls.test.IPC", Uri.parse("info://调用其他应用程序的Activity"));
        //设置value属性值
        intent.putExtra("value", "飞船离开地球");
//        startActivity(intent);
        startActivityForResult(intent, 0x1);
    }

    /**
     * 查询test contentprovider共享数据
     */
    private void query() {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            list.clear();
            while (cursor.moveToNext()) {
                String nameString = cursor.getString(cursor.getColumnIndex("name"));
                String numString = cursor.getString(cursor.getColumnIndex("phone"));
                list.add(nameString + "\n" + numString);
            }
            cursor.close();
        }
        Log.i("LOG_CAT","list: "+list.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            ToastUtils.showToast(this, "返回值：" + data.getExtras().getString("result"));
        }
    }
}
