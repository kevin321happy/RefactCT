package com.wh.jxd.com.refactorqm.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.common.Constance;
import com.wh.jxd.com.refactorqm.model.CategoryInfo;
import com.wh.jxd.com.refactorqm.model.EntBannerInfo;
import com.wh.jxd.com.refactorqm.model.EnterpriseDataModel;
import com.wh.jxd.com.refactorqm.model.EnterpriseInfo;
import com.wh.jxd.com.refactorqm.model.MemberRankModel;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.EnterpriseFragmentPresenterImpl;
import com.wh.jxd.com.refactorqm.ui.activity.LoginActivity;
import com.wh.jxd.com.refactorqm.ui.activity.WebViewActivity;
import com.wh.jxd.com.refactorqm.ui.adapter.ButtomMenuAdapter;
import com.wh.jxd.com.refactorqm.utils.FrescoUtils;
import com.wh.jxd.com.refactorqm.view.EnterpriseFragmentView;
import com.wh.jxd.com.refactorqm.view.widget.CapCircleprogressView;
import com.wh.jxd.com.refactorqm.view.widget.CircleImageView;
import com.wh.jxd.com.refactorqm.view.widget.MyGridView;
import com.wh.jxd.com.refactorqm.view.widget.SpringScrollview;
import com.wh.jxd.com.refactorqm.view.widget.waterwaveprogress.WaterWaveProgress;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by kevin321vip on 2017/9/28.
 * 企业首页界面
 */

public class EnterpriseFragment extends BaseMvpFragment<EnterpriseFragmentPresenterImpl, EnterpriseFragmentView> implements EnterpriseFragmentView, ButtomMenuAdapter.onButtomMenuClick {

    @Bind(R.id.binner)
    BGABanner mBinner;
    @Bind(R.id.iv_imaeBG)
    SimpleDraweeView mIvImaeBG;
    @Bind(R.id.iv_enterprise_head)
    CircleImageView mIvEnterpriseHead;
    @Bind(R.id.fram_entinfo)
    FrameLayout mFramEntinfo;
    @Bind(R.id.tv_enterprise_notice)
    TextView mTvEnterpriseNotice;
    @Bind(R.id.ll_enterprise_notice)
    LinearLayout mLlEnterpriseNotice;
    @Bind(R.id.gv_enterprise_info)
    MyGridView mGvEnterpriseInfo;
    @Bind(R.id.cap_study_rank)
    CapCircleprogressView mCapStudyRank;
    @Bind(R.id.tv_studytime_rank)
    TextView mTvStudytimeRank;
    @Bind(R.id.wave_score_average)
    WaterWaveProgress mWaveScoreAverage;
    @Bind(R.id.tv_score_rank)
    TextView mTvScoreRank;
    @Bind(R.id.rcy_bottom_menu)
    RecyclerView mRcyBottomMenu;
    @Bind(R.id.sv_enterprise_info)
    SpringScrollview mSvEnterpriseInfo;
    private EnterpriseFragmentPresenterImpl mFragmentPresenter;
    private List<String> mBanner_list = new ArrayList<>();
    private EnterpriseInfo mEnterpriseinfo;
    private List<CategoryInfo> mCategoryInfos;
    private MyGridViewAdapter mGridViewAdapter;
    private long mMax_studytime;


    private int START = 100;
    private int WAVE_START = 200;
    public int progress = 0;
    public int wave_progress = 0;
    private MemberRankModel mMember_rank;
    private int mStudy_time_progress;
    private long mScore_rate;
    private long mStudy_time;
    private ButtomMenuAdapter mMenuAdapter;
    private String mEnid;//企业id;
    private boolean HAS_NEWCOURSE;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    progress++;
                    if (progress <= mStudy_time_progress) {
                        mCapStudyRank.setCurrentProgress(progress);
                        sendEmptyMessageDelayed(START, 50);
                    }
                    break;
                case 200:
                    wave_progress++;
                    if (wave_progress <= mScore_rate) {
                        mWaveScoreAverage.setProgress(wave_progress);
                        sendEmptyMessageDelayed(WAVE_START, 50);
                    }
                    break;
            }
        }
    };

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //加载数据
        if (mFragmentPresenter == null) {
            mFragmentPresenter = creatP();
        }
        mFragmentPresenter.onLoadData();

        mGridViewAdapter = new MyGridViewAdapter();
        mGvEnterpriseInfo.setAdapter(mGridViewAdapter);
//        mGvEnterpriseInfo.setOnItemClickListener(mOnItemClickListener);
        mBanner_list = new ArrayList<>();
        mBinner.setAllowUserScrollable(true);
        mBinner.setOverScrollMode(2);

        mWaveScoreAverage.setShowProgress(true);
        mWaveScoreAverage.animateWave();
        mFragmentPresenter.getButtomMenuData();

        mMenuAdapter = new ButtomMenuAdapter(mFragmentPresenter.getButtomMenuData());
        mMenuAdapter.setOnButtomMenuClick(this);
        mRcyBottomMenu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRcyBottomMenu.setAdapter(mMenuAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enterprise;
    }

    @Override
    protected EnterpriseFragmentView creatV() {
        return this;
    }

    @Override
    protected EnterpriseFragmentPresenterImpl creatP() {
        if (mFragmentPresenter == null) {
            mFragmentPresenter = new EnterpriseFragmentPresenterImpl();
        }
        return mFragmentPresenter;
    }

    @Override
    public void onLoadSuccess(EnterpriseDataModel data) {
        if (data == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        showEnterpriseInfo(data);
    }

    /**
     * 显示企业信息
     *
     * @param data
     */
    private void showEnterpriseInfo(EnterpriseDataModel data) {
        List<EntBannerInfo> bannerList = data.getBannerList();
        mEnterpriseinfo = data.getEnterpriseinfo();
        String elogo = mEnterpriseinfo.getElogo();
        //显示企业的Banner
        showBanner(bannerList, elogo);
        //显示分类课程
        mCategoryInfos = data.getCategoryList();
        showCatgory();
        //显示学习排名
        mMember_rank = data.getMember_rank();
        showRankInfo(mMember_rank);
//        upDataCourseStaus();

    }

    /**
     * 显示学习排名信息
     * @param member_rank
     */
    private void showRankInfo(MemberRankModel member_rank) {
        if (member_rank == null){
            return;
        }
        mStudy_time = creatP().formatString2Long(member_rank.getStudy_time());
        mMax_studytime = creatP().formatString2Long(member_rank.getStudy_time_max());
        if (mMax_studytime == 0) {
            mStudy_time_progress = 0;
        } else {
            mStudy_time_progress = (int) (mStudy_time * 100 / mMax_studytime);
        }
        if (mStudy_time < 60) {
            mCapStudyRank.setText("0", "分钟");
        } else if (mStudy_time > 60000) {
            //小时
            mCapStudyRank.setText(mStudy_time / 3600 + "", "小时");
        } else {
            //分钟
            mCapStudyRank.setText(mStudy_time / 60 + "", "分钟");
        }
        String total_member = member_rank.getTotal_member();
        mTvStudytimeRank.setText(member_rank.getStudy_time_rank() + "名/" + total_member + "人");
        mTvScoreRank.setText(member_rank.getScore_rate_rank() + "名/" + total_member + "人");
        mScore_rate=creatP().formatString2Long(member_rank.getScore_rate());
        mHandler.sendEmptyMessageDelayed(START, 100);
        mHandler.sendEmptyMessageDelayed(WAVE_START, 100);
    }

    /**
     * 显示分类
     */
    private void showCatgory() {
        if (mCategoryInfos != null && mCategoryInfos.size() > 0) {
            mGridViewAdapter.notifyDataSetChanged();
        }
    }


    //显示录播图
    private void showBanner(final List<EntBannerInfo> bannerList, String elogo) {
        if (mBinner == null) {
            return;
        }
        if (bannerList == null || bannerList.size() == 0) {
            mBinner.setVisibility(View.INVISIBLE);
            showEnterpriseLogo(elogo);
            return;
        }
        mFramEntinfo.setVisibility(View.GONE);
        mBinner.setVisibility(View.VISIBLE);
        mBanner_list.clear();
        for (EntBannerInfo entBannerInfo : bannerList) {
            mBanner_list.add(entBannerInfo.getImg_url());
        }
        mBinner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                Glide.with(getActivity()).load(mBanner_list.get(position)).into(((ImageView) view));
            }
        });
        mBinner.setData(mBanner_list, null);
        mBinner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                if (!TextUtils.isEmpty(bannerList.get(position).getJump_url())) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("key", 12);
                    intent.putExtra("position", bannerList.get(position).getJump_url() + "");
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 显示企业的Logo
     *
     * @param elogo
     */
    private void showEnterpriseLogo(String elogo) {
        //背景高斯模糊显示
        String ImaUrl = elogo == null ? Constance.DEFAULT_ICON : elogo;
        if (mIvImaeBG != null) {
            FrescoUtils.showUrlBlur(mIvImaeBG, ImaUrl, 5, 10);
        }
        if (mIvEnterpriseHead != null) {
            Glide.with(getActivity()).load(ImaUrl).into(mIvEnterpriseHead);
        }
    }

    @Override
    public void onTokenLose() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void onMenuClick(int position) {

    }

    /**
     * 显示课程分类的adapter
     */
    private class MyGridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mCategoryInfos == null || mCategoryInfos.size() == 0) {
                return 3;
            } else {
                return mCategoryInfos.size() + 3;
            }
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.item_cottage, null);
            //自动布局
            AutoUtils.autoSize(view);
            CircleImageView imagView = (CircleImageView) view.findViewById(R.id.iv_image);
            TextView textview = (TextView) view.findViewById(R.id.tv_text);
            ImageView iv_new = (ImageView) view.findViewById(R.id.iv_new);
            int listSize = mCategoryInfos == null ? 0 : mCategoryInfos.size();
            if (position == 0) {
                iv_new.setVisibility(HAS_NEWCOURSE ? View.VISIBLE : View.INVISIBLE);
                imagView.setImageResource(R.drawable.ic_ent_taskcourse);
                textview.setText("我的课程");
            } else if (position == listSize + 1) {
                imagView.setImageResource(R.drawable.ic_live);
                textview.setText("直播课程");
            } else if (position == listSize + 2) {
                imagView.setImageResource(R.drawable.ic_all_course);
                textview.setText("显示全部");
            } else {
                if (mCategoryInfos != null && mCategoryInfos.get(position - 1) != null) {
                    Glide.with(getActivity()).load(mCategoryInfos.get(position - 1)).into(imagView);
//                    ImageLoader.getInstance().displayImage(mCategoryInfos.get(position - 1).getCategory_image(), imagView);
                    textview.setText(mCategoryInfos.get(position - 1).getCategory_name());
                }
            }
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
