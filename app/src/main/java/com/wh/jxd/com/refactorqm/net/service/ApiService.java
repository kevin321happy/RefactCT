package com.wh.jxd.com.refactorqm.net.service;

import com.wh.jxd.com.refactorqm.common.RequestCons;
import com.wh.jxd.com.refactorqm.model.UpLoadLocationBean;

import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kevin321vip on 2017/9/29.
 * 接口的
 */

public interface ApiService extends BaseService{

    /**
     * 上报定位信息
     */
    @POST(RequestCons.USER_USERLOGININFO)
     Observable<UpLoadLocationBean> uploadLoaction(@Field("userid") String userid, @Field("qmct_token") String qmct_token, @Field("str") String timestamp
           ,@Field("company_id") String company_id , @Field("str") String str, @Field("sign") String sign,@Field("lng") String lin,@Field("lat") String lat);


}
