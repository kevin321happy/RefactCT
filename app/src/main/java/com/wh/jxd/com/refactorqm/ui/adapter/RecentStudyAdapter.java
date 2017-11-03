package com.wh.jxd.com.refactorqm.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.RecentStudyEntity;

import java.util.List;

/**
 * Created by kevin321vip on 2017/11/3.
 */

public class RecentStudyAdapter extends RecyclerView.Adapter {
    private List<RecentStudyEntity> mStudyEntities;
    private onCourseItemClickListener mOnCourseItemClickListener;

    public void setOnCourseItemClickListener(onCourseItemClickListener onCourseItemClickListener) {
        mOnCourseItemClickListener = onCourseItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = View.inflate(parent.getContext(), R.layout.item_course_record, null);
        ViewHodler viewHodler = new ViewHodler(item);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHodler hodler = (ViewHodler) holder;
        final RecentStudyEntity recentStudyEntity = mStudyEntities.get(position);
        if (recentStudyEntity == null) {
            return;
        }
        hodler.mSimpleDraweeView.setImageURI(recentStudyEntity.getCourse_Ima());
        hodler.tv_course_title.setText(recentStudyEntity.getCourse_name());
        hodler.tv_last_study_time.setText(recentStudyEntity.getLast_Study_Time());
        hodler.tv_is_task.setText("1".equals(recentStudyEntity.getIs_Task()) ? "任务课程" : "非任务课程");
        hodler.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCourseItemClickListener != null) {
                    mOnCourseItemClickListener.onCourseItemClick(recentStudyEntity.getCourse_Id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudyEntities == null ? 0 : mStudyEntities.size();
    }

    public void setData(List<RecentStudyEntity> studyEntities) {
        this.mStudyEntities = studyEntities;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        public SimpleDraweeView mSimpleDraweeView;
        public TextView tv_course_title;
        public TextView tv_is_task;
        public TextView tv_last_study_time;
        public LinearLayout ll_item;

        public ViewHodler(View itemView) {
            super(itemView);
            mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.SimpleDraweeView);
            tv_course_title = (TextView) itemView.findViewById(R.id.tv_course_title);
            tv_is_task = (TextView) itemView.findViewById(R.id.tv_is_task);
            tv_last_study_time = (TextView) itemView.findViewById(R.id.tv_last_study_time);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public interface onCourseItemClickListener {
        void onCourseItemClick(String course_id);
    }
}
