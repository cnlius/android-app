package com.liusong.library.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * 提醒已经申请过
 * Created by liusong on 2017/10/16.
 */

public class RemindedDao extends BaseDao {
    private static final String TABLE_NAME = "reminded";
    private static final String CREATE_TABLE_SQL = "create table if not exists " +
            TABLE_NAME + "(id integer primary key autoincrement,productId varchar(256),userId varchar(256))";

    public RemindedDao(Context context) {
        super(context, CREATE_TABLE_SQL);
    }

    public boolean isReminded(String productId, String userId) {
        try {
            Cursor cursor = query(TABLE_NAME, new String[]{"productId", "userId"}, "productId=? and userId=?", new String[]{productId, userId});
            if (cursor.moveToNext()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertProduct(String productId, String userId) {
        try {
            ContentValues values = new ContentValues();
            values.put("productId", productId);
            values.put("userId", userId);
            insert(TABLE_NAME, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
