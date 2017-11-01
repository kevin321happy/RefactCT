package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.transition.Visibility;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.model.CourseDetailModel;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.CourseDetailPresenter;
import com.wh.jxd.com.refactorqm.view.CourseDetailView;
import com.wh.jxd.com.refactorqm.view.widget.CourseVedioPlay;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin321vip on 2017/10/31.
 * 课程详情界面
 */

public class CourseDetailActivity extends BaseMvpActivity<CourseDetailPresenter, CourseDetailView> implements CourseDetailView {
    @Bind(R.id.toolbar_subtitle)
    TextView mToolbarSubtitle;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.iv_tvBg)
    ImageView mIvTvBg;
    @Bind(R.id.btn_start_study)
    Button mBtnStartStudy;
    @Bind(R.id.tv_study_chaptername)
    TextView mTvStudyChaptername;
    @Bind(R.id.fl_mask)
    FrameLayout mFlMask;
    @Bind(R.id.TabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.ll_content)
    LinearLayout mLlContent;
    @Bind(R.id.CustomGSYVideoPlayer)
    CourseVedioPlay mCustomGSYVideoPlayer;
    private CourseDetailPresenter mCourseDetailPresenter;
    private MenuItem mItem_share;
    private String mCourse_id;

    /**
     * 重复登陆了
     */
    @Override
    public void onTokenLose() {

    }


    @Override
    protected CourseDetailView creatView() {
        return this;
    }
    @Override
    public CourseDetailPresenter creatPersenter(Context context) {
        if (mCourseDetailPresenter == null) {
            mCourseDetailPresenter = new CourseDetailPresenter();
        }
        return mCourseDetailPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_detail;
    }
    @Override
    protected void initView() {
        setToolBarTitle("课程详情");
        Intent intent = getIntent();
//        mCourse_id= intent.getStringExtra("courseid");
        mCourse_id=intent.getStringExtra("course_id");
//        mCourse_id = getIntent().getStringExtra(getString(R.string.课程id));
        if (mCourseDetailPresenter==null){
            mCourseDetailPresenter=creatPersenter(this);
        }
        mCourseDetailPresenter.getCourseDetail(mCourse_id);
    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        mItem_share = menu.findItem(R.id.action_share);
        mItem_share.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //分享课程
            case R.id.action_share:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getCourseDetailSuccess(CourseDetailModel data) {

    }
}
