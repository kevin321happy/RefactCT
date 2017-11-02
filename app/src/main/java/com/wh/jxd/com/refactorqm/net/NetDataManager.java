package com.wh.jxd.com.refactorqm.net;

import android.content.Context;

import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.model.BaseModel;
import com.wh.jxd.com.refactorqm.model.ChapterInfo;
import com.wh.jxd.com.refactorqm.model.ChapterListModel;
import com.wh.jxd.com.refactorqm.model.CourseDetailModel;
import com.wh.jxd.com.refactorqm.model.EnterpriseDataModel;
import com.wh.jxd.com.refactorqm.model.HomeInfo;
import com.wh.jxd.com.refactorqm.model.CommonDataModel;
import com.wh.jxd.com.refactorqm.model.UpLoadLocationBean;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.service.ApiService;
import com.wh.jxd.com.refactorqm.utils.CommonUtils;
import com.wh.jxd.com.refactorqm.utils.Md5Utils;
import com.wh.jxd.com.refactorqm.utils.NetUtils;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String psw = Md5Utils.encodeBy32BitMD5(password);
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

    /**
     * 获取首页信息
     */
    public Observable<HomeInfo> getHomeData() {
        Observable<HttpBean<HomeInfo>> homeData = mService.getHomeData();
        return (Observable<HomeInfo>) filterStatus(homeData);
    }

    /**
     * 获取个人用户信息
     */
    public Observable<UserInfo> getUserInfo(String userid, String qmct_token) {
        String timestamp = CommonUtils.getCurrentTimestamp();
        HashMap<String, String> sign = new HashMap<>();
        sign.put("userid", userid);
        sign.put("qmct_token", qmct_token);
        sign.put("timestamp", timestamp);
        String[] signData = NetUtils.getSignData(AppcationEx.getInstance(), sign);
        Observable<HttpBean<UserInfo>> userInfo = mService.getUserInfo(userid, qmct_token, timestamp, signData[1], signData[0]);
        return (Observable<UserInfo>) filterStatus(userInfo);
    }

    /**
     * 修改用户信息
     */
    public Observable<CommonDataModel> upDataUserInfo(String key, String value) {
        Map<String, String> baseArgumentMap = getBaseArgumentMap();
        baseArgumentMap.put(key, value);
        Observable<CommonDataModel> upDataUserInfoObservable = mService.upDataUserInfo(baseArgumentMap);
        return upDataUserInfoObservable;
    }

    /**
     * 获取手机验证码
     */
    public Observable<CommonDataModel> getVerificationCode(String key, String value, String type) {
        String timestamp = CommonUtils.getCurrentTimestamp();
        HashMap<String, String> sign = new HashMap<>();
        sign.put("timestamp", timestamp);
        sign.put(key, value);
        String[] signData = NetUtils.getSignData(AppcationEx.getInstance(), sign);
        // sign.put("qmct_token", PreferenceUtils.getQM_Token());
        sign.put("type", type);
        sign.put("str", signData[1]);
        sign.put("sign", signData[0]);
        Observable<CommonDataModel> upDataUserInfoObservable = mService.getVerificationCode(sign);
        return upDataUserInfoObservable;
    }

    /**
     * 更换新的手机号
     */
    public Observable<CommonDataModel> updataPhoneNum(String key1, String value1, String key2, String value2) {
        Map<String, String> baseArgumentMap = getBaseArgumentMap();
        baseArgumentMap.put(key1, value1);
        baseArgumentMap.put(key2, value2);
        Observable<CommonDataModel> commonDataModelObservable = mService.changePhoneNum(baseArgumentMap);
        return commonDataModelObservable;
    }

    /**
     * 获取企业首页的信息
     */
    public Observable<EnterpriseDataModel> getEenterprise(String key, String value) {
        Map<String, String> baseArgumentMap = getBaseArgumentMap();
        baseArgumentMap.put(key, value);
        Observable<HttpBean<EnterpriseDataModel>> enterpriseData = mService.getEnterpriseData(baseArgumentMap);
        return (Observable<EnterpriseDataModel>) filterStatus(enterpriseData);
    }

    /**
     * 获取课程详情
     */
    public Observable<CourseDetailModel> getCourseDetail(String key, String value) {
        Map<String, String> baseArgumentMap = getBaseArgumentMap();
        baseArgumentMap.put(key, value);
        Observable<HttpBean<CourseDetailModel>> modelObservable = mService.getCourseDetail(baseArgumentMap);
        return (Observable<CourseDetailModel>) filterStatus(modelObservable);
    }
    /**
     * 获取课程章节信息
     */
    public Observable<ChapterListModel> getChapterLists(String key, String value) {
        Map<String, String> baseArgumentMap = getBaseArgumentMap();
        baseArgumentMap.put(key, value);
        Observable<ChapterListModel> chapterList = mService.getChapterList(baseArgumentMap);
        return chapterList;
    }

    /**
     * 获取基本参数的Map
     */
    private Map<String, String> getBaseArgumentMap() {
        String timestamp = CommonUtils.getCurrentTimestamp();
        HashMap<String, String> sign = new HashMap<>();
        sign.put("userid", PreferenceUtils.getUserId());
        sign.put("timestamp", timestamp);
        sign.put("qmct_token", PreferenceUtils.getQM_Token());
        String[] signData = NetUtils.getSignData(AppcationEx.getInstance(), sign);
        sign.put("str", signData[1]);
        sign.put("sign", signData[0]);
        return sign;
    }
}
