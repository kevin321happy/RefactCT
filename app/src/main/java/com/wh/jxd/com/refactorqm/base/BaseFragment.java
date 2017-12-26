package com.wh.jxd.com.refactorqm.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wh.jxd.com.refactorqm.ui.activity.LoginActivity;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 跳转到登陆
     *
     * @param context
     */
    public void LoginAgain(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件ID
    protected abstract int getLayoutId();

}
