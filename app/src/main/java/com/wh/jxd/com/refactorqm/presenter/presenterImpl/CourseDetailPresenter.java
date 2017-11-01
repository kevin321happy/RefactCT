package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import android.view.View;

import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.listener.StandardVideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.model.CourseDetailModel;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.ui.activity.CourseDetailActivity;
import com.wh.jxd.com.refactorqm.utils.CommonUtils;
import com.wh.jxd.com.refactorqm.view.CourseDetailView;
import com.wh.jxd.com.refactorqm.view.widget.CourseVedioPlay;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kevin321vip on 2017/10/31.
 */

public class CourseDetailPresenter extends BasePersenterImpl<CourseDetailView> {

    private CourseDetailView mCourseDetailView;
    private OrientationUtils orientationUtils;
    private long mStartTime;
    private boolean isPlay;
    private CourseVedioPlay mCustomGSYVideoPlayer;

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
                        KLog.i("请求完成：" + data.toString());
                        if (data == null) {
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

    /**
     * 初始化播放器的配置
     *
     * @param courseDetailActivity
     * @param mCustomGSYVideoPlayer
     */
    public void initCourseVedioCongif(final CourseDetailActivity courseDetailActivity, final CourseVedioPlay mCustomGSYVideoPlayer) {
        this.mCustomGSYVideoPlayer = mCustomGSYVideoPlayer;
        if (mCustomGSYVideoPlayer == null) {
            return;
        }
        orientationUtils = new OrientationUtils(courseDetailActivity, mCustomGSYVideoPlayer);
        this.mCustomGSYVideoPlayer = mCustomGSYVideoPlayer;
        //初始化不打开外部的旋转
        //非视频课程不能通过触摸来改变
        mCustomGSYVideoPlayer.setIsTouchWiget(false);
        //打开自动旋转
        mCustomGSYVideoPlayer.setRotateViewAuto(true);
        mCustomGSYVideoPlayer.setLockLand(false);
        mCustomGSYVideoPlayer.setShowFullAnimation(true);
        //点击全屏按钮
        mCustomGSYVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();
                // 第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                mCustomGSYVideoPlayer.startWindowFullscreen(courseDetailActivity, true, true);
                mCustomGSYVideoPlayer.setOpenPreView(true);
            }
        });
        //播放器的监听
        mCustomGSYVideoPlayer.setStandardVideoAllCallBack(new StandardVideoAllCallBack() {
            //当点击封面
            @Override
            public void onClickStartThumb(String url, Object... objects) {

            }

            //点击了播放中的空白区域
            @Override
            public void onClickBlank(String url, Object... objects) {

            }

            @Override
            public void onClickBlankFullscreen(String url, Object... objects) {

            }
            //开始播放
            @Override
            public void onPrepared(String url, Object... objects) {
                //当前时间为开始播放时间
                orientationUtils.setEnable(true);
                if (mCourseDetailView != null) {
                    mCourseDetailView.onPrepared(url);
                }
            }

            @Override
            public void onClickStartIcon(String url, Object... objects) {
                //保存学习记录
//                Addcourserecord(mCourseInfo);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {

            }

            //播放状态下点击了暂停
            @Override
            public void onClickStop(String url, Object... objects) {
                isPlay = false;
                if (mCourseDetailView != null) {
                    mCourseDetailView.onStopPlay(url);
                }
            }

            //全屏播放状态下点击了暂停
            @Override
            public void onClickStopFullscreen(String url, Object... objects) {
                isPlay = false;
                KLog.i("全屏播放，路径：" + url);
                //点击了暂挺当前时间为结束时间
//                studyTimeSend(mStartTime, mSessonid, false);
            }

            //点击了暂停状态点击了播放
            @Override
            public void onClickResume(String url, Object... objects) {
                isPlay = true;
                //重新取开始时间
                mStartTime = CommonUtils.getCurrentTime();
            }

            //全屏状态下暂停状态点击了播放
            @Override
            public void onClickResumeFullscreen(String url, Object... objects) {
                isPlay = true;
                mStartTime = CommonUtils.getCurrentTime();
            }

            @Override
            public void onClickSeekbar(String url, Object... objects) {
            }

            @Override
            public void onClickSeekbarFullscreen(String url, Object... objects) {
            }

            //视频播放结束
            @Override
            public void onAutoComplete(String url, Object... objects) {
                isPlay = false;
//                studyTimeSend(mStartTime, mSessonid, false);
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {


            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }

            @Override
            public void onQuitSmallWidget(String url, Object... objects) {
            }

            @Override
            public void onEnterSmallWidget(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekVolume(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekPosition(String url, Object... objects) {

            }

            @Override
            public void onTouchScreenSeekLight(String url, Object... objects) {

            }
            @Override
            public void onPlayError(String url, Object... objects) {
                KLog.i("播放错误，路径：" + url);

            }
        });
    }
    /**
     * 释放播放器
     */
    public void releaseVedio() {
        mCustomGSYVideoPlayer.release();
        GSYVideoPlayer.releaseAllVideos();
        GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }
}
