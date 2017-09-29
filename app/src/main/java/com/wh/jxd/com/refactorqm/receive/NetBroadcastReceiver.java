package com.wh.jxd.com.refactorqm.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.wh.jxd.com.refactorqm.base.BaseActivtiy;
import com.wh.jxd.com.refactorqm.utils.NetStateUtils;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class NetBroadcastReceiver extends BroadcastReceiver{

    public NetEvevt evevt = BaseActivtiy.evevt;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetStateUtils.getNetWorkState(context);
            // 接口回调传过去状态的类型
            if (evevt!=null){
                evevt.onNetChange(netWorkState);
            }
        }
    }
    // 自定义接口
    public interface NetEvevt {
        public void onNetChange(int netMobile);
    }
}
