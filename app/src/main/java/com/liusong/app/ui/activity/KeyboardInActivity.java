package com.liusong.app.ui.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityKeyboardInBinding;
import com.liusong.app.utils.KeyboardUtils;

/**
 * Created by liu song on 2017/3/29.
 */

public class KeyboardInActivity extends AppCompatActivity {
    public static final int STATUS_SHOW = 1;
    public static final int STATUS_HIDE = -1;
    private ActivityKeyboardInBinding mBinding;
    private int keyboardStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_keyboard_in);
        initFlag();
        initListener();
    }

    private void initFlag() {
        //软键盘初始状态时隐藏
        keyboardStatus = STATUS_HIDE;
    }

    private void initListener() {
        initKeyboardListener();
    }

    private void initKeyboardListener() {
        DisplayMetrics dispalyMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dispalyMetrics);
        //记录屏幕高度的1/4
        final int tempHeight = dispalyMetrics.heightPixels / 4;

        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Rect rect = new Rect();
                //获取到程序显示的区域，包括标题栏，但不包括状态栏.
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                Log.i("LOG_CAT", "bottom=" + bottom + ",rect.bottom=" + rect.bottom + ",tempHeight=" + tempHeight);
                Log.i("LOG_CAT", "------------------------");
                //华为手机有虚拟键盘时，bottom-rect.bottom>0,所以这里判断键盘隐藏时bottom-rect.bottom<tempHeight
                if (bottom != 0 && bottom - rect.bottom < tempHeight && keyboardStatus != STATUS_HIDE) {
                    keyboardStatus = STATUS_HIDE;
                    Log.d("LOG_CAT", "收起软键盘");
                    Toast.makeText(KeyboardInActivity.this, "收起软键盘", Toast.LENGTH_SHORT).show();
                } else if (bottom != 0 && bottom - rect.bottom > tempHeight && keyboardStatus != STATUS_SHOW) {
                    keyboardStatus = STATUS_SHOW;
                    Log.d("LOG_CAT", "弹出软键盘");
                    Toast.makeText(KeyboardInActivity.this, "弹出软键盘", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //onTouchEvent中处理，在组件很多的界面,点击除这个EditText意外的控件，键盘就不会隐藏.
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, event)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.onTouchEvent(event);
    }*/

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyboardUtils.isShouldHideKeyboard(v, event)) {
                KeyboardUtils.hideKeyboard(this, v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
