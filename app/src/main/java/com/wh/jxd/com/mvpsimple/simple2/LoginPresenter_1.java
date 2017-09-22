package com.wh.jxd.com.mvpsimple.simple2;

import com.wh.jxd.com.mvpsimple.modle.SecretModel;
import com.wh.jxd.com.mvpsimple.net.RetrofitHelper;
import com.wh.jxd.com.mvpsimple.simple2.base.BasePresenter_1;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/9/14.
 */

public class LoginPresenter_1 extends BasePresenter_1<LoginView_1> {


    public void login(String username, String password) {
        final LoginView_1 loginView_1 = getView();
        Observable<SecretModel> observable = RetrofitHelper.getServiceHelper().getService().getSecret();
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SecretModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        loginView_1.onLoginSuccess("登陆失败了,渣渣");
                    }

                    @Override
                    public void onNext(SecretModel secretModel) {
                        loginView_1.onLoginSuccess("登陆成功了,厉害了");

                    }
                });

//        if ("xx".equals(username) && "123".equals(password)) {
//            if (getView() != null) {
//                getView().onLoginSuccess("success");
//            }
//        }

    }
}
