package com.wh.jxd.com.refactorqm.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseActivtiy;
import com.wh.jxd.com.refactorqm.common.Constance;
import com.wh.jxd.com.refactorqm.utils.CommonUtils;
import com.wh.jxd.com.refactorqm.utils.NetUtils;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;

import java.util.HashMap;

import butterknife.Bind;

/**
 * Created by kevin321vip on 2017/10/19.
 */

public class WebViewActivity extends BaseActivtiy {
    @Bind(R.id.toolbar_subtitle)
    TextView mToolbarSubtitle;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.webview)
    WebView mWebview;
    private int key;
    private String link;
    private String Web_Url;
    private int currentProgress;
    private boolean isAnimStart;
    private String job_ability_mode_id;
    private int mKey;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKey = getIntent().getIntExtra("key", -1);
        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        //mWebview.addJavascriptInterface(new JsInteration(), "android");
        if (mKey != -1) {
            switch (mKey) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
//                    Web_Url = Constance.H5_URL + "department.html" + "?entid=" + entid + "&deptid=" + activityId;
                    break;
                case 6:
//                    Web_Url = Constants.H5_URL + "studyMap.html?userid=" + PreferenceUtils.getUserId() + "&entid="
//                            + entid;
                    break;
                case 8:
//                    Web_Url = Constants.H5_URL + "capacity_model.html?userid=" + PreferenceUtils.getUserId();
                    break;
                case 9:
                    //活动去考试
//                    Web_Url = Constants.H5_URL + "start_exam.html?userid=" + PreferenceUtils.getUserId()
//                            + "&paperid=" + paperid;
                    break;
                case 10: //评估
//                    Web_Url = Constants.H5_URL + "questionnaire.html?userid=" + PreferenceUtils.getUserId()
//                            + "&activityid=" + activityId;
                    break;
                case 11:
                    //企业我的课程考试
//                    Web_Url = Constants.H5_URL + "start_exam.html?userid=" + PreferenceUtils.getUserId()
//                            + "&paperid=" + paperid + "&courseid=" + sessoinid;//sessoinid其实就是courseid
                    break;
                case 12:
                    //banner


                    if (TextUtils.isEmpty(link)) {
                        //Web_Url = Constance.H5_URL + "banner.html?cardid=" + posstion;
                    } else {
                        Web_Url = link;
                    }
                    break;
                case 13:
                    Web_Url = "http://www.52qmct.com";
                    break;
                case 14:
                    //关于我们
//                    setToolBarTitle("关于我们");
                    hideToolBar();
                    Web_Url = Constance.H5_URL + "aboutUs.html";
                    break;
                case 15:
//                    Web_Url =
//                            Constance.H5_URL + "chapterInfo.html?courseid=" + activityId + "&sessoinid="
//                                    + sessoinid;
                    break;
                case 16:
                    //问卷的id
//                    Web_Url =
//                            SyncStateContract.Constants.H5_URL + "research.html?userid=" + PreferenceUtils.getUserId() + "&id="
//                                    + sessoinid + "&type=" + type;
                    break;
                case 17:
                    //关于协议
//                    Web_Url = Constants.H5_URL + "agreement.html";
                    break;
                case 18:
                    setToolBarTitle("帮助与反馈");
                    Web_Url = Constance.H5_URL+"help.html";
                    break;
                case 19:
//                    navigationBar.setTitle("错题练习");
//                    Web_Url =
//                            Constants.H5_URL + "test_error.html?userid=" + PreferenceUtils.getUserId() + "&courseId="
//                                    + mCourse_id;
                    break;
                case 20:
                    break;
                case 21:
                    break;
                default:
                    break;
            }
            //加载网页
            String signStr = getSignData();
            if (Web_Url != null && signStr != null) {
                String H5_url;
                if (Web_Url.contains("?")) {
                    H5_url = Web_Url + "&signStr=" + signStr;
                } else {
                    H5_url = Web_Url + "?signStr=" + signStr;
                }
                mWebview.loadUrl(H5_url);
            }
        }
        // 获取网页加载进度
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                currentProgress = mProgressBar.getProgress();
                if (newProgress >= 100 && !isAnimStart) {
                    // 防止调用多次动画
                    isAnimStart = true;
                    mProgressBar.setProgress(newProgress);
                    // 开启属性动画让进度条平滑消失
                    startDismissAnimation(mProgressBar.getProgress());
                } else {
                    // 开启属性动画让进度条平滑递增
                    startProgressAnimation(newProgress);
                }
            }
        });
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (14 == key || 2 == key) {
                    //TODO 隐藏导航栏
//                    navigationBar.setVisibility(View.GONE);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view, String url) {
                view.loadUrl(url);
                if (url.contains("www.app.com")) {
                    view.loadUrl(
                            Constance.H5_URL + "capacity_model.html?userid=" + PreferenceUtils.getUserId());
                    int indexStart = url.indexOf("=");
                    job_ability_mode_id = url.substring(indexStart + 1);
                    //能力模型推荐的课程
//                    Intent intent = new Intent(WebViewActivity.this, CourseResultActivity.class);
//                    intent.putExtra("page", "1");
//                    intent.putExtra(getString(R.string.能力模型的id), job_ability_mode_id); //方便 课程页面的返回调用
//                    startActivityForResult(intent, 1024);
//                    finish();
                }
                if (url.contains("www.course.com")) {
                    int indexStart = url.indexOf("=");
                    String courseid = url.substring(indexStart + 1);
//                    Intent intent = new Intent(WebviewActivity.this, CourseDetailActivity.class);
//                    intent.putExtra("courseId", courseid); //方便 课程页面的返回调用
//                    startActivity(intent);
//                    finish();
                }
                if (url.contains("www.baidu.com")) {
                    finish();
                }
                if (url.contains("www.ideaback.com")) {
                    //跳到意见反馈
                    Intent intent = new Intent(WebViewActivity.this, FeedbackActivity.class);
                    startActivity(intent);
                    finish();
                }
                if (url.contains("www.addqqgroup.com")) {
                    //发起添加qq群
//                    joinQQGroup();
                    finish();
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //显示进度条
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(0);
                mProgressBar.setAlpha(1.0f);
            }
        });
    }

    //本地获取签名数据
    private String getSignData() {
        StringBuilder stringBuilder = new StringBuilder();
        String timestamp = CommonUtils.getCurrentTimestamp();
        HashMap<String, String> map = new HashMap<>();
        map.put("timestamp", timestamp);
        String[] signData = NetUtils.getSignData(WebViewActivity.this, map);
        return stringBuilder.append(timestamp).append("$").
                append(signData[0]).append("$").append(signData[1]).toString();
    }

    /**
     * progressBar递增动画
     */
    private void startProgressAnimation(int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(mProgressBar, "progress", currentProgress, newProgress);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    /**
     * progressBar消失动画
     */
    private void startDismissAnimation(final int progress) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mProgressBar, "alpha", 1.0f, 0.0f);
        anim.setDuration(100);  // 动画时长
        anim.setInterpolator(new DecelerateInterpolator());     // 减速
        // 关键, 添加动画进度监听器
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();      // 0.0f ~ 1.0f
                int offset = 100 - progress;
                mProgressBar.setProgress((int) (progress + offset * fraction));
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束
                mProgressBar.setProgress(0);

                mProgressBar.setVisibility(View.GONE);
                isAnimStart = false;
            }
        });
        anim.start();
    }


    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }


}
