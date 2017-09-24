package com.wh.jxd.com.mvpsimple.simple1;

import com.wh.jxd.com.mvpsimple.net.RetrofitHelper;
import com.wh.jxd.com.mvpsimple.simple1.base.BasePresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kevin321vip on 2017/9/14.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginView mLoginView;

    public void login(String username, String password) {
        mLoginView = getView();
        //联网操作
        CompositeSubscription subscription = new CompositeSubscription();
        subscription.add((Subscription) RetrofitHelper.getServiceHelper().getService().getSecret());
//        if ("xx".equals(username) && "123".equals(password)) {
//            if (mLoginView != null) {
//                mLoginView.onLoginSuccess("success");
//            }
//        }

    }
}
