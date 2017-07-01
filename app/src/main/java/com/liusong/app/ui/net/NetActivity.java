package com.liusong.app.ui.net;

import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.databinding.ActivityNetBinding;
import com.liusong.library.receiver.NetStateReceiver;

/**
 * Created by liusong on 2017/6/5.
 */

public class NetActivity extends BaseActivity {
    private ActivityNetBinding mBinding;
    private NetStateReceiver netStateReceiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_net);

        registerNetReceiver();
    }

    /**
     * 注册网络改变监听广播
     */
    private void registerNetReceiver() {
        netStateReceiver = new NetStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(netStateReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netStateReceiver);
    }
}
