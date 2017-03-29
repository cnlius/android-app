package com.liusong.app.utils;

import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘相关的工具类
 * Created by liusong on 2017/3/29.
 */

public class KeyboardUtils {

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v     当前焦点所在的view
     * @param event 动作事件
     * @return true:应该隐藏软键盘 false不应该被隐藏
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取EditText的当前的绝对坐标位置
            v.getLocationInWindow(leftTop);
            //EditText的坐标获取
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            //判断点击点是否在EditText上
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据IBinder用InputMethodManager，隐藏软键盘
     * @param context
     * @param token
     */
    public static void hideKeyboard(Context context,IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
