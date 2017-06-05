package com.liusong.app.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by liusong on 2017/6/5.
 */

public class App extends Application {

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i("Application","onTerminate()");
    }
}
