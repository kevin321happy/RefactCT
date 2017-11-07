package com.wh.jxd.com.refactorqm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.model.ActivityInfo;
import com.wh.jxd.com.refactorqm.model.CourseInfo;
import com.wh.jxd.com.refactorqm.model.GridViewBean;
import com.wh.jxd.com.refactorqm.model.HomeInfo;
import com.wh.jxd.com.refactorqm.model.TeacherCourse;
import com.wh.jxd.com.refactorqm.model.TopImgInfo;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.HomeFragmentPresenterImpl;
import com.wh.jxd.com.refactorqm.ui.activity.CourseDetailActivity;
import com.wh.jxd.com.refactorqm.ui.activity.LecturerDetailActivity;
import com.wh.jxd.com.refactorqm.ui.activity.LoginActivity;
import com.wh.jxd.com.refactorqm.ui.adapter.HotCourseAdapter;
import com.wh.jxd.com.refactorqm.ui.adapter.RecommendCourseAdapter;
import com.wh.jxd.com.refactorqm.ui.adapter.RecommendTeacherAdapter;
import com.wh.jxd.com.refactorqm.view.HomeFragmentView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class HomeFragment extends BaseMvpFragment<HomeFragmentPresenterImpl, HomeFragmentView> implements HomeFragmentView, RecommendCourseAdapter.OnClickListener, HotCourseAdapter.OnClickListener, RecommendTeacherAdapter.onClickListener {
    @Bind(R.id.binner)
    BGABanner mBinner;
    @Bind(R.id.gv_view)
    GridView mGvView;
    @Bind(R.id.iv_more_recommend_course)
    ImageView mIvMoreRecommendCourse;
    @Bind(R.id.rv_recommend_course)
    RecyclerView mRvRecommendCourse;
    @Bind(R.id.iv_more_hot_course)
    ImageView mIvMoreHotCourse;
    @Bind(R.id.rv_hot_course)
    RecyclerView mRvHotCourse;
    @Bind(R.id.iv_more_recomment_teacher)
    ImageView mIvMoreRecommentTeacher;
    @Bind(R.id.rv_recommend_teacher)
    RecyclerView mRvRecommendTeacher;
    @Bind(R.id.rl_one)
    LinearLayout mRlOne;
    @Bind(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;
    private HomeFragmentPresenterImpl mHomeFragmentPersenter;
    private boolean ISREFRESHING;//是否正在刷新中.....

    GridViewBean[] mGridViewBeans = {new GridViewBean(R.drawable.ic_all_course, "所有课程")
            , new GridViewBean(R.drawable.ic_all_teacher, "所有讲师"),
            new GridViewBean(R.drawable.ic_activity_zone, "活动专区")
            , new GridViewBean(R.drawable.ic_teacher_replay, "申请合作")};
    private RecommendCourseAdapter mRecommendAdapter;//推荐课程
    private HotCourseAdapter mHotAdapter;
    private RecommendTeacherAdapter mTeacherAdapter;

    private ArrayList<CourseInfo> mRecommendCourseList;
    private ArrayList<CourseInfo> mHotCourseList;
    private ArrayList<TeacherCourse> mRecommendTeacherList;
    private ArrayList<ActivityInfo> mActivityList;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //加载首页的数据
        if (mHomeFragmentPersenter != null) {
            mHomeFragmentPersenter.loadHomeData();
        }
        //设置下拉刷新的头部
        SinaRefreshView headerView = new SinaRefreshView(getActivity());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayout.setEnableLoadmore(false);
        mRefreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(getActivity());
        mRefreshLayout.setBottomView(loadingView);
        //设置下拉监听
        mRefreshLayout.setOnRefreshListener(mRefreshListenerAdapter);
        mGvView.setAdapter(new MyGridViewAdapter());
        // 推荐课程
        mRecommendAdapter = new RecommendCourseAdapter(getActivity());
        mRvRecommendCourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvRecommendCourse.setAdapter(mRecommendAdapter);
        mRecommendAdapter.setOnClickListener(this);
        //热门课程
        mHotAdapter = new HotCourseAdapter(getActivity());
        mRvHotCourse.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRvHotCourse.setAdapter(mHotAdapter);
        mHotAdapter.setOnClickListener(this);

        //推荐讲师
        mTeacherAdapter = new RecommendTeacherAdapter(getActivity());
        mRvRecommendTeacher.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvRecommendTeacher.setAdapter(mTeacherAdapter);
        mTeacherAdapter.setOnClickListener(this);

    }

    private RefreshListenerAdapter mRefreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            ISREFRESHING = true;
            //下拉的时候
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mHomeFragmentPersenter != null) {
                        mHomeFragmentPersenter.loadHomeData();
                    }
                }
            }, 1500);
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeFragmentView creatV() {
        return this;
    }

    @Override
    protected HomeFragmentPresenterImpl creatP() {
        if (mHomeFragmentPersenter == null) {
            mHomeFragmentPersenter = new HomeFragmentPresenterImpl();
        }
        return mHomeFragmentPersenter;
    }

    @Override
    public void onLoadSuccess(HomeInfo homeInfo) {
        if (mRefreshLayout != null && ISREFRESHING == true) {
            mRefreshLayout.finishRefreshing();
            ISREFRESHING = false;
        }
        //设置数据
        upDataui(homeInfo);

    }

    /**
     * 更新界面
     *
     * @param homeInfo
     */
    private void upDataui(HomeInfo homeInfo) {
        try {
            mRlOne.setVisibility(View.VISIBLE);
            final List<TopImgInfo> topimg = homeInfo.getTopImg();
            if (topimg.size() > 0) {
                final List<String> list = new ArrayList<String>();
                for (int a = 0; a < topimg.size(); a++) {
                    list.add(topimg.get(a).getCarimg());
                }
                mBinner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                        Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
                    }
                });
                mBinner.setData(list, null);
                mBinner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                    }
                });
            }
//          //推荐课程
            mRecommendCourseList = (ArrayList<CourseInfo>) homeInfo.getCourseList1();
            mRecommendAdapter.setListItems(mRecommendCourseList);
            mRecommendAdapter.notifyDataSetChanged();
//          //热门课程
            mHotCourseList = (ArrayList<CourseInfo>) homeInfo.getCourseList3();
            mHotAdapter.setListitems(mHotCourseList);
            mHotAdapter.notifyDataSetChanged();
//          //推荐讲师
            mRecommendTeacherList = (ArrayList<TeacherCourse>) homeInfo.getTeacherList();
            mTeacherAdapter.setListItems(mRecommendTeacherList);
            mTeacherAdapter.notifyDataSetChanged();
            //推荐活动
            mActivityList = (ArrayList<ActivityInfo>) homeInfo.getActivityList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadFail(String info) {
        if (mRefreshLayout != null && ISREFRESHING == true) {
            mRefreshLayout.finishRefreshing();
            ISREFRESHING = false;
        }
    }

    @Override
    public void onTokenLose() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, 111);
    }

    /**
     * 点击了推荐课程
     *
     * @param position
     * @param courseId
     */
    @Override
    public void onRecommendCourseClickListener(int position, String courseId) {
        Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
        intent.putExtra("course_id", courseId);
        startActivity(intent);
    }

    /**
     * 热门课程
     *
     * @param position
     * @param courseId
     */
    @Override
    public void onHotCourseClickListener(int position, String courseId) {
        Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
        intent.putExtra("course_id", courseId);
        startActivity(intent);
    }

    /**
     * 推荐讲师
     *
     * @param position
     * @param teacherid
     */
    @Override
    public void onTeacherItemClick(int position, int teacherid) {
        Intent intent = new Intent(getActivity(), LecturerDetailActivity.class);
        intent.putExtra(getString(R.string.讲师id), teacherid);
        startActivity(intent);

    }

    private class MyGridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mGridViewBeans.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(parent.getContext(), R.layout.item_categry, null);
            GridViewBean mGridViewBean = mGridViewBeans[position];
            mGridViewBean.toString();
            AutoUtils.autoSize(view);
            ImageView imagView = (ImageView) view.findViewById(R.id.iv_image);
            TextView textview = (TextView) view.findViewById(R.id.tv_text);
            imagView.setImageResource(mGridViewBeans[position].getRes());
            textview.setText(mGridViewBeans[position].getTitle());
            return view;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
