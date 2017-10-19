package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import android.content.Context;

import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.utils.DataCleanManager;
import com.wh.jxd.com.refactorqm.view.SystemSettingView;

/**
 * Created by kevin321vip on 2017/10/18.
 */

public class SystemSettingPresenter extends BasePersenterImpl<SystemSettingView> {

    private SystemSettingView mSystemSettingView;

    public SystemSettingPresenter() {
        mSystemSettingView = getView();
    }

    /**
     * 清楚缓存
     */
    public void clearCache(Context context) {
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(context);
            if (!"0.0Byte".equals(totalCacheSize)) {
                DataCleanManager.clearAllCache(context);
                if (mSystemSettingView==null){
                    mSystemSettingView=getView();
                }
                mSystemSettingView.onClearSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
