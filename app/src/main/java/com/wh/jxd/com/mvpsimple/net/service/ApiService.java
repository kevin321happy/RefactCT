package com.wh.jxd.com.mvpsimple.net.service;

import com.wh.jxd.com.mvpsimple.modle.SecretModel;
import com.wh.jxd.com.mvpsimple.modle.UserLoginBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kevin321vip on 2017/9/19.
 * 联网管理
 */

public interface ApiService {
    /**
     * 获取Secret
     * @return
     */
    @POST("app/getSecret")
    Observable<SecretModel> getSecret();
    /*
     * 用户登陆
     */
    @FormUrlEncoded
    @POST("user_login")
    Observable<UserLoginBean> login(@Field("phone") String phone, @Field("password") String psw, @Field("timestamp") String timestamp
            , @Field("str") String str, @Field("sign") String sign);
}
