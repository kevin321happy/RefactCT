package com.wh.jxd.com.refactorqm.net;

import com.wh.jxd.com.refactorqm.net.service.ApiService;
import com.wh.jxd.com.refactorqm.net.service.BaseService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by kevin321vip on 2017/9/29.
 * 网络数据获取的管理类
 */

public class NetDataManager {

    private BaseService mService;

    /**
     * 泛型设置获取Service对象
     *
     * @param clazz
     */
    public NetDataManager(Class<? extends BaseService> clazz) {
        mService = RetrofitHelper.getInstance().getService(clazz);

    }

    /**
     * 给返回结果去掉状态码等属性,
     * 如果是查询出错,则返回状态码对应的描述给用户
     *
     * @param observable
     * @return
     */
    public Observable filterStatus(Observable observable) {
        return observable.map(new ResultFilter());
    }

    /**
     * 过滤数据去掉外壳
     * @param <T>
     */
    public class ResultFilter<T> implements Func1<HttpBean<T>, T> {
        @Override
        public T call(HttpBean<T> tHttpBean) {
            if (!"1".equals(tHttpBean.getStatus())) {
                throw new ApiException(tHttpBean.getInfo());
            } else if ("-99".equals(tHttpBean.getIs_login())) {
                return null;
            }
            return tHttpBean.getData();
        }
    }


    /**
     * 启动App的时候请求定位
     */
    public void uploadLoaction(String userid, String qm_token, String timestemp, String companyId, String srt, String sign, String lng, String lat) {
        ((ApiService) mService).uploadLoaction(userid, qm_token, timestemp, companyId, srt, sign, lng, lat);
    }
}
