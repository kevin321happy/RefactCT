package com.wh.jxd.com.refactorqm.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.model.GridViewBean;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.PersonalFragmentPresenterImpl;
import com.wh.jxd.com.refactorqm.ui.adapter.PersonalMenuAdapter;
import com.wh.jxd.com.refactorqm.utils.FrescoUtils;
import com.wh.jxd.com.refactorqm.view.PersonalFragmentView;
import com.wh.jxd.com.refactorqm.view.widget.AppBarStateChangeListener;
import com.wh.jxd.com.refactorqm.view.widget.CircleImageView;
import com.wh.jxd.com.refactorqm.view.widget.NoScrollListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class PersonalFragment extends BaseMvpFragment<PersonalFragmentPresenterImpl, PersonalFragmentView> implements PersonalFragmentView {


    @Bind(R.id.iv_bg)
    SimpleDraweeView mIvBg;
    @Bind(R.id.iv_head)
    CircleImageView mIvHead;
    @Bind(R.id.tv_tool_bar_title)
    TextView mTvToolBarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.lv_menu)
    NoScrollListView mLvMenu;
    @Bind(R.id.tv_login_out)
    TextView mTvLoginOut;
    private PersonalFragmentPresenterImpl fragmentPresenter;
    private PersonalFragmentPresenterImpl mFragmentPresenter;
    //因为setExpanded会调用事件监听，所以通过标志过滤掉
    public static int expendedtag = 2;
    GridViewBean[] mGridViewBeans = {new GridViewBean(R.drawable.ic_recent_study, "最近学习")
            , new GridViewBean(R.drawable.my_collect, "我的收藏"),
            new GridViewBean(R.drawable.my_four, "我的积分")
            , new GridViewBean(R.drawable.ic_mydownload, "我的下载")
            , new GridViewBean(R.drawable.ic_record, "全民录课")
            , new GridViewBean(R.drawable.ic_helpback, "帮助反馈")
            , new GridViewBean(R.drawable.my_six, "系统设置")};
    private PersonalMenuAdapter mPersonalMenuAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        if (mFragmentPresenter == null) {
            mFragmentPresenter = creatP();
        }
        mFragmentPresenter.loadData();
        mPersonalMenuAdapter = new PersonalMenuAdapter(mGridViewBeans);
        mLvMenu.setAdapter(mPersonalMenuAdapter);
        mAppbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    mTvToolBarTitle.setVisibility(View.GONE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    mTvToolBarTitle.setVisibility(View.VISIBLE);
                } else {
                    //中间状态
                    mTvToolBarTitle.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected PersonalFragmentView creatV() {
        return this;
    }
    @Override
    protected PersonalFragmentPresenterImpl creatP() {
        if (mFragmentPresenter == null) {
            mFragmentPresenter = new PersonalFragmentPresenterImpl();
        }
        return mFragmentPresenter;
    }

    @Override
    public void onLoadSuccess(UserInfo data) {
//        KLog.i("成功：" + data.toString());
        if (data == null) {
            return;
        }
        FrescoUtils.showUrlBlur(mIvBg, data.getHead_image(), 10, 10);
        Glide.with(this).load(data.getHead_image()).into(mIvHead);
//        data.getMember_name();
    }

    @Override
    public void onLoadFail(String s) {
        KLog.i("失败：" + s.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
