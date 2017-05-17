package com.liusong.library.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * 对数据库中的表进行操作
 * 1> ContentValues存储的机制,只能存储基本类型的数据;
 * -----------------------------------------
 * ContentValues values=new ContentValues();
 * values.put("key", value);
 * -----------------------------------------
 * 2> 特别指查询操作完成之后需要通过getDba().isOpen()判断是否打开dba,并关闭dba和cursor；
 * Created by liu song on 2017/5/17.
 */

public abstract class BaseDao {
    private DbHelper db;
    private SQLiteDatabase dba; //获取数据库操作员-->可以对数据库进行增删改查操作；

    protected DbHelper getDbHelper() {
        return db;
    }

    protected void setDbHelper(DbHelper db) {
        this.db = db;
    }

    public SQLiteDatabase getDba() {
        return dba;
    }

    /**
     * 判断表是否存在
     *
     * @param table
     * @return
     */
    protected boolean isHasTable(String table) {
        dba = db.getReadableDatabase();
        Cursor cursor = dba.rawQuery("select count(*) from sqlite_master where type='table' and name = '" + table + "'", null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    return true;
                }
            }
            cursor.close();
        }
        dba.close();
        return false;
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
