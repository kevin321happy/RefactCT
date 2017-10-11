package com.wh.jxd.com.refactorqm.persenter.persenterImpl;

import android.text.TextUtils;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.persenter.LoginPersenter;
import com.wh.jxd.com.refactorqm.view.LoginView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public class LoginPersenterImpl extends BasePersenterImpl<LoginView> implements LoginPersenter {
    private LoginView mLoginView;

    public LoginPersenterImpl() {
    }

    @Override
    public void login(String phone, String passward) {
        if (mLoginView == null) {
            mLoginView = getView();
        }
        if (TextUtils.isEmpty(phone)) {
            // 账号不能为空
            mLoginView.onCheckFail("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(passward)) {
            // 密码不能为空
            mLoginView.onCheckFail("密码不能为空");
            return;
        }
        NetDataManager<UserInfo> manager = new NetDataManager();
        Observable<UserInfo> login = manager.login(phone, passward);
        login.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        KLog.i("开始请求");
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        KLog.i("请求完成了！！！！");


                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        KLog.i("请求成功了！！！！" + userInfo.toString());
                        mLoginView.onLoginSucesss(userInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        KLog.i("请求失败了！！！！+" + e.toString());
                        mLoginView.onLoginFail(e.toString());
                    }
                });
    }
}
