package com.wh.jxd.com.mvpsimple;

import android.app.Application;

/**
 * Created by kevin321vip on 2017/8/28.
 * 应用程序的入口
 */

public class App extends Application {
    private static App instance;

    /**
     * 单例获取实例
     * @return
     */
    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
