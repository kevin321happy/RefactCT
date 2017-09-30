package com.wh.jxd.com.refactorqm.base;

/**
 * Created by kevin321vip on 2017/9/27.
 */
public class BasePersenterImpl<V extends BaseView> implements BasePersenter {
    private V mView;

    public V getView() {
        return mView;
    }


    public void attachView(V view) {
        this.mView = view;

    }

    public void detachView() {
        this.mView = null;

    }


}
