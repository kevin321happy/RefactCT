package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.model.TeacherModel;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.LecturerDetailPresenter;
import com.wh.jxd.com.refactorqm.ui.adapter.TeacherDetailAdapter;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.DiscreteScrollView.DiscreteScrollView;
import com.wh.jxd.com.refactorqm.view.DiscreteScrollView.InfiniteScrollAdapter;
import com.wh.jxd.com.refactorqm.view.DiscreteScrollView.Orientation;
import com.wh.jxd.com.refactorqm.view.DiscreteScrollView.transform.ScaleTransformer;
import com.wh.jxd.com.refactorqm.view.LecturerDetailView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin321vip on 2017/11/6.
 * 讲师详情
 */

public class LecturerDetailActivity extends BaseMvpActivity<LecturerDetailPresenter, LecturerDetailView> implements LecturerDetailView, DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder> {
    @Bind(R.id.toolbar_subtitle)
    TextView mToolbarSubtitle;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.dis_teacher_item)
    DiscreteScrollView mDisTeacherItem;
    @Bind(R.id.tv_teacher_name)
    TextView mTvTeacherName;
    @Bind(R.id.tv_teacher_introduce)
    TextView mTvTeacherIntroduce;
    @Bind(R.id.tv_teacher_labe)
    TextView mTvTeacherLabe;
    @Bind(R.id.btn_enter_study)
    Button mBtnEnterStudy;
    private String mTeache_id;
    private LecturerDetailPresenter mLecturerDetailPresenter;
    private InfiniteScrollAdapter<TeacherDetailAdapter.ViewHolder> mInfiniteAdapter;
    private List<TeacherModel> mTeacher_list;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_detail;
    }

    @Override
    protected void initView() {
        setToolBarTitle("讲师详情");
        mTeache_id = getIntent().getStringExtra(getString(R.string.讲师id));
        if (mTeache_id == null) {
            mTeache_id = "13";
        }
        if (mLecturerDetailPresenter == null) {
            mLecturerDetailPresenter = creatPersenter(this);
        }
        mDisTeacherItem.setOrientation(Orientation.HORIZONTAL);
        mDisTeacherItem.addOnItemChangedListener(this);
        mLecturerDetailPresenter.loadTeacherData(mTeache_id);
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
    protected LecturerDetailView creatView() {
        return this;
    }

    @Override
    public LecturerDetailPresenter creatPersenter(Context context) {
        if (mLecturerDetailPresenter == null) {
            mLecturerDetailPresenter = new LecturerDetailPresenter();
        }
        return mLecturerDetailPresenter;
    }

    @Override
    public void onTokenLose() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 加载到了讲师数据
     *
     * @param data
     */
    @Override
    public void loadTeacherDataSuccess(List<TeacherModel> data) {
        if (data != null && data.size() > 0) {
            showTeacheData(data);
        }
    }
    /**
     * 显示讲师数据
     * @param data
     */
    private void showTeacheData(List<TeacherModel> data) {
        this.mTeacher_list=data;
        mInfiniteAdapter = InfiniteScrollAdapter.wrap(new TeacherDetailAdapter(data));
        mDisTeacherItem.setAdapter(mInfiniteAdapter);
        mDisTeacherItem.setItemTransitionTimeMillis(150);
        mDisTeacherItem.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }


    @Override
    public void onLoadFail(String info) {
        ToastUtils.showShortToast(this, info);
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = mInfiniteAdapter.getRealPosition(adapterPosition);
        showCurrentTeacherInfo(positionInDataSet);
    }

    /**
     * 显示当前的数据
     * @param positionInDataSet
     */
    private void showCurrentTeacherInfo(int positionInDataSet) {
        if (positionInDataSet < mTeacher_list.size()) {
            TeacherModel teacherCourse = mTeacher_list.get(positionInDataSet);
            if (teacherCourse == null){
                return;
            }
            mTeache_id = teacherCourse.getTeacher_id();
            mTvTeacherName.setText(teacherCourse.getTeacher_name() == null ? "" : teacherCourse.getTeacher_name());
            mTvTeacherIntroduce.setText(teacherCourse.getTeacher_info() == null ? "" : teacherCourse.getTeacher_info());
            mTvTeacherLabe.setText(teacherCourse.getTeacher_field() == null ? "" : teacherCourse.getTeacher_field());
        }
    }
}
