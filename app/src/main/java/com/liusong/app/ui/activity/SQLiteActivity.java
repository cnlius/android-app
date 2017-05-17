package com.liusong.app.ui.activity;

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

public class SQLiteActivity extends BaseActivity implements View.OnClickListener{
    private ActivitySqliteBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_sqlite);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create_temp:
                createTempTable();
                break;
        }
    }

    private void createTempTable() {
        AppTempDao appTempDao = new AppTempDao(this);
        appTempDao.createTempTable();
        appTempDao.insertDefaultData2Table();
    }
}
