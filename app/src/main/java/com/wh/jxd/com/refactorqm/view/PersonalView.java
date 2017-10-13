package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.UserInfo;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public interface PersonalView extends BaseView {
    /**
     * 获得用户信息成功
     * @param userInfo
     */
    void getUserInfoSuccess(UserInfo userInfo);
}
