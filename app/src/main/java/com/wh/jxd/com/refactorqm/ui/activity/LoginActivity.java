package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.LoginPresenterImpl;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;
import com.wh.jxd.com.refactorqm.utils.StatusBarUtil;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.LoginView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenterImpl, LoginView> implements LoginView {
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
    private LoginPresenterImpl mLoginPersenter;

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
        //设置状态栏背景透明
        StatusBarUtil.setStatusBarColor(this, R.color.transparent);
    }

    @Override
    protected LoginView creatView() {
        return this;
    }

    @Override
    public LoginPresenterImpl creatPersenter(Context context) {
        if (mLoginPersenter == null) {
            mLoginPersenter = new LoginPresenterImpl();
        }
        return mLoginPersenter;
    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

    @OnClick({R.id.ll_back, R.id.iv_psw_show, R.id.bt_login, R.id.tv_register, R.id.tv_forgotpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_psw_show:
                break;
            case R.id.bt_login:
                if (mLoginPersenter != null) {
                    mLoginPersenter.login(mEtPhoneNumber.getText().toString().trim(), mEtPassword.getText().toString().trim());
                }
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_forgotpwd:
                break;
        }
    }
    @Override
    public void onCheckFail(String info) {
        ToastUtils.showLongToast(this, info);
    }

    @Override
    public void onLoginFail(String erro) {
        ToastUtils.showShortToast(this,"登陆失败了~:"+erro.toString());
    }

    @Override
    public void onLoginSucesss(UserInfo userInfo) {
        PreferenceUtils.setUserId(userInfo.getId());
        finish();
    }
}
