package com.wh.jxd.com.mvpsimple.simple2;

import android.content.Context;

import com.wh.jxd.com.mvpsimple.modle.UserLoginBean;
import com.wh.jxd.com.mvpsimple.net.NetDataManager;
import com.wh.jxd.com.mvpsimple.simple2.base.BasePresenter_1;
import com.wh.jxd.com.mvpsimple.utils.Md5Utils;
import com.wh.jxd.com.mvpsimple.utils.NetUtils;
import com.wh.jxd.com.mvpsimple.utils.Utils;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/9/14.
 */

public class LoginPresenter_1 extends BasePresenter_1<LoginView_1> {





    public void login(Context context,String phone, String password) {

        String md5_password = Md5Utils.encodeBy32BitMD5(password);
        String timestamp = Utils.getCurrentTimestamp();
        HashMap<String, String> sign = new HashMap<>();
        sign.put("phone", phone);
        sign.put("timestamp", timestamp);
        sign.put("password", md5_password);
        String[] signData = NetUtils.getSignData(context, sign);

        final LoginView_1 loginView_1 = getView();
        NetDataManager dataManager = new NetDataManager();
        Observable<UserLoginBean> observable = dataManager.login(phone, md5_password, timestamp, signData[0], signData[1]);
//        Observable<SecretModel> secret = dataManager.getSecret();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserLoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        loginView_1.onLoginSuccess("登陆失败了,渣渣");
                    }

                    @Override
                    public void onNext(UserLoginBean UserLoginBean) {
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
