package com.wh.jxd.com.refactorqm.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.TeacherModel;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class TeacherDetailAdapter extends RecyclerView.Adapter<TeacherDetailAdapter.ViewHolder> {

    private List<TeacherModel> data;

    public void setData(List<TeacherModel> data) {
        this.data = data;
    }

    public TeacherDetailAdapter(List<TeacherModel> data) {
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_teacher_detail_card, parent, false);
        AutoUtils.autoSize(v);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(data.get(position).getTeacher_photo_url())
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
