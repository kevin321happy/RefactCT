package com.wh.jxd.com.refactorqm.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.receive.NetBroadcastReceiver;
import com.wh.jxd.com.refactorqm.utils.NetStateUtils;
import com.wh.jxd.com.refactorqm.utils.StatusBarUtil;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by kevin321vip on 2017/9/27.
 */

public abstract class BaseActivtiy<P extends BasePersenterImpl, V extends BaseView> extends AutoLayoutActivity implements BaseView, NetBroadcastReceiver.NetEvevt {

    private static final String TAG = "BaseActivtiy";
    private P mpersenter;
    private V mView;
    protected SystemBarTintManager tintManager;
    private SparseArray<Long> mLastClickTimes;
    public static NetBroadcastReceiver.NetEvevt evevt;
    private LinearLayout mRootLayout;
    /**
     * Toolbar instance
     */
    protected Toolbar mToolbar;

    public Toolbar getToolbar() {
        return mToolbar;
    }

    /**
     * 网络类型
     */
    private int netMobile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                //Logger.w("Activity is not the root.  Finishing Activity instead of launching.");
                Log.w(TAG, "Activity is not the root.  Finishing Activity instead of launching.");
                finish();
                return;
            }
        }
        ButterKnife.bind(this);
        init();
        evevt = this;
        inspectNet();
        if (this.mpersenter == null) {
            mpersenter = creatPersenter();
        }
        if (mpersenter == null) {
            throw new NullPointerException("mpersenter不能为空~");
        }
        if (this.mView == null) {
            mView = creatView();
        }
        if (this.mView == null) {
            throw new NullPointerException("mView不能为空~");
        }
        mpersenter.attachView(mView);
        initView();
    }

    /**
     * 网络监听
     */
    private boolean inspectNet() {
        netMobile = NetStateUtils.getNetWorkState(this);
        return isNetConnect();
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        boolean netConnect = isNetConnect();
        if (!netConnect) {
            ToastUtils.showLongToast(this, "当前网络不可用！");
        }
    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }


    protected abstract int creatLayout();

    protected abstract void initView();

    /**
     * 初始化界面操作
     */
    public void init() {
        // 设置了全屏的界面需要排除
        //设置状态栏颜色
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.StatusBarLightMode(this);
    }

    /**
     * 获取View对象
     *
     * @return
     */

    protected abstract V creatView();

    /**
     * 获取子类的Persenter对象
     *
     * @return
     */
    public abstract P creatPersenter();

    /**
     * 双击监听
     *
     * @param res
     */
    public void setImmersionState(int res) {

    }

    /**
     * APP字体大小，不随系统的字体大小的变化而变化的方法
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
    /**
     * 检查是否可执行点击操作 防重复点击
     *
     * @return 返回true则可执行
     */
    protected boolean checkClick(int id) {
        Long lastTime = mLastClickTimes.get(id);
        Long thisTime = System.currentTimeMillis();
        mLastClickTimes.put(id, thisTime);
        return !(lastTime != null && thisTime - lastTime < 800);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    public abstract boolean isSystemBarTranclucent();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mpersenter.attachView(mView);
    }
}
