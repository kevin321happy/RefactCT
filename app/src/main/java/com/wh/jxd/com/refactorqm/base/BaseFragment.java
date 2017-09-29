package com.wh.jxd.com.refactorqm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public abstract class BaseFragment<P extends BasePersenterImpl, V extends BaseView> extends Fragment {

    private P mPersenter;
    private V mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mPersenter == null) {
            this.mPersenter = creatP();
        }
//        if (mPersenter == null) {
//            throw new NullPointerException("mPersenter不能为空哦~");
//        }
        if (mView == null) {
            this.mView = creatV();
        }
//        if (mView == null) {
//            throw new NullPointerException("mView不能为空哦！");
//        }
        if (mPersenter!=null&&mView!=null){
            mPersenter.attachView(mView);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPersenter != null) {
            mPersenter.detachView();
        }
    }
    protected abstract void initView(View view, Bundle savedInstanceState);
    //获取布局文件ID
    protected abstract int getLayoutId();

    protected abstract V creatV();

    protected abstract P creatP();

}
