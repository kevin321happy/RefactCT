package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.ChapterListModel;
import com.wh.jxd.com.refactorqm.model.TeacherListModel;
import com.wh.jxd.com.refactorqm.model.TeacherModel;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.view.LecturerDetailView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.internal.schedulers.ScheduledAction;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/11/6.
 */

public class LecturerDetailPresenter extends BasePersenterImpl<LecturerDetailView> {

    private NetDataManager<TeacherListModel> mNetDataManager;
    private LecturerDetailView mDetailView;

    /**
     * 加载讲师数据
     * @param teache_id
     */
    public void loadTeacherData(String teache_id) {
        mDetailView = getView();
        mNetDataManager = new NetDataManager<>();
        Observable<TeacherListModel> chapterLists = mNetDataManager.getTeacherList("teacher_id", "13");
        chapterLists.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<TeacherListModel>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onNext(TeacherListModel data) {
                        if (mDetailView!=null){
                            if ("-99".equals(data.getIs_login())){
                                mDetailView.onTokenLose();
                                return;
                            }
                            if ("-1".equals(data.getStatus())){
                                mDetailView.onLoadFail(data.getInfo());
                                return;
                            }
                            mDetailView.loadTeacherDataSuccess(data.getData());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
