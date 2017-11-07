package com.wh.jxd.com.refactorqm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.model.GridViewBean;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.model.event.MainEvent;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.PersonalFragmentPresenterImpl;
import com.wh.jxd.com.refactorqm.ui.activity.LoginActivity;
import com.wh.jxd.com.refactorqm.ui.activity.PersonalActivity;
import com.wh.jxd.com.refactorqm.ui.activity.RecentStudyActivity;
import com.wh.jxd.com.refactorqm.ui.activity.SystemSettingActivity;
import com.wh.jxd.com.refactorqm.ui.activity.WebViewActivity;
import com.wh.jxd.com.refactorqm.ui.adapter.PersonalMenuAdapter;
import com.wh.jxd.com.refactorqm.utils.FrescoUtils;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.PersonalFragmentView;
import com.wh.jxd.com.refactorqm.view.widget.AppBarStateChangeListener;
import com.wh.jxd.com.refactorqm.view.widget.CircleImageView;
import com.wh.jxd.com.refactorqm.view.widget.NoScrollListView;
import com.wh.jxd.com.refactorqm.view.widget.dialog.ActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class PersonalFragment extends BaseMvpFragment<PersonalFragmentPresenterImpl, PersonalFragmentView> implements PersonalFragmentView, AdapterView.OnItemClickListener {
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
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_leave)
    TextView mTvLeave;
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
        mLvMenu.setOnItemClickListener(this);
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
//        ToastUtils.showShortToast(getActivity(), "加载成功了");
        if (data == null) {
            return;
        }
        FrescoUtils.showUrlBlur(mIvBg, data.getHead_image(), 10, 10);
        Glide.with(this).load(data.getHead_image()).into(mIvHead);
        mTvLeave.setText(data.getMember_level() == null ? "LV" : "LV" + data.getMember_level());
        mTvName.setText(data.getMember_name() == null ? "" : data.getMember_name());
        AppcationEx.getInstance().setUserInfo(data);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onLoadFail(String s) {
        KLog.i("失败：" + s.toString());
    }

    @Override
    public void clearInfoSuccess() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void onTokenLose() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, 333);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 收到EvenBus的消息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMianEvent(MainEvent event) {
        if ("3".equals(event.getType())) {
            mFragmentPresenter.loadData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 333) {
            mFragmentPresenter.loadData();
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMainEvent(MainEvent event) {
//        ToastUtils.showShortToast(getActivity(), event.getType());
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    /**
     * 菜单条目的点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                //最近学习
                intent = new Intent(getActivity(), RecentStudyActivity.class);
                startActivity(intent);
                break;
            case 1:
                //我的收藏
                break;
            case 2:
                //我的积分
                break;
            case 3:
                //我的下载
                break;
            case 4:
                //全民录课
                break;
            case 5:
                //帮助反馈
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("key", 18);
                startActivity(intent);
                break;
            case 6:
                //系统设置
                intent = new Intent(getActivity(), SystemSettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_head, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_out:
                //退出登陆
                new ActionSheetDialog(getActivity())
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("退出当前账号", ActionSheetDialog.SheetItemColor.Black,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        if (mFragmentPresenter != null) {
                                            mFragmentPresenter.loginOut();
                                        }
                                    }
                                }).show();
                break;
        }
    }
}
