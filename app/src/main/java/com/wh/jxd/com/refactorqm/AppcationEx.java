package com.wh.jxd.com.refactorqm;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by kevin321vip on 2017/9/27.
 * 使用MVP+retrofit2模式重构裘马草堂
 */

public class AppcationEx extends Application {
    private static AppcationEx instance=null;
    /**
     * 单例获取实例
     * @return
     */
    public static AppcationEx getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initAutoLayout();
    }

    //初始化百分比布局
    private void initAutoLayout() {
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
