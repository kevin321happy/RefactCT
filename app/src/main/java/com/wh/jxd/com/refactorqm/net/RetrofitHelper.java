package com.wh.jxd.com.refactorqm.net;

import com.google.gson.GsonBuilder;
import com.wh.jxd.com.refactorqm.common.Constance;
import com.wh.jxd.com.refactorqm.net.service.ApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevin321vip on 2017/9/29.
 * Retrofit网络辅助类
 */

public class RetrofitHelper {
    private static RetrofitHelper mRetrofitHelper;
    private GsonConverterFactory mConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());
    private Retrofit mRetrofit;

    /**
     * 单例模式创建对象
     *
     * @return
     */
    public static RetrofitHelper getInstance() {
        if (mRetrofitHelper == null) {
            mRetrofitHelper = new RetrofitHelper();
        }
        return mRetrofitHelper;
    }

    public RetrofitHelper() {
        init();
    }

    /**
     * 初始化Retrofit
     */
    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constance.BASE_URL)
                .addConverterFactory(mConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 获取Service对象
     * @param
     * @return
     */

    public ApiService getService() {
        return mRetrofit.create(ApiService.class);
    }
}
