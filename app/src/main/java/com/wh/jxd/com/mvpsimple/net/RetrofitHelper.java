package com.wh.jxd.com.mvpsimple.net;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.wh.jxd.com.mvpsimple.common.Constance;
import com.wh.jxd.com.mvpsimple.net.service.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kevin321vip on 2017/9/19.
 */

public class RetrofitHelper {
    private static RetrofitHelper serviceHelper;
    private Context context;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private GsonConverterFactory mGsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());
    private Retrofit mRetrofit;

    public static RetrofitHelper getServiceHelper() {
        if (serviceHelper == null) {
            serviceHelper = new RetrofitHelper();
        }
        return serviceHelper;
    }

    public RetrofitHelper() {
        init();
    }

    public RetrofitHelper(Context context) {
        this.context = context;

    }

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constance.BASE_URL)
                .addConverterFactory(mGsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public ApiService getService() {
        return mRetrofit.create(ApiService.class);
    }

}
