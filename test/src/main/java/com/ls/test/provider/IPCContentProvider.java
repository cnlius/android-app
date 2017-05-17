package com.ls.test.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ls.test.db.TestDbHelper;

/**
 * IPC：ContentProvider共享数据
 * Created by liu song on 2017/5/16.
 */

public class IPCContentProvider extends ContentProvider {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    //test.db中的temp表
    private static final String tableName = "temp";
    private TestDbHelper testDbHelper;
    private static UriMatcher uriMatcher; //文本过滤器,帮助我们过滤，分辨出查询者想要查询哪个数据表。

    static {
        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.ls.test.provider", tableName, TYPE_ONE); //uri,表,匹配码
        //“#”为数据的通配符，“*”为所有文本的通配符，匹配码为第三个参数。eg:content://cn.wuxiaocheng.people/age/23;
        uriMatcher.addURI("com.ls.test.provider", tableName + "/#", TYPE_TWO);
    }

    public IPCContentProvider() {
    }

    @Override
    public boolean onCreate() {
        testDbHelper=new TestDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor=null;
        switch (uriMatcher.match(uri)) {
            case TYPE_ONE:
                cursor=testDbHelper.getWritableDatabase().query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TYPE_TWO:
                long id= ContentUris.parseId(uri);
                selection="id="+id+" and "+selection;
                cursor=testDbHelper.getWritableDatabase().query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String typeString="";
        switch (uriMatcher.match(uri)) {
            case TYPE_ONE:
                typeString="com.ls.test.provider.one";
                break;
            case TYPE_TWO:
                typeString="com.ls.test.provider.two";
                break;
            default:
                break;
        }
        return typeString;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri tempUri=null;
        long rowid= testDbHelper.getWritableDatabase().insert(tableName, null, values);
        switch (uriMatcher.match(uri)) {
            case TYPE_ONE:
                tempUri=ContentUris.withAppendedId(uri, rowid);
                break;
            case TYPE_TWO:
                tempUri=uri;
                break;
            default:
                break;
        }
        return tempUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int result=0;
        switch (uriMatcher.match(uri)) {
            case TYPE_ONE:
                result= testDbHelper.getWritableDatabase().delete(tableName, selection, selectionArgs);
                break;
            case TYPE_TWO:
                long id=ContentUris.parseId(uri);
                selection="id="+id+" and "+selection;
                result= testDbHelper.getWritableDatabase().delete(tableName, selection, selectionArgs);
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowid= 0;
        switch (uriMatcher.match(uri)) {
            case TYPE_ONE:
                rowid= testDbHelper.getWritableDatabase().update(tableName, values, selection, selectionArgs);
                break;
            case TYPE_TWO:
                long id=ContentUris.parseId(uri);
                selection="id="+id+" and "+selection;
                rowid= testDbHelper.getWritableDatabase().update(tableName, values,selection, selectionArgs);
                break;
            default:
                break;
        }
        return rowid;
    }
}
