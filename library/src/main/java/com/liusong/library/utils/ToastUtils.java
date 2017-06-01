package com.liusong.library.utils;

import android.content.Context;
import android.support.compat.BuildConfig;
import android.util.Config;
import android.widget.Toast;

/**
 * 弹toast提示
 *
 * Created by liu song on 2017/5/10.
 */

public class ToastUtils {

    //debug模式下弹toast
    private static final boolean isDebugToast = BuildConfig.DEBUG;

    /**
     * 显示toast提示
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (isDebugToast) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
