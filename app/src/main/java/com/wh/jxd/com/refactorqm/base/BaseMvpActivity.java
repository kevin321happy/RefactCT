package com.wh.jxd.com.refactorqm.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by kevin321vip on 2017/9/30.
 */

public abstract class BaseMvpActivity<P extends BasePersenterImpl, V extends BaseView> extends BaseActivtiy implements BaseView {

    private P mpersenter;
    private V mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mpersenter == null) {
            mpersenter = creatPersenter(this);
        }
        if (mpersenter == null) {
            throw new NullPointerException("mpersenter不能为空~");
        }
        if (this.mView == null) {
            mView = creatView();
        }
        if (this.mView == null) {
            throw new NullPointerException("mView不能为空~");
        }
        mpersenter.attachView(mView);
    }

    /**
     * 获取View对象
     *
     * @return
     */

    protected abstract V creatView();

    /**
     * 获取子类的Persenter对象
     *
     * @return
     */
    public abstract P creatPersenter(Context context);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpersenter != null) {
            mpersenter.detachView();
        }
    }
}
