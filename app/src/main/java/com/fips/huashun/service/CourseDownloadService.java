package com.fips.huashun.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
<<<<<<< HEAD
import com.fips.huashun.R;
import com.fips.huashun.db.dao.SectionDownloadDao;
import com.fips.huashun.modle.dbbean.CourseSectionEntity;
import com.fips.huashun.modle.event.OnPuaseDownloadEvent;
import com.fips.huashun.modle.event.SectionDownloadEvent;
import com.fips.huashun.modle.event.SectionDownloadStateEvent;
import com.fips.huashun.ui.utils.SPUtils;
import com.fips.huashun.ui.utils.ToastUtil;
import com.google.gson.Gson;
=======
import android.util.Log;
import com.fips.huashun.R;
import com.fips.huashun.db.dao.SectionDownloadDao;
import com.fips.huashun.modle.dbbean.CourseSectionEntity;
import com.fips.huashun.modle.event.ChangeDownLoadEvent;
import com.fips.huashun.modle.event.SectionDownloadEvent;
import com.fips.huashun.modle.event.SectionDownloadStateEvent;
import com.fips.huashun.ui.utils.ToastUtil;
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.ServerError;
import com.yanzhenjie.nohttp.error.StorageReadWriteError;
import com.yanzhenjie.nohttp.error.StorageSpaceNotEnoughError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import de.greenrobot.event.EventBus;
import java.io.File;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
=======
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8

public class CourseDownloadService extends Service {

  private DownloadQueue mDownloadQueue;
  private String mPath;
  private SectionDownloadDao mSectionDownloadDao;
  private EventBus mEventBus;
  private ToastUtil mToastUtil;
<<<<<<< HEAD
  private Map<String, DownloadRequest> DownloadRequests;
  private Gson mGson;

  @Override

  public void onCreate() {
    super.onCreate();
    //创建表
//    mGson = new Gson();
    if (mSectionDownloadDao == null) {
      mSectionDownloadDao = new SectionDownloadDao(getApplication());
    }
=======

  @Override
  public void onCreate() {
    super.onCreate();
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
    mEventBus = EventBus.getDefault();
    mEventBus.register(this);
    mDownloadQueue = NoHttp.newDownloadQueue(3);
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    //注册EvenBus
<<<<<<< HEAD
=======

>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onStart(Intent intent, int startId) {
    super.onStart(intent, startId);
<<<<<<< HEAD
=======
    Log.i("test", "下载服务开启了");
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
    mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/section";
    mToastUtil = ToastUtil.getInstant();
    File filedir = new File(mPath);
    if (!filedir.exists()) {
      filedir.mkdirs();
    }
  }

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onRebind(Intent intent) {
    super.onRebind(intent);
  }

  @Override
  public boolean onUnbind(Intent intent) {
    return super.onUnbind(intent);
  }

  @Override
  public void onDestroy() {
<<<<<<< HEAD
    //保存到本地
    String downloadJson = mGson.toJson(DownloadRequests);
    SPUtils.put(getApplication(), "save", downloadJson);
=======
    super.onDestroy();
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
    if (mEventBus != null) {
      mEventBus.unregister(this);
    }
    mDownloadQueue.cancelAll();
<<<<<<< HEAD
    //重启服务
    Intent sevice = new Intent(this, CourseDownloadService.class);
    this.startService(sevice);
    super.onDestroy();
  }

  //当收到暂停或继续下载的event
  public void onEventMainThread(OnPuaseDownloadEvent event) {
    String pid = event.getWhat();
    String sectionUrl = event.getSectionUrl();
    if (DownloadRequests != null) {
      DownloadRequest downloadRequest = DownloadRequests.get(pid);
      if (downloadRequest == null) {
        String filename = pid + sectionUrl.substring(sectionUrl.lastIndexOf("."));
        addToDownloadQueue(pid, sectionUrl, filename);
      } else {
        //在下载的时候点击了,暂停当前下载
        if (downloadRequest.isStarted() && !downloadRequest.isFinished()) {
          downloadRequest.cancel();
        } else if (!downloadRequest.inQueue()) {
          String filename = pid + sectionUrl.substring(sectionUrl.lastIndexOf("."));
          addToDownloadQueue(pid, sectionUrl, filename);
        }
      }
    } else {
      if (DownloadRequests == null) {
        DownloadRequests = new HashMap<>();
      }
      String filename = pid + sectionUrl.substring(sectionUrl.lastIndexOf("."));
      addToDownloadQueue(pid, sectionUrl, filename);
    }
  }

  //当收到了EventBus的下载的消息
  public void onEventMainThread(SectionDownloadEvent event) {
    final String sectionUrl = event.getSectionUrl();
    final String sectionId = event.getSectionId();
=======
  }

  //当收到取消下载的
  public void onEventMainThread(ChangeDownLoadEvent event) {
    String pid = event.getPid();
    final String localPath = event.getLocalPath();
    //取消下载任务
    mDownloadQueue.cancelBySign(pid);
    mDownloadQueue.notify();
    Log.i("test", "下载任务取消");
  }


  //当收到了EventBus的下载的消息
  public void onEventMainThread(SectionDownloadEvent event) {
    if(mDownloadQueue.unFinishSize()>=3){
      ToastUtil.getInstant().show("亲，当前下载队列已满,请稍后尝试！");
      return;
    }
    ToastUtil.getInstant().show("已加入下载队列！");
    final String sectionUrl = event.getSectionUrl();
    final String sectionId = event.getSectionId();
    int pid = Integer.parseInt(sectionId);
    if (mSectionDownloadDao == null) {
      mSectionDownloadDao = new SectionDownloadDao(getApplication());
    }
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
    final String filename = sectionId + sectionUrl.substring(sectionUrl.lastIndexOf("."));
<<<<<<< HEAD
    CourseSectionEntity sectionEntity = mSectionDownloadDao
        .querySectionBySectionId(sectionId);
    sectionEntity.setLocalpath(mPath + filename);
    mSectionDownloadDao.upDataInfo(sectionEntity);
<<<<<<< HEAD
    ToastUtil.getInstant().show("已加入下载队列！");
=======
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
    //加入下载的队列中
    addToDownloadQueue(sectionId, sectionUrl, filename);
=======
    if (mDownloadQueue.unFinishSize() >= 3) {
      //排队等待
      addToWaite(sectionUrl, sectionId);
    } else {
      CourseSectionEntity sectionEntity = mSectionDownloadDao
          .querySectionBySectionId(sectionId);
      sectionEntity.setLocalpath(mPath + filename);
      mSectionDownloadDao.upDataInfo(sectionEntity);
      //加入下载的队列中
      addToDownloadQueue(sectionId, sectionUrl, filename);
    }
>>>>>>> dev
  }
  //加入排队等待
  private void addToWaite(final String sectionUrl, final String sectionId) {
    final String filename = sectionId + sectionUrl.substring(sectionUrl.lastIndexOf("."));
    Observable.create(new OnSubscribe<Object>() {
      @Override
      public void call(Subscriber<? super Object> subscriber) {
        CourseSectionEntity sectionEntity = mSectionDownloadDao
            .querySectionBySectionId(sectionId);
        sectionEntity.setState(-2);
        mSectionDownloadDao.upDataInfo(sectionEntity);
        SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
        downloadStateEvent.setState(3);
        subscriber.onNext(downloadStateEvent);

      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Object>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {

          }
          @Override
          public void onNext(Object o) {
            SectionDownloadStateEvent stateEvent = (SectionDownloadStateEvent) o;
            mEventBus.post(stateEvent);
            //加入下载的队列中
            addToDownloadQueue(sectionId, sectionUrl, filename);
            ToastUtil.getInstant().show("加入等待队列");
          }
        });
    return;
  }
  //开始下载
  public void addToDownloadQueue(String pid, String url, String name) {
<<<<<<< HEAD
    if (DownloadRequests == null) {
      DownloadRequests = new HashMap<>();
    }
    DownloadRequest downloadRequest = NoHttp
        .createDownloadRequest(url, mPath, name, true, true);
    //设置取消请求的标识
    DownloadRequests.put(pid, downloadRequest);
    //将任务加入队列
    mDownloadQueue.add(Integer.parseInt(pid), downloadRequest, mDownloadListener);
  }
<<<<<<< HEAD
=======
    DownloadRequest downloadRequest = NoHttp
        .createDownloadRequest(url, mPath, name, true, true);
    //设置取消请求的标识
    downloadRequest.setCancelSign(pid);
    //将任务加入队列
    mDownloadQueue.add(Integer.parseInt(pid), downloadRequest, mDownloadListener);
  }

>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
=======

>>>>>>> dev
  //文件下载的监听
  private DownloadListener mDownloadListener = new DownloadListener() {
    @Override
    public void onDownloadError(int what, Exception exception) {
      CourseSectionEntity sectionEntity = mSectionDownloadDao
          .querySectionBySectionId(what + "");
      sectionEntity.setState(-1);
      mSectionDownloadDao.upDataInfo(sectionEntity);
      SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
<<<<<<< HEAD
      downloadStateEvent.setState(-1);
=======
      downloadStateEvent.setState(1);
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
      //发送EvenBus下载中
      if (mEventBus != null) {
        mEventBus.post(downloadStateEvent);
      }
<<<<<<< HEAD
=======
      //发送Evenbus
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
      //提示出错信息
      if (exception instanceof ServerError) {
        mToastUtil.show(R.string.download_error_server);
      } else if (exception instanceof NetworkError) {
        mToastUtil.show(R.string.download_error_network);
      } else if (exception instanceof StorageReadWriteError) {
        mToastUtil.show(R.string.download_error_storage);
      } else if (exception instanceof StorageSpaceNotEnoughError) {
        mToastUtil.show(R.string.download_error_space);
      } else if (exception instanceof TimeoutError) {
        mToastUtil.show(R.string.download_error_timeout);
      } else if (exception instanceof UnKnownHostError) {
        mToastUtil.show(R.string.download_error_un_know_host);
      } else if (exception instanceof URLError) {
        mToastUtil.show(R.string.download_error_url);
      } else {
<<<<<<< HEAD
//        mToastUtil.show(R.string.download_error_un);
=======
        mToastUtil.show(R.string.download_error_un);
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
      }
    }

    @Override
    public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders,
        long allCount) {
      //更新数据库
      if (mSectionDownloadDao != null) {
        CourseSectionEntity courseSectionEntity = mSectionDownloadDao
            .querySectionBySectionId(what + "");
        courseSectionEntity.setState(1);
<<<<<<< HEAD
        mSectionDownloadDao.upDataInfo(courseSectionEntity);
      }
      //发送EvenBus下载中
      if (mEventBus != null) {
        SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
        downloadStateEvent.setState(0);
=======
//        courseSectionEntity.setLocalpath(mPath + filename);
        mSectionDownloadDao.upDataInfo(courseSectionEntity);
      }
      SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
      downloadStateEvent.setState(1);
      //发送EvenBus下载中
      if (mEventBus != null) {
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
        mEventBus.post(downloadStateEvent);
      }
    }

    @Override
    public void onProgress(int what, int progress, long fileCount, long speed) {
      //保存进度
      CourseSectionEntity sectionEntity = mSectionDownloadDao
          .querySectionBySectionId(what + "");
      if (sectionEntity != null) {
<<<<<<< HEAD
        sectionEntity.setProgress(progress + "");
        mSectionDownloadDao.upDataInfo(sectionEntity);
      }
      //发送EvenBus下载中
      if (mEventBus != null) {
        SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
        downloadStateEvent.setWhat(what + "");
        downloadStateEvent.setState(1);
        mEventBus.post(downloadStateEvent);
      }
    }

    @Override
    public void onFinish(final int what, final String filePath) {
      //在集合中移除改队列
      DownloadRequests.remove(what + "");
      //更新数据库
      if (mSectionDownloadDao == null) {
        return;
      }
      //Rxjava
      Observable.just(mSectionDownloadDao)
          .map(new Func1<SectionDownloadDao, SectionDownloadStateEvent>() {
            @Override
            public SectionDownloadStateEvent call(SectionDownloadDao SectionDownloadDao) {
              CourseSectionEntity courseSectionEntity = mSectionDownloadDao
                  .querySectionBySectionId(what + "");
              if (courseSectionEntity != null) {
                courseSectionEntity.setState(2);
                courseSectionEntity.setLocalpath(filePath);
                mSectionDownloadDao.upDataInfo(courseSectionEntity);
              }
              SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
              downloadStateEvent.setState(2);
              downloadStateEvent.setWhat(what + "");
              return downloadStateEvent;
            }
          })
          .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
          .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
          .subscribe(new Action1<SectionDownloadStateEvent>() {
            @Override
            public void call(SectionDownloadStateEvent sectionDownloadStateEvent) {
              if (mEventBus != null) {
                CourseSectionEntity sectionEntity = mSectionDownloadDao
                    .querySectionBySectionId(what + "");
                String s = sectionEntity.toString();
                mEventBus.post(sectionDownloadStateEvent);
              }
            }
          });
    }

    @Override
    public void onCancel(int what) {
      //保存到数据库状态3
=======
        sectionEntity.setProgress(progress + "%");
        mSectionDownloadDao.upDataInfo(sectionEntity);
      }
    }

    @Override
    public void onFinish(int what, String filePath) {
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
      //更新数据库
      if (mSectionDownloadDao != null) {
        CourseSectionEntity courseSectionEntity = mSectionDownloadDao
            .querySectionBySectionId(what + "");
        if (courseSectionEntity != null) {
<<<<<<< HEAD
          courseSectionEntity.setState(3);
          mSectionDownloadDao.upDataInfo(courseSectionEntity);
        }
      }
      //发送EvenBus下载中
      if (mEventBus != null) {
        SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
        downloadStateEvent.setWhat(what + "");
        downloadStateEvent.setState(3);
        mEventBus.post(downloadStateEvent);
      }
    }
=======
          courseSectionEntity.setState(2);
          courseSectionEntity.setLocalpath(filePath);
          mSectionDownloadDao.upDataInfo(courseSectionEntity);
        }
      }
      if (mEventBus != null) {
        SectionDownloadStateEvent downloadStateEvent = new SectionDownloadStateEvent();
        downloadStateEvent.setState(1);
        mEventBus.post(downloadStateEvent);
      }
    }

    @Override
    public void onCancel(int what) {
//        CheckDownloadQueueState();
    }
>>>>>>> f8c163e9f9b16c6f8465981156b159495b4df8c8
  };

  //检查下载队列中的状态
  private void CheckDownloadQueueState() {
    //队列中没有请求的,反注册服务
    if (mDownloadQueue.unFinishSize() == 0) {
      //停止服务
      stopSelf();
      if (mEventBus != null) {
        mEventBus.unregister(this);
      }
    }
  }
}
