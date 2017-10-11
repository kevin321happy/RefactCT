package com.wh.jxd.com.refactorqm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.EnterpriseFragmentPresenterImpl;
import com.wh.jxd.com.refactorqm.view.EnterpriseFragmentView;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class EnterpriseFragment extends BaseMvpFragment<EnterpriseFragmentPresenterImpl, EnterpriseFragmentView> implements EnterpriseFragmentView {

    private EnterpriseFragmentPresenterImpl fragmentPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enterprise;
    }

    @Override
    protected EnterpriseFragmentView creatV() {
        return this;
    }

    @Override
    protected EnterpriseFragmentPresenterImpl creatP() {
        if (fragmentPresenter == null) {
            fragmentPresenter = new EnterpriseFragmentPresenterImpl();
        }
        return fragmentPresenter;
    }

    @Override
    public void onLoadSuccess() {

    }

}
