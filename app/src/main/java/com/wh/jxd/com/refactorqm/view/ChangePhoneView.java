package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;

/**
 * Created by kevin321vip on 2017/10/19.
 */

public interface ChangePhoneView extends BaseView {
    /**
     * 校验失败
     *
     * @param s
     */
    void onCheckFail(String s);

    /**
     * 校验成功
     */
    void oncheckSuccess(String phome,String ver);

    /**
     * 获取验证码成功
     */
    void getVerificationCodeSuccess();

    void changePhoneSuccess();
}
