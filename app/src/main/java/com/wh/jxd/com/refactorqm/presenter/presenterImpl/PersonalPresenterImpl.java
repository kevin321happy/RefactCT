package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.BaseModel;
import com.wh.jxd.com.refactorqm.model.UpDataUserInfo;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.presenter.PersonalPresent;
import com.wh.jxd.com.refactorqm.view.PersonalView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/10/12.
 */
public class PersonalPresenterImpl extends BasePersenterImpl<PersonalView> implements PersonalPresent {
    private PersonalView mPersonalView;
    private NetDataManager<BaseModel> mNetDataManager;
    //    private NetDataManager<UpDataUserInfo> mInfoNetDataManager;

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public void getUserInfo() {
        if (mPersonalView == null) {
            mPersonalView = getView();
        }
        UserInfo userInfo = AppcationEx.getInstance().getUserInfo();
        if (userInfo != null) {
            mPersonalView.getUserInfoSuccess(userInfo);
        }
    }

    /**
     * 更新用户信息
     *
     * @param key
     * @param value
     */
    public void upDataInfo(final String key, final String value) {
        if (mNetDataManager == null) {
            mNetDataManager = new NetDataManager<>();
        }
        Observable<UpDataUserInfo> upDataUserInfo = mNetDataManager.upDataUserInfo(key, value);
        upDataUserInfo.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<UpDataUserInfo>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onNext(UpDataUserInfo data) {
                        KLog.i(data.toString());
                        UserInfo userInfo=AppcationEx.getInstance().getUserInfo();
//                        data.toString().
                        if (!data.getSuc().equals("y")) {
                            return;
                        }
                        if (!"1".equals(data.getStatus())){
                            mPersonalView.updataInfoFail("网络失败");
                            return;
                        }
                        if (mPersonalView == null) {
                            return;
                        }
                        if ("member_name".equals(key)) {
                            userInfo.setUser_name(value);
                            mPersonalView.updataNameSuccess(value);
                        } else if ("nickname".equals(key)) {
                            userInfo.setNickname(value);
                            mPersonalView.updataNicknameSuccess(value);
                        } else if ("head_image".equals(key)) {
                            userInfo.setHead_image(value);
                            mPersonalView.updataHeadImaSuccess(value);
                        } else if ("sex".equals(key)) {
                            userInfo.setSex(value);
                            mPersonalView.updataSexSuccess(value);
                        } else if ("marry".equals(key)) {
                            userInfo.setMarry(value);
                            mPersonalView.updataMarrySuccess(value);
                        } else if ("birthday".equals(key)) {
                            userInfo.setBirthday(value);
                            mPersonalView.updataBirthDaySuccess(value);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (mPersonalView != null) {
                            mPersonalView.updataInfoFail(e.toString());
                        }
                    }
                });
    }
}
