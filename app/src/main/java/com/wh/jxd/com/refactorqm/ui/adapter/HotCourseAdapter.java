package com.wh.jxd.com.refactorqm.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.CourseInfo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

public class HotCourseAdapter extends Adapter {


  private ArrayList<CourseInfo> mListitems;
  private Context mContext;
  private OnClickListener mOnClickListener;

  public void setOnClickListener(OnClickListener onClickListener) {
    mOnClickListener = onClickListener;
  }


  public HotCourseAdapter(Context context) {
    this.mContext=context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View item = View.inflate(parent.getContext(), R.layout.item_home_hot_course, null);
    AutoUtils.autoSize(item);
    return new ViewHodler(item);

  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    final CourseInfo courseInfo = mListitems.get(position);
    if (null == courseInfo) {
      return;
    }
    ViewHodler viewHodler = (ViewHodler) holder;
    viewHodler.iv_hotcourse_ima.setImageURI(courseInfo.getCourseImage()==null? "":courseInfo.getCourseImage());
    viewHodler.tv_hotcourse_name.setText(courseInfo.getCourseName()==null? "":courseInfo.getCourseName());
    viewHodler.ll_item.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null!=mOnClickListener){
          mOnClickListener.onHotCourseClickListener(position,courseInfo.getCourseId());
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mListitems==null?0:mListitems.size();
  }

  public void setListitems(ArrayList<CourseInfo> listitems) {
    mListitems = listitems;
  }

  class ViewHodler extends ViewHolder {
    private SimpleDraweeView iv_hotcourse_ima;
    private TextView tv_hotcourse_name;
    private LinearLayout ll_item;

    public ViewHodler(View itemView) {
      super(itemView);
      iv_hotcourse_ima = (SimpleDraweeView) itemView.findViewById(R.id.iv_hotcourse_ima);
      tv_hotcourse_name = (TextView) itemView.findViewById(R.id.tv_hotcourse_name);
      ll_item= (LinearLayout) itemView.findViewById(R.id.ll_item);
    }
  }

  public interface OnClickListener{
    void onHotCourseClickListener(int position, String courseId);

  }
}
