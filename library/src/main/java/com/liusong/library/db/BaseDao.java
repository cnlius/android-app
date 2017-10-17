package com.liusong.library.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

public abstract class BaseDao {
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase dba;

    public BaseDao(Context context,String sql) {
        this.context = context;
        dbHelper = new DbHelper(context,sql);
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }

    protected Cursor query(@NonNull String table, String[] projection, String selection, String[] selectionArgs) {
        return query(table, projection, selection, selectionArgs, null);
    }

    protected Cursor query(@NonNull String table, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        dba = dbHelper.getReadableDatabase();
        Cursor cursor = dba.query(table, projection, selection, selectionArgs, null, null, orderBy);
        return cursor;
    }

    protected void insert(@NonNull String table, @NonNull ContentValues values) {
        dba = dbHelper.getWritableDatabase();
        dba.insert(table, null, values);
        dba.close();
    }


}
