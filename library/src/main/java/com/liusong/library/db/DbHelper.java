package com.liusong.library.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

/**
 * 创建数据库
 * Created by liu song on 2017/5/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="souyijie.db";
    private static final int DB_VERSION = 1;
    private String tableSql;

    public DbHelper(Context context,String tableSql) {
        super(context, DB_NAME, null, DB_VERSION);
        this.tableSql=tableSql;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!TextUtils.isEmpty(tableSql)) {
            db.execSQL(tableSql);
            Log.i("DbHelper","create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
