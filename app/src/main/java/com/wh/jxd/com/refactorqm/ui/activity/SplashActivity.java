package com.wh.jxd.com.refactorqm.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.base.BaseActivtiy;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by kevin321vip on 2017/11/4.
 */

public class SplashActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //点击安装包进行安装，安装结束后不点击完成，而是点击打开应用，应用启动后，再回到桌面，从桌面点击应用图标会造成反复重启应用的现象。
        startActivity(new Intent(this, MainActivity.class));
    }
}
