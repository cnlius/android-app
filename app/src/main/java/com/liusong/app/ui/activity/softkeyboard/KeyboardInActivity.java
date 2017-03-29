package com.liusong.app.ui.activity.softkeyboard;

import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityKeyboardInBinding;

/**
 * Created by liu song on 2017/3/29.
 */

public class KeyboardInActivity extends AppCompatActivity {
    private ActivityKeyboardInBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_keyboard_in);
        initListener();
    }

    private void initListener() {
        initKeyboardListener();
    }

    private void initKeyboardListener() {

        DisplayMetrics dispalyMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
        final int tempHeight=dispalyMetrics.heightPixels/4;

        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Rect rect=new Rect();
                //获取到程序显示的区域，包括标题栏，但不包括状态栏.
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                Log.i("LOG_CAT","bottom="+bottom+
                                ",oldBottom="+oldBottom+
                                ",rect.bottom="+rect.bottom+
                                ",tempHeight="+tempHeight);
                Log.i("LOG_CAT","------------------------");
                if (bottom != 0 && oldBottom != 0 && bottom - rect.bottom < tempHeight) {
                    if(bottom-rect.bottom<=0){
                        Log.d("LOG_CAT", "键盘消失");
                    }else {
                        Log.d("LOG_CAT", "虚拟键盘没消失");
                    }
                } else if (bottom != 0 && oldBottom != 0 && bottom - rect.bottom > tempHeight) {
                    Log.d("LOG_CAT", "弹出键盘");
                }
            }
        });
    }
}
