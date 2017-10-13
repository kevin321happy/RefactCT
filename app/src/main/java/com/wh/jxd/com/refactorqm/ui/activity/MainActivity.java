package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.MainPresenter;
import com.wh.jxd.com.refactorqm.ui.fragment.EnterpriseFragment;
import com.wh.jxd.com.refactorqm.ui.fragment.HomeFragment;
import com.wh.jxd.com.refactorqm.ui.fragment.PersonalFragment;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.MainView;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter, MainView> implements MainView {
    @Bind(R.id.rm_content)
    FrameLayout mRmContent;
    @Bind(R.id.iv_menu_home)
    ImageView mIvMenuHome;
    @Bind(R.id.tv_menu_home)
    TextView mTvMenuHome;
    @Bind(R.id.ll_menu_home)
    LinearLayout mLlMenuHome;
    @Bind(R.id.iv_menu_enterprise)
    ImageView mIvMenuEnterprise;
    @Bind(R.id.tv_menu_enterprise)
    TextView mTvMenuEnterprise;
    @Bind(R.id.ll_menu_enterprise)
    LinearLayout mLlMenuEnterprise;
    @Bind(R.id.iv_menu_personal)
    ImageView mIvMenuPersonal;
    @Bind(R.id.tv_menu_personal)
    TextView mTvMenuPersonal;
    @Bind(R.id.ll_menu_personal)
    LinearLayout mLlMenuPersonal;
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private EnterpriseFragment mEnterpriseFragment;
    private PersonalFragment mPersonalFragment;
    private MainPresenter mMainPersenter;
    private MenuItem item_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.transparencyBar(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        clickMenu(mLlMenuHome);
        sendLocation();
    }

    /**
     * 发送定位信息
     */
    private void sendLocation() {
        if (mMainPersenter != null) {
//            mMainPersenter.sendLocation(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 点击了某个菜单
     *
     * @param view
     */
    private void clickMenu(View view) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        int viewId = view.getId();
        //设置菜单的样式
        setMenuStyle(viewId);
        //隐藏的所有的Fragmet
        hideFragment(ft);
        //设置显示的Fragment
        setFragment(viewId, ft);
        ft.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        item_search = menu.findItem(R.id.action_search);
        item_search.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.action_search:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 隐藏返回键
     * @return
     */
    @Override
    protected boolean isShowBacking() {
        return false;
    }
    /**
     * 设置显示的Fagment
     * @param viewId
     * @param ft
     */
    private void setFragment(int viewId, FragmentTransaction ft) {
        switch (viewId) {
            case R.id.ll_menu_home:
                showToolBar();
                setToolBarTitle("大厅");
                if (item_search!=null){
                    item_search.setVisible(true);
                }
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.rm_content, mHomeFragment);
                } else {
                    ft.show(mHomeFragment);
                }
                break;
            case R.id.ll_menu_enterprise:
                showToolBar();
                setToolBarTitle("企业");
                if (item_search!=null){
                    item_search.setVisible(false);
                }
                if (mEnterpriseFragment == null) {
                    mEnterpriseFragment = new EnterpriseFragment();
                    ft.add(R.id.rm_content, mEnterpriseFragment);
                } else {
                    ft.show(mEnterpriseFragment);
                }
                break;
            case R.id.ll_menu_personal:
                setImmersionState(getResources().getColor(R.color.transparent));
                //如果用户ID为空先跳转到登陆
                if (PreferenceUtils.getUserId() == null||"".equals(PreferenceUtils.getUserId())) {
                    startActivity(new Intent(this, LoginActivity.class));

                }
                hideToolBar();
                String userId = PreferenceUtils.getUserId();
                if (userId == null || "".equals(userId)) {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                ToastUtils.showShortToast(this, "用户ID:" + userId);
                setToolBarTitle("个人");
                if (item_search!=null){
                    item_search.setVisible(false);
                }
                if (mPersonalFragment == null) {
                    mPersonalFragment = new PersonalFragment();
                    ft.add(R.id.rm_content, mPersonalFragment);
                } else {
                    ft.show(mPersonalFragment);
                }
                break;
            default:
                break;
        }

    }

    /**
     * 先隐藏所有的Fragment
     *
     * @param ft
     */
    private void hideFragment(FragmentTransaction ft) {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }
        if (mEnterpriseFragment != null) {
            ft.hide(mEnterpriseFragment);
        }
        if (mPersonalFragment != null) {
            ft.hide(mPersonalFragment);
        }
    }
    private void setMenuStyle(int vID) {
        // 首页
        if (vID == R.id.ll_menu_home) {
            mIvMenuHome.setImageDrawable(getResources().getDrawable(R.drawable.one_pressd));
            mTvMenuHome.setTextColor(getResources().getColor(R.color.title_color));
        } else {
            mIvMenuHome.setImageDrawable(getResources().getDrawable(R.drawable.one));
            mTvMenuHome.setTextColor(getResources().getColor(R.color.color_999));
        }
        // 热点
        if (vID == R.id.ll_menu_enterprise) {
            mIvMenuEnterprise.setImageDrawable(getResources().getDrawable(R.drawable.two_pressd));
            mTvMenuEnterprise.setTextColor(getResources().getColor(R.color.title_color));
        } else {
            mIvMenuEnterprise.setImageDrawable(getResources().getDrawable(R.drawable.two));
            mTvMenuEnterprise.setTextColor(getResources().getColor(R.color.color_999));
        }
        // 发言
        if (vID == R.id.ll_menu_personal) {
            mIvMenuPersonal.setImageDrawable(getResources().getDrawable(R.drawable.three_pressd));
            mTvMenuPersonal.setTextColor(getResources().getColor(R.color.title_color));
        } else {
            mIvMenuPersonal.setImageDrawable(getResources().getDrawable(R.drawable.three));
            mTvMenuPersonal.setTextColor(getResources().getColor(R.color.color_999));
        }
    }

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected MainView creatView() {
        return this;
    }

    @Override
    public MainPresenter creatPersenter(Context context) {
        if (mMainPersenter == null) {
            mMainPersenter = new MainPresenter(context);
        }
        return mMainPersenter;
    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

    @OnClick({R.id.ll_menu_home, R.id.ll_menu_enterprise, R.id.ll_menu_personal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_menu_home:
                clickMenu(mLlMenuHome);
                break;
            case R.id.ll_menu_enterprise:
                clickMenu(mLlMenuEnterprise);
                break;
            case R.id.ll_menu_personal:
                clickMenu(mLlMenuPersonal);
                break;
        }
    }
}
