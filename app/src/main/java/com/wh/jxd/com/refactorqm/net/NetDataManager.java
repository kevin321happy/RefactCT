package com.wh.jxd.com.refactorqm.net;

import android.content.Context;

import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.model.BaseModel;
import com.wh.jxd.com.refactorqm.model.UpLoadLocationBean;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.service.ApiService;
import com.wh.jxd.com.refactorqm.utils.CommonUtils;
import com.wh.jxd.com.refactorqm.utils.Md5Utils;
import com.wh.jxd.com.refactorqm.utils.NetUtils;

import java.util.HashMap;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by kevin321vip on 2017/9/29.
 * 网络数据获取的管理类
 */

public class NetDataManager<T extends BaseModel> {

    private ApiService mService;
    private Context mContext;

    /**
     * 泛型设置获取Service对象
     *
     * @param
     */
    public NetDataManager() {
        mService = RetrofitHelper.getInstance().getService();
    }

    /**
     * 给返回结果去掉状态码等属性,
     * 如果是查询出错,则返回状态码对应的描述给用户
     *
     * @param observable
     * @return
     */
    public Observable<T> filterStatus(Observable observable) {
        return observable.map(new ResultFilter<T>());
    }

    /**
     * 过滤数据去掉外壳
     *
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
     * 用户登陆
     */
    public Observable<UserInfo> login(String phone, String password) {
        String psw= Md5Utils.encodeBy32BitMD5(password);
        //开始登陆
        String timestamp = CommonUtils.getCurrentTimestamp();
        HashMap<String, String> sign = new HashMap<>();
        sign.put("phone", phone);
        sign.put("timestamp", timestamp);
        sign.put("password", psw);
        String[] signData = NetUtils.getSignData(AppcationEx.getInstance(), sign);
        Observable<HttpBean<UserInfo>> loginObserable = mService.login(phone, psw, timestamp, signData[1], signData[0]);
        return (Observable<UserInfo>) filterStatus(loginObserable);
    }


    /**
     * 启动App的时候请求定位
     */
    public void uploadLoaction(String userid, String qm_token, String timestemp, String companyId, String srt, String sign, String lng, String lat) {
        Observable<UpLoadLocationBean> beanObservable = ((ApiService) mService).uploadLoaction(userid, qm_token, timestemp, companyId, srt, sign, lng, lat);
    }
}
