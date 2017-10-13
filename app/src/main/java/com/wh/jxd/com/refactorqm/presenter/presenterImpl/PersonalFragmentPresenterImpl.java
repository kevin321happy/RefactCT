package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.presenter.PersonalFragmentPresenter;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;
import com.wh.jxd.com.refactorqm.view.PersonalFragmentView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public class PersonalFragmentPresenterImpl extends BasePersenterImpl<PersonalFragmentView> implements PersonalFragmentPresenter {

    private PersonalFragmentView mPersonalFragmentView;

    /**
     * 加载个人数据
     */
    @Override
    public void loadData() {
        if (mPersonalFragmentView == null) {
            mPersonalFragmentView = getView();
        }
        NetDataManager<UserInfo> userInfoNetDataManager = new NetDataManager<>();
        //获取用户信息
        Observable<UserInfo> userInfo = userInfoNetDataManager.getUserInfo(PreferenceUtils.getUserId(), PreferenceUtils.getQM_Token());
        userInfo.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        KLog.i("开始");

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                        KLog.i("完成");
                    }

                    @Override
                    public void onNext(UserInfo data) {
//                        KLog.i("成功：" + data.toString());
                        if (mPersonalFragmentView != null) {
                            mPersonalFragmentView.onLoadSuccess(data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        KLog.i("失败");
                        if (mPersonalFragmentView != null) {
                            mPersonalFragmentView.onLoadFail(e.toString());
                        }
                    }
                });
    }

    /**
     * 退出登陆
     */
    public void loginOut() {
        //清除用户信息
        PreferenceUtils.setUserId("");
        PreferenceUtils.setQM_Token("");
        PreferenceUtils.setCompanyId("");
        PreferenceUtils.setUserInfoBean("");
        PreferenceUtils.setUser_PSW("");
        PreferenceUtils.setUser_Phone("");
        PreferenceUtils.setIs_Teacher(false);
        if (mPersonalFragmentView != null) {
            mPersonalFragmentView.clearInfoSuccess();
        }
    }
}
