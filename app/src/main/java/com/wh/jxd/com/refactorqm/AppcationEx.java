package com.wh.jxd.com.refactorqm;

import android.Manifest;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.service.InitializeService;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by kevin321vip on 2017/9/27.
 * 使用MVP+retrofit2模式重构裘马草堂
 */

public class AppcationEx extends Application {
    private static AppcationEx instance = null;
    private UserInfo userInfo;

    /**
     * 单例获取实例
     *
     * @return
     */
    public static AppcationEx getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);
        initFresco();
    }

    //初始化fresco
    private void initFresco() {
        String[] Permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        boolean hasPermissions = EasyPermissions.hasPermissions(this, Permissions);
        File filesDir = this.getFilesDir();
        String path = filesDir.toString();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(this)
                                .setBaseDirectoryPath(new File(path))
                                .build())
                .setDownsampleEnabled(true)
                .build();
        if (hasPermissions) {
            Fresco.initialize(this, build);
        } else {
            // 没有权限，暂时使用默认配置。
            ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                    .setDownsampleEnabled(true)
                    .build();
            Fresco.initialize(this, config);
        }
    }
    /**
     * 保存用户信息到本地
     * @param userInfo
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("userId", userInfo.getUserid()).apply();
        Gson gson = new Gson();
        String userInfoStr = gson.toJson(userInfo);
        PreferenceUtils.setUserInfoBean(userInfoStr); //将userinfo 序列化到本地
    }

    public UserInfo getUserInfo() {
        if (userInfo != null) {
            return userInfo;
        } else {
            //将userInfo 从文件中读出来
            String userInfoBeanStr = PreferenceUtils.getUserInfoBean();
            if (!TextUtils.isEmpty(userInfoBeanStr)) {
                Gson gson = new Gson();
                return gson.fromJson(PreferenceUtils.getUserInfoBean(), new TypeToken<UserInfo>() {
                }.getType());
            }
            return null;
        }
    }
}
