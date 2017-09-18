package com.wh.jxd.com.mvpsimple.simple1;

import com.wh.jxd.com.mvpsimple.simple1.base.BasePresenter;

/**
 * Created by kevin321vip on 2017/9/14.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginView mLoginView;



    public void login(String username, String password) {
        mLoginView = (LoginView) getView();
        if ("xx".equals(username) && "123".equals(password)) {
            if (mLoginView != null) {
                mLoginView.onLoginSuccess("success");
            }
        }

    }
}
