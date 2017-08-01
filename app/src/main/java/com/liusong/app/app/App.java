package com.liusong.app.app;

import android.app.Application;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by liusong on 2017/6/5.
 */

public class App extends Application {

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i("Application","onTerminate()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initJPush();
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
