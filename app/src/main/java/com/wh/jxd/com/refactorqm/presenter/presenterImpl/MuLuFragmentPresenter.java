package com.wh.jxd.com.refactorqm.presenter.presenterImpl;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.base.BasePersenterImpl;
import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.db.ChaptersDao;
import com.wh.jxd.com.refactorqm.model.BaseModel;
import com.wh.jxd.com.refactorqm.model.ChapterInfo;
import com.wh.jxd.com.refactorqm.model.ChapterListModel;
import com.wh.jxd.com.refactorqm.model.SectionInfo;
import com.wh.jxd.com.refactorqm.net.FilterSubscriber;
import com.wh.jxd.com.refactorqm.net.HttpBean;
import com.wh.jxd.com.refactorqm.net.NetDataManager;
import com.wh.jxd.com.refactorqm.view.MuLuFragmentView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


/**
 * Created by kevin321vip on 2017/11/1.
 */

public class MuLuFragmentPresenter extends BasePersenterImpl<MuLuFragmentView> {

    private MuLuFragmentView mMuLuFragmentView;
    private ChaptersDao mChaptersDao;

    /**
     * 获取章节数据
     *
     * @param courseId
     */
    public void getChapterData(String courseId) {
        mMuLuFragmentView = getView();
        NetDataManager<ChapterListModel> dataManager = new NetDataManager<>();
        Observable<ChapterListModel> chapterLists = dataManager.getChapterLists("courseId", courseId);
        chapterLists.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<ChapterListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ChapterListModel data) {
                        if (data == null) {
                            mMuLuFragmentView.onTokenLose();
                            return;
                        }
                        List<ChapterInfo> chapterInfos = data.getData();
                        if (chapterInfos.size() > 0) {
                            for (ChapterInfo chapterInfo : chapterInfos) {
                                KLog.d("章节信息：" + chapterInfo.toString());
                            }
                        }
                        if (chapterInfos != null && chapterInfos.size() > 0) {
                            mMuLuFragmentView.onLoadSuccess(chapterInfos);
                        } else {
                            mMuLuFragmentView.onLoadFail("对象空了啊~");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        KLog.d("章节信息失败：" + e.toString());
//                        mMuLuFragmentView.onLoadFail(e.toString());
                    }
                });
    }

    /**
     * 保存数据到数据库中
     *
     * @param chapterInfos
     */
    public void saveToDb(final List<ChapterInfo> chapterInfos) {
        if (mChaptersDao == null) {
            mChaptersDao = new ChaptersDao();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (ChapterInfo chapterInfo : chapterInfos) {
                    List<SectionInfo> chapterList = chapterInfo.getChapterList();
                    for (SectionInfo sectionInfo : chapterList) {
                        mChaptersDao.insertOneChapter(sectionInfo);
                    }
                }
            }
        }).start();

    }
}
