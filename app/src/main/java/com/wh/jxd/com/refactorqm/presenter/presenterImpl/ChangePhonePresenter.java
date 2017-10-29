package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import android.text.TextUtils;

import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.BaseModel;
import com.wh.jxd.com.refactorqm.model.CommonDataModel;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.view.ChangePhoneView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/10/19.
 */

public class ChangePhonePresenter extends BasePersenterImpl<ChangePhoneView> {

    private ChangePhoneView mChangePhoneView;
    private NetDataManager<BaseModel> mModelNetDataManager;

    public ChangePhonePresenter() {
        mChangePhoneView = getView();
    }

    /**
     * 获取验证码
     *
     * @param phone_num
     */
    public void getVerificationCode(String phone_num) {
        if (mChangePhoneView == null) {
            mChangePhoneView = getView();
        }
        if (mModelNetDataManager == null) {
            mModelNetDataManager = new NetDataManager<>();
        }
        Observable<CommonDataModel> modelObservable = mModelNetDataManager.getVerificationCode("phone", phone_num, "2");
        modelObservable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommonDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommonDataModel commonDataModel) {
                        if ("1".equals(commonDataModel.getStatus())) {
                            mChangePhoneView.getVerificationCodeSuccess();
                        }
                    }
                });
    }

    /**
     * 校验参数信息
     *
     * @param verificationCode
     * @param phone_num
     */
    public void CheckParameters(String verificationCode, String phone_num) {
        if (mChangePhoneView == null) {
            mChangePhoneView = getView();
        }
        if (TextUtils.isEmpty(verificationCode)) {
            mChangePhoneView.onCheckFail("验证码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(phone_num)) {
            mChangePhoneView.onCheckFail("电话号码不能为空");
            return;
        }
        mChangePhoneView.oncheckSuccess(phone_num, verificationCode);
    }

    /**
     * 开始提交新的手机号
     *
     * @param phone_num
     * @param verificationCode
     */
    public void submitNewNum(String phone_num, String verificationCode) {
        if (mModelNetDataManager == null) {
            mModelNetDataManager = new NetDataManager<>();
        }
        Observable<CommonDataModel> updataPhoneNum = mModelNetDataManager.updataPhoneNum("phone", phone_num, "code", verificationCode);
        updataPhoneNum.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommonDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommonDataModel commonDataModel) {
                        if (commonDataModel.getStatus().equals("1")){
                            mChangePhoneView.changePhoneSuccess();
                        }else {
                            mChangePhoneView.onCheckFail("更换失败");
                        }
                    }
                });

    }
}
