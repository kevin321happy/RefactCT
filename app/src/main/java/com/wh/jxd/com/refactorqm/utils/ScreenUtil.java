package com.wh.jxd.com.refactorqm.utils;

import android.content.Context;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public class ScreenUtil {

    /**
     * 获得状态栏的高度
     * @param context
     * @return px
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
