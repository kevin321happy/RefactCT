package com.wh.jxd.com.refactorqm;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
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
        initFresco();
    }

    //初始化百分比布局
    private void initAutoLayout() {
        AutoLayoutConifg.getInstance().useDeviceSize();
    }

    //初始化fresco
    private void initFresco() {
//        String[] Permissions = new String[]{permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE};
//        boolean hasPermissions = EasyPermissions.hasPermissions(this, Permissions);
//        File filesDir = this.getFilesDir();
//        String path = filesDir.toString();
//        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
//                .setDownsampleEnabled(true)
//                .setMainDiskCacheConfig(
//                        DiskCacheConfig.newBuilder(this)
//                                .setBaseDirectoryPath(new File(path))
//                                .build())
//                .setDownsampleEnabled(true)
//                .build();
//        if (hasPermissions) {
//            Fresco.initialize(this, build);
//        } else {
            // 没有权限，暂时使用默认配置。
            ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                    .setDownsampleEnabled(true)
                    .build();
            Fresco.initialize(this, config);
//        }
    }
}
