package com.liusong.app.ui.db;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.base.BaseActivity;
import com.liusong.app.dao.AppTempDao;
import com.liusong.app.databinding.ActivitySqliteBinding;

/**
 * Created by liu song on 2017/5/17.
 */

public class SQLiteActivity extends BaseActivity implements View.OnClickListener {
    private ActivitySqliteBinding mBinding;
    private AppTempDao appTempDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sqlite);
        initAppDb();
    }

    /**
     * 初始化app.db数据库
     */
    private void initAppDb() {
        appTempDao = new AppTempDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_temp: //创建temp表
                appTempDao.createTempTable();
                break;
            case R.id.btn_insert_temp: //想temp表中插入两条数据
                appTempDao.insertDefaultData2Table();
                showToast("向temp表中插入数据");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭数据库连接
        if (appTempDao.getDba().isOpen()) {
            appTempDao.getDba().close();
        }
    }
}
