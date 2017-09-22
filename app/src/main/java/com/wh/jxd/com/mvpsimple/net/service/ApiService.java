package com.wh.jxd.com.mvpsimple.net.service;

import com.wh.jxd.com.mvpsimple.modle.SecretModel;

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
}
