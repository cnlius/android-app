package com.liusong.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 弹toast提示
 * Created by liu song on 2017/5/10.
 */

public class ToastUtils {

    private static final boolean isShowToast = true;

    /**
     * 显示toast提示
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (isShowToast) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
