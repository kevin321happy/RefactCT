package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.EntButtomMenuModel;
import com.wh.jxd.com.refactorqm.model.EnterpriseDataModel;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.presenter.EnterpriseFragmentPresenter;
import com.wh.jxd.com.refactorqm.view.EnterpriseFragmentView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public class EnterpriseFragmentPresenterImpl extends BasePersenterImpl<EnterpriseFragmentView> implements EnterpriseFragmentPresenter {

    private EnterpriseFragmentView mFragmentView;
    private NetDataManager<EnterpriseDataModel> mNetDataManager;

    /**
     * 加载数据
     */
    @Override
    public void onLoadData() {
        if (mFragmentView == null) {
            mFragmentView = getView();
        }
        if (mNetDataManager == null) {
            mNetDataManager = new NetDataManager<>();
        }
        Observable<EnterpriseDataModel> dataModelObservable = mNetDataManager.getEenterprise("company_id", "8");
        dataModelObservable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<EnterpriseDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(EnterpriseDataModel data) {
                        if (mFragmentView == null) {
                            mFragmentView = getView();
                        }
                        if (data==null){
                            mFragmentView.onTokenLose();
                            return;
                        }
                        mFragmentView.onLoadSuccess(data);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 获取底部菜单数据
     */
    public List<EntButtomMenuModel> getButtomMenuData() {
        ArrayList<EntButtomMenuModel> infos = new ArrayList<>();
        infos.add(new EntButtomMenuModel(R.drawable.ic_ent_culture, "企业资讯"));
        infos.add(new EntButtomMenuModel(R.drawable.ic_ent_examine, "我的考试"));
        infos.add(new EntButtomMenuModel(R.drawable.ic_ent_pk, "PK榜"));
        infos.add(new EntButtomMenuModel(R.drawable.ic_ent_more, "更多"));
        return infos;
    }

    /**
     * 讲String类型转化为long
     */
    public long formatString2Long(String study_time) {
        long data = 0;
        if (study_time != null && !"".equals(study_time)) {
            data = Long.parseLong(study_time);
        }
        return data;
    }
}
