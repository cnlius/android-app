package com.ls.test.ipc;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ls.test.R;
import com.ls.test.databinding.ActivityIpcTargetBinding;

/**
 * Created by liu song on 2017/4/27.
 */

public class IPCTargetActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityIpcTargetBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ipc_target);
        receiveData();
    }

    /**
     * 获得其他地方传递过来的数据
     */
    private void receiveData() {
        if (getIntent().getData() != null) {
            //获得Host，也就是info://后面的内容
            String host = getIntent().getData().getHost();
            Bundle bundle = getIntent().getExtras();
            //其他的应用程序会传递过来一个value值，在该应用程序中需要获得这个值
            String value = bundle.getString("value");
            //将Host和Value组合在一下显示在EditText组件中
            mBinding.tvData.setText(host + ":" + value);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                backApp();
                break;
        }
    }

    private void backApp() {
        Intent intent = new Intent();
        //设置要返回的属性值
        intent.putExtra("result" , "从其它星球返回来了");
        //设置返回码和Intent对象
        setResult(RESULT_OK , intent);
        //关闭Activity
        finish();
    }
}
