package com.wh.jxd.com.refactorqm.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.TeacherCourse;
import com.wh.jxd.com.refactorqm.view.widget.CircleImageView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * description: 首页推荐讲师的Adapter
 * autour: Kevin
 * company:锦绣氘(武汉)科技有限公司
 * date: 2017/6/20 10:54
 * update: 2017/6/20
 * version: 1.21
 * 站在峰顶 看世界
 * 落在谷底 思人生
 */

public class RecommendTeacherAdapter extends Adapter {

    private ArrayList<TeacherCourse> mListItems;
    private Context mContext;
    private onClickListener onClickListener;
    public void setOnClickListener(RecommendTeacherAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public RecommendTeacherAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = View.inflate(parent.getContext(), R.layout.item_home_recommend_teacher, null);
        AutoUtils.autoSize(item);
        return new ViewHodler(item);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TeacherCourse teacherInfo = mListItems.get(position);
        if (teacherInfo == null) return;
        ViewHodler hodler = (ViewHodler) holder;
        Glide.with(mContext).load(teacherInfo.getTeacherPhoto()).into(hodler.iv_teacher_ima);
        hodler.tv_teacher_name.setText(teacherInfo.getTeacherName() + "");
        hodler.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=onClickListener){
                    onClickListener.onTeacherItemClick(position,teacherInfo.getTeacherId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListItems==null?0:mListItems.size();
    }

    public void setListItems(ArrayList<TeacherCourse> listItems) {
        mListItems = listItems;
    }

    class ViewHodler extends ViewHolder {
        private CircleImageView iv_teacher_ima;
        private TextView tv_teacher_name;

        private LinearLayout ll_item;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_teacher_ima = (CircleImageView) itemView.findViewById(R.id.iv_teacher_ima);
            tv_teacher_name = (TextView) itemView.findViewById(R.id.tv_teacher_name);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public interface onClickListener {
        void onTeacherItemClick(int position, int teacherid);
    }
}
