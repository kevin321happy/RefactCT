package com.wh.jxd.com.refactorqm.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseActivtiy;
import com.wh.jxd.com.refactorqm.persenter.LoginPersenter;
import com.wh.jxd.com.refactorqm.utils.StatusBarUtil;
import com.wh.jxd.com.refactorqm.view.LoginView;

import butterknife.Bind;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public class LoginActivity extends BaseActivtiy<LoginPersenter, LoginView> implements LoginView {
    @Bind(R.id.ll_back)
    LinearLayout mLlBack;
    @Bind(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.iv_psw_show)
    ImageView mIvPswShow;
    @Bind(R.id.bt_login)
    Button mBtLogin;
    @Bind(R.id.tv_register)
    TextView mTvRegister;
    @Bind(R.id.tv_forgotpwd)
    TextView mTvForgotpwd;
    private LoginPersenter mLoginPersenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //隐藏ToolBar
        hideToolBar();
        StatusBarUtil.setStatusBarColor(this, R.color.transparent);

    }

    @Override
    protected LoginView creatView() {
        return this;
    }

    @Override
    public LoginPersenter creatPersenter() {
        if (mLoginPersenter == null) {
            mLoginPersenter = new LoginPersenter();
        }
        return mLoginPersenter;
    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

}
