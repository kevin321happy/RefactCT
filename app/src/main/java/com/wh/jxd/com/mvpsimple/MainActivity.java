package com.wh.jxd.com.mvpsimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wh.jxd.com.mvpsimple.simple2.BaseActivity;
import com.wh.jxd.com.mvpsimple.simple2.LoginPresenter_1;
import com.wh.jxd.com.mvpsimple.simple2.LoginView_1;

public class MainActivity extends BaseActivity<LoginPresenter_1, LoginView_1> implements LoginView_1 {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected LoginView_1 creatMvpView() {
        return this;
    }

    @Override
    public LoginPresenter_1 creatPresenter() {
        return new LoginPresenter_1();
    }

    @Override
    public void onLoginSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

    }

    /**
     * 点击开始登陆
     *
     * @param view
     */
    public void login(View view) {
        //泛型设计,抽取BasePresenter 和BaseView  避免在每一个Presenter里面去定义attachView和detachView方法,减少代码冗余
//        mLoginPresenter = new LoginPresenter_1();
//        mLoginPresenter.attachView(this);
//        mLoginPresenter.login("xx", "123");

        //通过抽取BaseActivity,精简代码避免去每一个Activity里面去做绑定和解绑操作
        getPresenter().login("xx", "123");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter()!=null) {
            getPresenter().detachView();
        }
    }
}
