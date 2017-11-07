package com.wh.jxd.com.refactorqm.service;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by kevin321vip on 2017/11/4.
 * 应用程序初始化的服务
 */

public  class InitializeService extends IntentService {
    private static Context mContext;
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.wh.jxd.com.refactorqm.service.action.INIT";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        mContext = context;
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    /**
     * 应用启动初始化
     */
    private void performInit() {
        initAutoLayout();
//        initFresco();
    }

    //初始化百分比布局
    private void initAutoLayout() {
        AutoLayoutConifg.getInstance().useDeviceSize();
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
}
