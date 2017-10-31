package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;

import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.CourseDetailPresenter;
import com.wh.jxd.com.refactorqm.view.CourseDetailView;

/**
 * Created by kevin321vip on 2017/10/31.
 * 课程详情界面
 */

public class CourseDetailActivity extends BaseMvpActivity<CourseDetailPresenter,CourseDetailView> {
    @Override
    public void onTokenLose() {

    }

    @Override
    protected CourseDetailView creatView() {
        return null;
    }

    @Override
    public CourseDetailPresenter creatPersenter(Context context) {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }
}
