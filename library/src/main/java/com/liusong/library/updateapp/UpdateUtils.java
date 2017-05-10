package com.liusong.library.updateapp;

import android.text.TextUtils;

/**
 * 更新apk工具类
 * Created by liu song on 2017/5/10.
 */

public class UpdateUtils {

    /**
     * 检查更新根据版本名
     *
     * @param curVersion 当前版本
     * @param newVersion 新版本
     * @return 0:相等 -1:需要升级 1不需要更新
     */
    public static int checkUpdateByVersionName(String curVersion, String newVersion) {
        if (TextUtils.equals(curVersion, newVersion)) {
            return 0;
        }
        String[] curArray = curVersion.split("\\.");
        String[] newArray = newVersion.split("\\.");
        int minLen = Math.min(curArray.length, newArray.length);

        for(int i=0;i<minLen;i++){
            int result=Integer.parseInt(curArray[i]) - Integer.parseInt(newArray[i]);
            if(result!=0){
                return result;
            }
        }
        return 0;
    }
}
