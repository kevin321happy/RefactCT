package com.wh.jxd.com.refactorqm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.model.HomeInfo;
import com.wh.jxd.com.refactorqm.persenter.persenterImpl.HomeFragmentPersenterImpl;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.HomeFragmentView;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class HomeFragment extends BaseMvpFragment<HomeFragmentPersenterImpl, HomeFragmentView> implements HomeFragmentView {

    private HomeFragmentPersenterImpl mHomeFragmentPersenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //加载首页的数据
        if (mHomeFragmentPersenter != null) {
            mHomeFragmentPersenter.loadHomeData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeFragmentView creatV() {
        return this;
    }

    @Override
    protected HomeFragmentPersenterImpl creatP() {
        if (mHomeFragmentPersenter == null) {
            mHomeFragmentPersenter = new HomeFragmentPersenterImpl();
        }
        return new HomeFragmentPersenterImpl();
    }

    @Override
    public void onLoadSuccess(HomeInfo homeInfo) {
        ToastUtils.showShortToast(getActivity(), "加载数据成功：" + homeInfo.toString());
    }
}
