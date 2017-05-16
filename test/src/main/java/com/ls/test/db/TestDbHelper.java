package com.ls.test.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * test.db数据库
 * Created by liu song on 2017/5/16.
 */

public class TestDbHelper extends SQLiteOpenHelper {

    //创建temp表的sql语句
    private static final String CREATE_TEMP_TABLE_SQL = "create table temp(id integer primary key autoincrement,name varchar(256),phone text";

    //创建数据库
    public TestDbHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    //创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEMP_TABLE_SQL);

        //创建表并增加两条记录

        ContentValues contentValues=new ContentValues();
        contentValues.put("name", "zhangsan");
        contentValues.put("phone", "22222222222");
        db.insert("temp", null, contentValues);

        contentValues.clear();
        contentValues.put("name", "lisi");
        contentValues.put("number", "11111111111");
        db.insert("temp", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
