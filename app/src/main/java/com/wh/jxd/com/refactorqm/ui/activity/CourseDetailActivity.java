package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.db.ChaptersDao;
import com.wh.jxd.com.refactorqm.model.CourseDetailModel;
import com.wh.jxd.com.refactorqm.model.CourseInfo;
import com.wh.jxd.com.refactorqm.model.SectionInfo;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.CourseDetailPresenter;
import com.wh.jxd.com.refactorqm.ui.adapter.CourseDetailPagerAdapter;
import com.wh.jxd.com.refactorqm.ui.fragment.CourseDetailFragment;
import com.wh.jxd.com.refactorqm.ui.fragment.CourseMuLuFragment;
import com.wh.jxd.com.refactorqm.ui.fragment.RecommendCourseFragment;
import com.wh.jxd.com.refactorqm.view.CourseDetailView;
import com.wh.jxd.com.refactorqm.view.widget.CourseVedioPlay;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private ImageView mImageView;
    private String mVedio_url;
    private OrientationUtils orientationUtils;
    private List<Fragment> mFragments;
    private CourseDetailPagerAdapter mPagerAdapter;

    /**
     * 重复登陆了
     */
    @Override
    public void onTokenLose() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
        mCourse_id = intent.getStringExtra("course_id");
        if (mCourseDetailPresenter == null) {
            mCourseDetailPresenter = creatPersenter(this);
        }
        if (mCustomGSYVideoPlayer == null) {
            return;
        }
        if (mFragments == null) {
            mFragments = new ArrayList<>();
        }
        mFragments.clear();
        mFragments.add(setArgument(new CourseDetailFragment(), mCourse_id));
        mFragments.add(setArgument(new CourseMuLuFragment(), mCourse_id));
        mFragments.add(setArgument(new RecommendCourseFragment(), mCourse_id));

        mPagerAdapter = new CourseDetailPagerAdapter(getSupportFragmentManager(), (ArrayList<Fragment>) mFragments);
        mViewpager.setAdapter(mPagerAdapter);
        mViewpager.setCurrentItem(1);
        mTabLayout.setupWithViewPager(mViewpager);
        //设置指示器的宽度
        mCourseDetailPresenter.setIndicator(mTabLayout, 25, 25);


        mCourseDetailPresenter.initCourseVedioCongif(this, mCustomGSYVideoPlayer);
        mCourseDetailPresenter.getCourseDetail(mCourse_id);
    }

    /**
     * 给fragment传递参数
     */
    private Fragment setArgument(Fragment fragment, String argument) {
        Bundle args = new Bundle();
        args.putString(getString(R.string.课程id), argument);
        fragment.setArguments(args);
        return fragment;
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
        ChaptersDao chaptersDao = new ChaptersDao();
        switch (item.getItemId()) {
            //分享课程
            case R.id.action_share:
                ArrayList<SectionInfo> sectionInfos = chaptersDao.queryAllSectionByCourseID(mCourse_id);
                for (SectionInfo sectionInfo : sectionInfos) {
                    KLog.d("该课程下的章节的信息：" + sectionInfo.toString());
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getCourseDetailSuccess(CourseDetailModel data) {
        CourseInfo coursr_detail = data.getCoursr_detail();
        SectionInfo first_chapter = data.getFirst_chapter();
        mVedio_url = first_chapter.getUrl();
        mIvTvBg.setVisibility(View.VISIBLE);
        String courseImage = coursr_detail.getCourseImage();
        Glide.with(this).load(courseImage).into(mIvTvBg);
        mCustomGSYVideoPlayer.getBackButton().setVisibility(View.INVISIBLE);
        mCustomGSYVideoPlayer.setUp(mVedio_url, false, "");
        showCourseInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCourseDetailPresenter.releaseVedio();
    }

    @Override
    public void onPrepared(String url) {

    }

    @Override
    public void onStopPlay(String url) {

    }

    /**
     * 显示课程信息
     */
    private void showCourseInfo() {
    }

    @OnClick(R.id.btn_start_study)
    public void onViewClicked() {
        if (mFlMask != null) {
            mFlMask.setVisibility(View.GONE);
        }
        mCustomGSYVideoPlayer.startPlayLogic();
    }
}
