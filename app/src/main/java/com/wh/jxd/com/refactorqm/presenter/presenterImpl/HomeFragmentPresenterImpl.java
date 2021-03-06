package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.HomeInfo;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.presenter.HomeFragmentPresenter;
import com.wh.jxd.com.refactorqm.view.HomeFragmentView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/9/30.
 */

public class HomeFragmentPresenterImpl extends BasePersenterImpl<HomeFragmentView> implements HomeFragmentPresenter {


    private HomeFragmentView mHomeFragmentView;

    public HomeFragmentPresenterImpl() {

    }

    /**
     * 加载首页的数据
     */
    @Override
    public void loadHomeData() {
        mHomeFragmentView = getView();
        NetDataManager<HomeInfo> homeInfoNetDataManager = new NetDataManager<>();
        Observable<HomeInfo> homeData =
                homeInfoNetDataManager.getHomeData();
        homeData.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        KLog.i("开始请求");

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<HomeInfo>() {
                    @Override
                    public void onCompleted() {
                        KLog.i("请求完成了！！！！");
                    }

                    @Override
                    public void onNext(HomeInfo data) {
                        if (mHomeFragmentView != null) {
                            mHomeFragmentView.onLoadSuccess(data);
                        }
                        KLog.i("请求成功了！！！！" + data.toString());
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (mHomeFragmentView != null) {
                            mHomeFragmentView.onLoadFail(e.toString());
                        }
                        KLog.i("请求失败了！！！！" + e.toString());
                    }
                });
    }
}
