package com.ls.test.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * test.db数据库
 *
 * 1> 每个数据库中个都会有一张temp临时表，表中有两条记录；
 * Created by liu song on 2017/5/16.
 */

public class TestDbHelper extends SQLiteOpenHelper {

    //创建temp表的sql语句
    private static final String CREATE_TEMP_TABLE_SQL = "create table temp(id integer primary key autoincrement,name varchar(256),phone text)";

    /**
     * 创建名为工程名.db的数据库
     * @param context
     */
    public TestDbHelper(Context context) {
        this(context, "test.db", null, 1);
    }

    /**
     * 动态创建一个数据库
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public TestDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据表，并增加两条记录
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEMP_TABLE_SQL);

        ContentValues contentValues=new ContentValues();
        contentValues.put("name", "zhangsan");
        contentValues.put("phone", "15370854698");
        db.insert("temp", null, contentValues);

        contentValues.clear();
        contentValues.put("name", "lisi");
        contentValues.put("phone", "18811223699");
        db.insert("temp", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
