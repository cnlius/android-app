package com.liusong.library.updateapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.liusong.library.interfaces.CallBack1;

/**
 * 下载目录观察者
 * content://downloads/my_downloads
 * Created by liu song on 2017/5/11.
 */

public class DownloadObserver extends ContentObserver {
    private DownloadManager downloadManager;
    private CallBack1<Integer> callBack;
    private long lastDownloadId;

    public DownloadObserver(Context context, Handler handler, CallBack1<Integer> callBack) {
        super(handler);
        this.callBack=callBack;
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        lastDownloadId = sp.getLong(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
    }

    @Override
    public void onChange(boolean selfChange) {
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(lastDownloadId);
        Cursor cursor = downloadManager.query(query);
        if (cursor != null) {
            cursor.moveToFirst();
            int curProgress = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int totalProgress = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            cursor.close();
            int progress = curProgress * 100 /totalProgress;
            callBack.call(progress);
        }
    }
}
