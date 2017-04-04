package com.liusong.library.okhttp.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liusong.library.okhttp.CommonOkHttpClient;
import com.liusong.library.okhttp.listener.DisposeDataHandle;
import com.liusong.library.okhttp.listener.DisposeDataListener;
import com.liusong.library.okhttp.request.CommonRequest;

/**
 * Created by liusong on 2017/4/4.
 */

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testOkhttp();
    }

    private void testOkhttp() {
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest("",null),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        }));
    }
}
