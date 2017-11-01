package com.wh.jxd.com.refactorqm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.CourseInfo;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/14.
 */

public class RecommendCourseAdapter extends RecyclerView.Adapter {

    private ArrayList<CourseInfo> mListItems;
    private OnClickListener mOnClickListener;
    private Context mContext;

    public RecommendCourseAdapter(Context context) {
        this.mContext=context;

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_recommend_course, null);
        AutoUtils.autoSize(itemView);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHodler hodler = (ViewHodler) holder;
        final CourseInfo courseInfo = mListItems.get(position);
        if (courseInfo == null) {
            return;
        }
        hodler.iv_recommend_course.setImageURI(courseInfo.getCourseImage() == null ? "" : courseInfo.getCourseImage());
        hodler.tv_course_name.setText(courseInfo.getCourseName() == null ? "" : courseInfo.getCourseName());
        hodler.ll_recommend_course_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
//                    KLog.i("课程的值："+courseInfo.toString());
                    mOnClickListener.onRecommendCourseClickListener(position, courseInfo.getCourseId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListItems==null?0:mListItems.size();
    }

    public void setListItems(ArrayList<CourseInfo> listItems) {
        mListItems = listItems;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private SimpleDraweeView iv_recommend_course;
        private TextView tv_course_name;
        private AutoLinearLayout ll_recommend_course_item;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_recommend_course = (SimpleDraweeView) itemView.findViewById(R.id.iv_recommend_course);
            tv_course_name = (TextView) itemView.findViewById(R.id.tv_recommend_course_name);
            ll_recommend_course_item = (AutoLinearLayout) itemView.findViewById(R.id.ll_recommend_course_item);
        }
    }

    public interface OnClickListener {
        void onRecommendCourseClickListener(int position, String courseId);
    }
}
