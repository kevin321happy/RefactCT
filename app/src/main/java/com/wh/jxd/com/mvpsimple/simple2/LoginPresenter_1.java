package com.wh.jxd.com.mvpsimple.simple2;

import com.wh.jxd.com.mvpsimple.simple2.base.BasePresenter_1;

/**
 * Created by kevin321vip on 2017/9/14.
 */

public class LoginPresenter_1 extends BasePresenter_1<LoginView_1> {
    public void login(String username, String password) {

        if ("xx".equals(username) && "123".equals(password)) {
            if (getView() != null) {
                getView().onLoginSuccess("success");
            }
        }

    }
}
