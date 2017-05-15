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
        receiveData();
    }

    /**
     * 获得其他地方传递过来的数据
     */
    private void receiveData() {
        if (getIntent().getData() != null) {
            //获得Host，也就是info://后面的内容
            String host = getIntent().getData().getHost();
            Bundle bundle = getIntent().getExtras();
            //其他的应用程序会传递过来一个value值，在该应用程序中需要获得这个值
            String value = bundle.getString("value");
            //将Host和Value组合在一下显示在EditText组件中
            mBinding.tvData.setText(host + ":" + value);
        }
    }
}
