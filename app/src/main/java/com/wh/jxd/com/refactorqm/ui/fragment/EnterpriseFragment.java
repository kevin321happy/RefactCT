package com.wh.jxd.com.refactorqm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.model.EnterpriseDataModel;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.EnterpriseFragmentPresenterImpl;
import com.wh.jxd.com.refactorqm.ui.activity.LoginActivity;
import com.wh.jxd.com.refactorqm.view.EnterpriseFragmentView;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class EnterpriseFragment extends BaseMvpFragment<EnterpriseFragmentPresenterImpl, EnterpriseFragmentView> implements EnterpriseFragmentView {

    private EnterpriseFragmentPresenterImpl mFragmentPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //加载数据
        if (mFragmentPresenter == null) {
            mFragmentPresenter.onLoadData();

        }
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
        if (mFragmentPresenter == null) {
            mFragmentPresenter = new EnterpriseFragmentPresenterImpl();
        }
        return mFragmentPresenter;
    }

    @Override
    public void onLoadSuccess(EnterpriseDataModel data) {
        if (data == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        showEnterpriseInfo(data);

    }

    /**
     * 显示企业信息
     *
     * @param data
     */
    private void showEnterpriseInfo(EnterpriseDataModel data) {



    }

    @Override
    public void onTokenLose() {

    }
}
