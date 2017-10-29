package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.UserInfo;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public interface LoginView extends BaseView {
    /**
     * 检验失败
     * @param info
     */
    void onCheckFail(String info);
    /**
     * 登陆失败
     */
    void onLoginFail(String erro);

    /**
     * 登陆成功
     * @param userInfo
     */
    void onLoginSucesss(UserInfo userInfo);

}
