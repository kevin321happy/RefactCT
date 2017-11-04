package com.wh.jxd.com.refactorqm.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpFragment;
import com.wh.jxd.com.refactorqm.model.ChapterInfo;
import com.wh.jxd.com.refactorqm.model.CourseMuluTitle;
import com.wh.jxd.com.refactorqm.model.SectionInfo;
import com.wh.jxd.com.refactorqm.model.event.SectionClickEvent;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.MuLuFragmentPresenter;
import com.wh.jxd.com.refactorqm.ui.adapter.CourseMuluAdapter;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.MuLuFragmentView;

import org.greenrobot.eventbus.EventBus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by kevin321vip on 2017/11/1.
 */

public class CourseMuLuFragment extends BaseMvpFragment<MuLuFragmentPresenter, MuLuFragmentView> implements MuLuFragmentView, CourseMuluAdapter.OnSectionItemClickListener {

    @Bind(R.id.tv_left_room)
    TextView mTvLeftRoom;
    @Bind(R.id.ll_download_manage)
    LinearLayout mLlDownloadManage;
    @Bind(R.id.ex_listview)
    ExpandableListView mExListview;
    @Bind(R.id.tv_collect)
    TextView mTvCollect;
    @Bind(R.id.tv_buy_course)
    TextView mTvBuyCourse;
    @Bind(R.id.ll_bottom_menu)
    LinearLayout mLlBottomMenu;
    private MuLuFragmentPresenter muLuFragmentPresenter;
    private String courseId;
    private CourseMuluAdapter mMuluAdapter;
    private boolean mIsEnterpriseCourse;//是否是企业课程 private List<CourseMuluTitle> mGroup;
    private List<CourseMuluTitle> mGroup;
    private Map<String, List<SectionInfo>> mMap;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        mMuluAdapter = new CourseMuluAdapter(getActivity());
        mMuluAdapter.setOnSectionItemClickListener(this);
        mGroup = new ArrayList<>();
        mMap = new HashMap<>();

        if (arguments != null) {
            courseId = getArguments().getString(getString(R.string.课程id));
        }
        if (muLuFragmentPresenter == null) {
            muLuFragmentPresenter = creatP();
        }
        muLuFragmentPresenter.getChapterData(courseId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course_mulu;
    }

    @Override
    protected MuLuFragmentView creatV() {
        return this;
    }

    @Override
    protected MuLuFragmentPresenter creatP() {
        if (muLuFragmentPresenter == null) {
            muLuFragmentPresenter = new MuLuFragmentPresenter();
        }
        return muLuFragmentPresenter;
    }

    @Override
    public void onTokenLose() {
        LoginAgain(getActivity());
    }

    @Override
    public void onLoadFail(String info) {
        ToastUtils.showShortToast(getActivity(), info);
    }

    @Override
    public void onLoadSuccess(List<ChapterInfo> chapterInfos) {
        //获取数据成功了
        if (null == chapterInfos || chapterInfos.size() == 0) {
            return;
        }
        //企业课程
//            mIsEnterpriseCourse = true;
        mLlDownloadManage.setVisibility(View.VISIBLE);
        //保存到数据库
        muLuFragmentPresenter.saveToDb(chapterInfos);
        for (ChapterInfo chapterInfo : chapterInfos) {
            List<SectionInfo> chapterList = chapterInfo.getChapterList();
            String seesion_name = chapterInfo.getSession_name();
            //添加章节的名称
            mGroup.add(new CourseMuluTitle(seesion_name));
            //添加子条目的数据
            mMap.put(seesion_name, chapterList);
        }
        if (mMuluAdapter != null) {
            mMuluAdapter.setData(mGroup, mMap);
            mExListview.setAdapter(mMuluAdapter);
            //取消自带的指示器
            mExListview.setGroupIndicator(null);
            //默认展开所有的列表
            if (mGroup == null || mGroup.size() == 0) {
                return;
            }
            for (int i = 0; i < mGroup.size(); i++) {
                mExListview.expandGroup(i);
            }
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

    /**
     * 当点击了课程章节
     * @param groupPosition
     * @param childPosition
     */
    @Override
    public void onSectionItemClick(int groupPosition, int childPosition) {
        SectionInfo sectionInfo = mMuluAdapter.getChild(groupPosition, childPosition);
        SectionClickEvent sectionClickEvent = new SectionClickEvent();
        sectionClickEvent.setSectionId(sectionInfo.getSession_id());
        sectionClickEvent.setSectionName(sectionInfo.getChapter_name());
        sectionClickEvent.setSectionUrl(sectionInfo.getUrl());
        EventBus.getDefault().post(sectionClickEvent);
    }
}
