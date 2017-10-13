package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.PersonalPresenter;
import com.wh.jxd.com.refactorqm.view.PersonalView;

/**
 * Created by kevin321vip on 2017/10/12.
 * 个人信息界面
 */

public class PersonalActivity extends BaseMvpActivity<PersonalPresenter, PersonalView> implements PersonalView {

    private PersonalPresenter mPersonalPresenter;

    @Override
    protected PersonalView creatView() {
        return this;
    }

    @Override
    public PersonalPresenter creatPersenter(Context context) {
        if (mPersonalPresenter == null) {
            mPersonalPresenter = new PersonalPresenter();
        }
        return mPersonalPresenter;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_personalinfo;
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }
}
