package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.UpDataUserInfo;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.presenter.PersonalPresent;
import com.wh.jxd.com.refactorqm.view.PersonalView;

/**
 * Created by kevin321vip on 2017/10/12.
 */
public class PersonalPresenterImpl extends BasePersenterImpl<PersonalView> implements PersonalPresent {
    private PersonalView mPersonalView;
    private NetDataManager<UpDataUserInfo> mInfoNetDataManager;
    /**
     * 获取用户信息
     *
     * @return
     */
    public void getUserInfo() {
        if (mPersonalView == null) {
            mPersonalView = getView();
        }
        UserInfo userInfo = AppcationEx.getInstance().getUserInfo();
        if (userInfo != null) {
            mPersonalView.getUserInfoSuccess(userInfo);
        }
    }
    /**
     * 更新用户信息
     * @param key
     * @param value
     */
    public void upDataInfo(String key, String value) {
        if (mInfoNetDataManager == null) {
            mInfoNetDataManager = new NetDataManager<>();
        }
    }
}
