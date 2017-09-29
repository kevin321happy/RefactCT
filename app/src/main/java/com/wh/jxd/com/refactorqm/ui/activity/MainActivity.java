package com.wh.jxd.com.refactorqm.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseActivtiy;
import com.wh.jxd.com.refactorqm.persenter.MainPersenter;
import com.wh.jxd.com.refactorqm.ui.fragment.EnterpriseFragment;
import com.wh.jxd.com.refactorqm.ui.fragment.HomeFragment;
import com.wh.jxd.com.refactorqm.ui.fragment.PersonalFragment;
import com.wh.jxd.com.refactorqm.view.MainView;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivtiy<MainPersenter, MainView> implements MainView {
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickMenu(mLlMenuHome);


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

    /**
     * 设置显示的Fagment
     *
     * @param viewId
     * @param ft
     */
    private void setFragment(int viewId, FragmentTransaction ft) {
        switch (viewId) {
            case R.id.ll_menu_home:
                setToolBarTitle("大厅");
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.rm_content, mHomeFragment);
                } else {
                    ft.show(mHomeFragment);
                }
                break;
            case R.id.ll_menu_enterprise:
                setToolBarTitle("企业");
                if (mEnterpriseFragment == null) {
                    mEnterpriseFragment = new EnterpriseFragment();
                    ft.add(R.id.rm_content, mEnterpriseFragment);
                } else {
                    ft.show(mEnterpriseFragment);
                }
                break;
            case R.id.ll_menu_personal:
                setToolBarTitle("个人");
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
    public MainPersenter creatPersenter() {
        return new MainPersenter();
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
