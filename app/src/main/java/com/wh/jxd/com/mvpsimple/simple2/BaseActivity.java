package com.wh.jxd.com.mvpsimple.simple2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wh.jxd.com.mvpsimple.simple2.base.BasePresenter_1;
import com.wh.jxd.com.mvpsimple.simple2.base.BaseView_1;

/**
 * Created by kevin321vip on 2017/9/18.
 */

public abstract class BaseActivity<P extends BasePresenter_1, V extends BaseView_1> extends Activity {

    private P mPresenter;
    private V mView;


    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.mPresenter == null) {
            mPresenter = creatPresenter();
        }
        if (this.mView == null) {
            mView = creatMvpView();
        }
        //如果对象为空,抛异常
        if (mPresenter == null) {
            throw new NullPointerException("presenter对象不能为空");
        }
        if (mView == null) {
            throw new NullPointerException("view对象不能为空");
        }
        mPresenter.attachView(mView);

    }

    /**
     * 获取View对象
     *
     * @return
     */
    protected abstract V creatMvpView();

    /**
     * 获得p层的对象
     *
     * @return
     */
    public abstract P creatPresenter();

    /**
     * 解除绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
