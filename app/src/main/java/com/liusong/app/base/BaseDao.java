package com.liusong.app.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liusong.app.utils.DbHelper;

/**
 * 对数据库中的表进行操作
 * 1> ContentValues存储的机制,只能存储基本类型的数据;
 * -----------------------------------------
 * ContentValues values=new ContentValues();
 * values.put("key", value);
 * -----------------------------------------
 * Created by liu song on 2017/5/17.
 */

public abstract class BaseDao {
    private DbHelper db;
    private SQLiteDatabase dba; //获取数据库操作员-->可以对数据库进行增删改查操作；

    {
        Log.i("BaseDao","1");
        db = initDbHelper();
        Log.i("BaseDao","2");
    }

    protected abstract DbHelper initDbHelper();

    protected DbHelper getDbHelper() {
        return db;
    }

    //增

    /**
     * 向表中插入数据
     * 利用ContentValue在对应字段插入数据
     *
     * @param table  表名
     * @param values 数据
     */
    protected void insert(@NonNull String table, @NonNull ContentValues values) {
        dba = db.getWritableDatabase();
        dba.insert(table, null, values);
        dba.close();
    }

    //删

    /**
     * 删除表中相应数据
     *
     * @param table         表名
     * @param selection     删除条件 "key=value" or "key=?"
     * @param selectionArgs 条件"key=?"对应参数数组 use args when selection is "key=?"
     */
    protected void delete(@NonNull String table, @Nullable String selection, @Nullable String[] selectionArgs) {
        dba = db.getWritableDatabase();
        dba.delete(table, selection, selectionArgs);
        dba.close();
    }

    /**
     * 删除表
     *
     * @param table
     */
    protected void deleteTable(@NonNull String table) {
        dba = db.getWritableDatabase();
        dba.execSQL("DROP TABLE " + table);
        dba.close();
    }

    /**
     * 清空表中数据
     *
     * @param table
     */
    protected void clearTable(@NonNull String table) {
        dba = db.getWritableDatabase();
        dba.execSQL("DELETE FROM " + table);
        dba.close();
    }

    //改

    /**
     * 修改表数据
     *
     * @param table         表
     * @param values        修改的字段
     * @param selection
     * @param selectionArgs
     */
    protected void update(@NonNull String table, @NonNull ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        dba = db.getWritableDatabase();
        dba.update(table, values, selection, selectionArgs);
        dba.close();
    }

    //查询

    /**
     * 查询表中满足条件的记录
     *
     * @param table
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param orderBy
     * @return
     */
    protected Cursor query(@NonNull String table, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String orderBy) {
        dba = db.getReadableDatabase();
        Cursor cursor = dba.query(table, projection, selection, selectionArgs, null, null, orderBy);
        dba.close();
        return cursor;
    }

    /**
     * 查询表中所有记录
     *
     * @param table
     * @return
     */
    protected Cursor queryAll(@NonNull String table) {
        dba = db.getReadableDatabase();
        Cursor cursor = dba.rawQuery("SELECT * FROM " + table, null);
        dba.close();
        return cursor;
    }

    /**
     * 执行无任何返回信息的SQL语句
     * 如：增删改
     *
     * @param sqlString
     */
    protected void execSQL(@Nullable String sqlString) {
        dba = db.getWritableDatabase();
        dba.execSQL(sqlString);
        dba.close();
    }
}
