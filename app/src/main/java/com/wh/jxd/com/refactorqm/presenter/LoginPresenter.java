package com.wh.jxd.com.refactorqm.presenter;

import com.wh.jxd.com.refactorqm.base.BasePersenter;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public interface LoginPresenter extends BasePersenter {
    void login(String phone, String passward);
}
