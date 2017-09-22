package com.wh.jxd.com.mvpsimple.net;

import com.wh.jxd.com.mvpsimple.modle.SecretModel;
import com.wh.jxd.com.mvpsimple.modle.UserLoginBean;
import com.wh.jxd.com.mvpsimple.net.service.ApiService;

import rx.Observable;

/**
 * Created by kevin321vip on 2017/9/22.
 * 获取网络数据的管理类
 */

public class NetDataManager {
    private ApiService mApiService;

    public NetDataManager() {
        mApiService = RetrofitHelper.getServiceHelper().getService();
    }

    /**
     * 获取Secret数据
     */
    public Observable<SecretModel> getSecret() {
        return mApiService.getSecret();
    }

    /**
     * 登陆
     */
    public Observable<UserLoginBean> login(String phone, String psw, String timestamp, String str, String sign) {
        return mApiService.login(phone, psw, timestamp, str, sign);
    }
}
