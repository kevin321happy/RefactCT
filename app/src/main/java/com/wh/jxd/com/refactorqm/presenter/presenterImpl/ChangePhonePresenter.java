package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.view.ChangePhoneView;

/**
 * Created by kevin321vip on 2017/10/19.
 */

public class ChangePhonePresenter extends BasePersenterImpl<ChangePhoneView> {

    private final ChangePhoneView mChangePhoneView;

    public ChangePhonePresenter() {
        mChangePhoneView = getView();
    }

    /**
     * 获取验证码
     * @param phone_num
     */
    public void getVerificationCode(String phone_num) {

    }
}
