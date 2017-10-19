package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.SystemSettingPresenter;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.SystemSettingView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin321vip on 2017/10/18.
 * 系统设置
 */

public class SystemSettingActivity extends BaseMvpActivity<SystemSettingPresenter, SystemSettingView> implements SystemSettingView {
    @Bind(R.id.toolbar_subtitle)
    TextView mToolbarSubtitle;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ll_updatepwd)
    LinearLayout mLlUpdatepwd;
    @Bind(R.id.tv_old_phone)
    TextView mTvOldPhone;
    @Bind(R.id.ll_change_phone_num)
    LinearLayout mLlChangePhoneNum;
    @Bind(R.id.tv_cash)
    TextView mTvCash;
    @Bind(R.id.ll_cleanCash)
    LinearLayout mLlCleanCash;
    @Bind(R.id.tv_current_version)
    TextView mTvCurrentVersion;
    @Bind(R.id.ll_update_app)
    LinearLayout mLlUpdateApp;
    @Bind(R.id.ll_score)
    LinearLayout mLlScore;
    @Bind(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @Bind(R.id.ll_aboutapp)
    LinearLayout mLlAboutapp;
    private SystemSettingPresenter mSystemSettingPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_setting;
    }

    @Override
    protected void initView() {
        setToolBarTitle("系统设置");
    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected SystemSettingView creatView() {
        return this;
    }

    @Override
    public SystemSettingPresenter creatPersenter(Context context) {
        if (mSystemSettingPresenter == null) {
            mSystemSettingPresenter = new SystemSettingPresenter();
        }
        return mSystemSettingPresenter;
    }

    @OnClick({R.id.ll_updatepwd, R.id.ll_change_phone_num, R.id.ll_cleanCash, R.id.ll_update_app, R.id.ll_feedback, R.id.ll_aboutapp})
    public void onViewClicked(View view) {
        if (mSystemSettingPresenter == null) {
            mSystemSettingPresenter = creatPersenter(SystemSettingActivity.this);
        }
        switch (view.getId()) {
            case R.id.ll_updatepwd:
                ToastUtils.showShortToast(this, "暂未实现");
                break;
            case R.id.ll_change_phone_num:
                Intent intent = new Intent(this, ChangePhoneNumActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_cleanCash:
                mSystemSettingPresenter.clearCache(this);
                break;
            case R.id.ll_update_app:
                break;
            case R.id.ll_feedback:
                break;
            case R.id.ll_aboutapp:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClearSuccess() {
        mTvCash.setText("0.0Byte");
    }
}
