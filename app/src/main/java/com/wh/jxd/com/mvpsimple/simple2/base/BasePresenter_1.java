package com.wh.jxd.com.mvpsimple.simple2.base;

/**
 * Created by kevin321vip on 2017/9/14.
 */

public class BasePresenter_1<V extends BaseView_1> {

    private V mView;

    public V getView() {
        return mView;
    }

    public void attachView(V View) {
        this.mView = View;
    }

    public void detachView() {
        this.mView = null;
    }
}
