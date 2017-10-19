package com.wh.jxd.com.refactorqm.net.service;

import com.wh.jxd.com.refactorqm.common.RequestCons;
import com.wh.jxd.com.refactorqm.model.HomeInfo;
import com.wh.jxd.com.refactorqm.model.CommonDataModel;
import com.wh.jxd.com.refactorqm.model.UpLoadLocationBean;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.HttpBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
    @FormUrlEncoded
    @POST(RequestCons.USER_LOGIN)
    Observable<HttpBean<UserInfo>> login(@Field("phone") String phone, @Field("password") String password, @Field("timestamp") String timestamp
            , @Field("str") String str, @Field("sign") String sign);

    /**
     * 上报定位信息
     */
    @POST(RequestCons.USER_USERLOGININFO)
    Observable<UpLoadLocationBean> uploadLoaction(@Field("userid") String userid, @Field("qmct_token") String qmct_token, @Field("timestamp") String timestamp
            , @Field("company_id") String company_id, @Field("str") String str, @Field("sign") String sign, @Field("lng") String lin, @Field("lat") String lat);

    /**
     * 获取企业信息
     */
    @POST(RequestCons.HOME_INFO)
    Observable<HttpBean<HomeInfo>> getHomeData();

    /**
     * 获取个人信息
     */
    @FormUrlEncoded
    @POST(RequestCons.USER_USERINFO)
    Observable<HttpBean<UserInfo>> getUserInfo(@Field("userid") String userid, @Field("qmct_token") String qmct_token, @Field("timestamp") String timestamp, @Field("str") String str, @Field("sign") String sign);

    /**
     * 修改个人信息
     */
    @FormUrlEncoded
    @POST(RequestCons.USER_EDITUSERINFO)
    Observable<CommonDataModel> upDataUserInfo(@FieldMap Map<String, String> options);

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST(RequestCons.GET_PHONE_CODE)
    Observable<CommonDataModel> getVerificationCode(@FieldMap Map<String, String> options);

    /**
     * 更换新的手机号
     */
    @FormUrlEncoded
    @POST(RequestCons.CHANGE_PHONE)
    Observable<CommonDataModel> changePhoneNum(@FieldMap Map<String, String> options);
}
