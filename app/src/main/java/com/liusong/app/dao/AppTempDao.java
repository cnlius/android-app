package com.liusong.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.liusong.app.base.BaseDao;
import com.liusong.app.utils.DbHelper;

/**
 * app.db数据库中操作temp表
 * 注意：必须先创建表才能操作
 * Created by liu song on 2017/5/17.
 */

public class AppTempDao extends BaseDao {
    private Context context;

    public AppTempDao(Context context) {
        this.context = context;
    }

    @Override
    protected DbHelper initDbHelper() {
        Log.i("AppTempDao","initDbHelper");
        return new DbHelper(context, "app.db", 1);
    }

    /**
     * 创建一张temp表
     */
    public void createTempTable(){
        execSQL("create table temp(id integer primary key autoincrement,name varchar(256),phone text");
    }

    /**
     * 插入点数据在temp表中
     */
    public void insertDefaultData2Table(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name", "zhangsan");
        contentValues.put("phone", "15370854698");
        insert("temp",contentValues);

        contentValues.clear();
        contentValues.put("name", "lisi");
        contentValues.put("phone", "18811223699");
        insert("temp",contentValues);
    }
}
