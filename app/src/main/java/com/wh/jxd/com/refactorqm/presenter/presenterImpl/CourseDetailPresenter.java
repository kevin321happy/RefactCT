package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.CourseDetailModel;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.view.CourseDetailView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/10/31.
 */

public class CourseDetailPresenter extends BasePersenterImpl<CourseDetailView> {

    private CourseDetailView mCourseDetailView;

    public CourseDetailPresenter() {
    }

    /**
     * 获取课程ID
     *
     * @param course_id
     */
    public void getCourseDetail(String course_id) {
        if (mCourseDetailView == null) {
            mCourseDetailView = getView();
        }
        NetDataManager<CourseDetailModel> detailModelNetDataManager = new NetDataManager<>();
        final Observable<CourseDetailModel> modelObservable = detailModelNetDataManager.getCourseDetail("courseId", course_id);
        modelObservable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        KLog.i("开始请求");

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<CourseDetailModel>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onNext(CourseDetailModel data) {
                        KLog.i("请求完成："+data.toString());
                        if (data==null){
                            mCourseDetailView.onTokenLose();
                            return;
                        }
                        mCourseDetailView.getCourseDetailSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
