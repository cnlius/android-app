package com.ls.test.app;

import android.app.Application;

import com.ls.test.db.TestDbHelper;

/**
 * Created by liu song on 2017/5/19.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化，ContentProvider
        new TestDbHelper(this);
    }
}
