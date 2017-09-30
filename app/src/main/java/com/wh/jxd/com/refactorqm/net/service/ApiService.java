package com.wh.jxd.com.refactorqm.net.service;

import com.wh.jxd.com.refactorqm.common.RequestCons;
import com.wh.jxd.com.refactorqm.model.UpLoadLocationBean;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.HttpBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kevin321vip on 2017/9/29.
 * 接口的
 */

public interface ApiService {
    /**
     * 用户登陆
     */
// .params(getString(R.string.手机号), phone)
//            .params(getString(R.string.密码), password)
//            .params(getString(R.string.时间戳), timestamp)
//            .params(getString(R.string.签名str), signData[1])
//            .params(getString(R.string.签名), signData[0])
    @FormUrlEncoded
    @POST(RequestCons.USER_LOGIN)
    Observable<HttpBean<UserInfo>> login(@Field("phone") String phone, @Field("password") String password, @Field("timestamp") String timestamp
            , @Field("str") String str, @Field("sign") String sign);

    /**
     * 上报定位信息
     */
    @POST(RequestCons.USER_USERLOGININFO)
    Observable<UpLoadLocationBean> uploadLoaction(@Field("userid") String userid, @Field("qmct_token") String qmct_token, @Field("str") String timestamp
            , @Field("company_id") String company_id, @Field("str") String str, @Field("sign") String sign, @Field("lng") String lin, @Field("lat") String lat);


}
