package com.liusong.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 灰色保活手法创建的Service进程
 *
 * @author Clock
 * @since 2016-04-12
 */
public class LiveService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
