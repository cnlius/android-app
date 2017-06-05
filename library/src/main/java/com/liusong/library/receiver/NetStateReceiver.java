package com.liusong.library.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.liusong.library.utils.NetUtils;

/**
 * 网络状态监听广播
 * Created by liusong on 2017/6/5.
 */

public class NetStateReceiver extends BroadcastReceiver {
    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)) {
            if (NetUtils.isConnected(context)) {
                Log.i("LOG_CAT", "当前网络状态可用");
            } else {
                Log.i("LOG_CAT", "当前网络不可用");
            }
        }
    }
}
