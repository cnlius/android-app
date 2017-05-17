package com.liusong.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.liusong.app.base.BaseDao;
import com.liusong.app.utils.DbHelper;
import com.liusong.library.utils.ToastUtils;

/**
 * app.db数据库中操作temp表
 * 注意：
 * 1> 必须先创建表才能操作;
 * 2> 创建表时，如果不想加判断表是否存在，可以重写DbHelper的onCreate方法，在其中创建表；
 * Created by liu song on 2017/5/17.
 */

public class AppTempDao extends BaseDao {
    private Context context;

    public AppTempDao(Context context) {
        this.context = context;
        //初始化数据库
        setDbHelper(new DbHelper(context, "app.db", 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                super.onCreate(db);
                execSQL("create table temp(id integer primary key autoincrement,name varchar(256),phone varchar(256))");
            }
        });
    }


    /**
     * 创建一张temp表
     */
    public void createTempTable() {
        if (isHasTable("temp")) {
            ToastUtils.showToast(context,"temp表已经存在");
        }else{
            execSQL("create table temp(id integer primary key autoincrement,name varchar(256),phone varchar(256))");
            ToastUtils.showToast(context,"temp表创建成功");
        }
    }

    /**
     * 插入点数据在temp表中
     */
    public void insertDefaultData2Table() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "zhangsan");
        contentValues.put("phone", "15370854698");
        insert("temp", contentValues);

        contentValues.clear();
        contentValues.put("name", "lisi");
        contentValues.put("phone", "18811223699");
        insert("temp", contentValues);
    }
}
