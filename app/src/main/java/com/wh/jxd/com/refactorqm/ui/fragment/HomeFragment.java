package com.wh.jxd.com.refactorqm.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.base.BaseView;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class HomeFragment extends BaseMvpFragment {
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected BaseView creatV() {
        return null;
    }

    @Override
    protected BasePersenterImpl creatP() {
        return null;
    }
}
