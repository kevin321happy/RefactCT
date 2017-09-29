package com.wh.jxd.com.refactorqm.persenter;

import android.content.Context;

import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.view.MainView;

/**
 * Created by kevin321vip on 2017/9/27.
 *  mainActivtiy的Persenter
 */

public class MainPersenter extends BasePersenterImpl<MainView> {

    private final MainView mMainView;

    public MainPersenter() {
        mMainView = getView();
    }
    /**
     * 发送定位信息
     */
    public void sendLocation(Context context) {




    }
}
