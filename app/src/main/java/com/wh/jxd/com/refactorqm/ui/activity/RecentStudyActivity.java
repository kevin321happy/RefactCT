package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseActivtiy;
import com.wh.jxd.com.refactorqm.db.RecentStudyDao;
import com.wh.jxd.com.refactorqm.model.RecentStudyEntity;
import com.wh.jxd.com.refactorqm.ui.adapter.RecentStudyAdapter;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin321vip on 2017/11/3.
 */

public class RecentStudyActivity extends BaseActivtiy implements RecentStudyAdapter.onCourseItemClickListener {
    @Bind(R.id.toolbar_subtitle)
    TextView mToolbarSubtitle;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rcv)
    RecyclerView mRcv;
    private RecentStudyDao mRecentStudyDao;
    private List<RecentStudyEntity> mStudyEntities;
    private RecentStudyAdapter mRecentStudyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recent_study;
    }

    @Override
    protected void initView() {
        setToolBarTitle("最近学习");
        mRecentStudyAdapter = new RecentStudyAdapter();
        mRecentStudyAdapter.setOnCourseItemClickListener(this);
        mRcv.setLayoutManager(new LinearLayoutManager(this));
        mRcv.setAdapter(mRecentStudyAdapter);
        mRecentStudyDao = new RecentStudyDao();
        mStudyEntities = mRecentStudyDao.queryRecentStudy();
        if (mStudyEntities != null && mStudyEntities.size() > 0) {
            mRecentStudyAdapter.setData(mStudyEntities);
        }
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
    public void onCourseItemClick(String course_id) {
        ToastUtils.showShortToast(this,"这门课的ID:"+course_id);

        Intent intent = new Intent(this, CourseDetailActivity.class);
        intent.putExtra("course_id", course_id);
        startActivity(intent);
//        Intent intent = new Intent(this, CourseDetailActivity.class);
//        intent.putExtra("course_id", course_id);
//        startActivity(intent);
//        Intent intent = new Intent(RecentStudyActivity.this, CourseDetailActivity.class);
//        intent.putExtra("course_id", course_id);
//        startActivity(intent);
    }
}
