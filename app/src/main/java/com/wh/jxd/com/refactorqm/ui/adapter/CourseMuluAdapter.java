package com.wh.jxd.com.refactorqm.ui.adapter;

/**
 * Created by Administrator on 2016/3/14.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.CourseMuluTitle;
import com.wh.jxd.com.refactorqm.model.SectionInfo;
import com.wh.jxd.com.refactorqm.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CourseMuluAdapter extends BaseExpandableListAdapter {
    // 用来控制CheckBox的选中状况
    //    private List<String> mStringList = new ArrayList<>();
    private boolean chooseAll;
    private LayoutInflater mInflater;
    List<CourseMuluTitle> mGroup = new ArrayList<>();
    Map<String, List<SectionInfo>> mMap = new HashMap<>();
    private Context mContext;
//    private ImageLoader imageLoader = ImageLoader.getInstance();
    private boolean isDel = false;
//    private CourseNameDao mCourseNameDao;
//    private SectionDownloadDao mSectionDownloadDao;
    private OnSectionItemClickListener mOnSectionItemClickListener;
    private int playing_parent;
    private int playing_child;
    private boolean mShowCheck = false;



    public void setShowCheck(boolean showCheck, boolean chooseAll) {
        this.mShowCheck = showCheck;
        this.chooseAll = chooseAll;
    }

    public void setOnSectionItemClickListener(
            OnSectionItemClickListener onSectionItemClickListener) {
        mOnSectionItemClickListener = onSectionItemClickListener;
    }

    public void setRecentPosition(int partent_position, int child_position) {
        playing_parent = partent_position;
        playing_child = child_position;
        notifyDataSetChanged();
    }

    public interface OnSectionItemClickListener {
        void onSectionItemClick(int groupPosition, int childPosition);//点击章节条目
    }

    public CourseMuluAdapter(Context context) {
//        getDaos(context);
        mContext = context;
    }



    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = mGroup.get(groupPosition).getTitlename();
        List<SectionInfo> sectionInfos = mMap.get(key);
        return sectionInfos.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroup.get(groupPosition);
    }

    @Override
    public SectionInfo getChild(int groupPosition, int childPosition) {
        String key = mGroup.get(groupPosition).getTitlename();
        List<SectionInfo> sectionInfos = mMap.get(key);
        return sectionInfos.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        //父控件的视图
        CourseMuluTitle courseMuluTitle = mGroup.get(groupPosition);
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_mulu_title, null);
        }
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_mulu_title);
        ImageView iv_arrow = (ImageView) convertView.findViewById(R.id.iv_mulu_arrow);
        String titleOrder = getTitleOrder(groupPosition);
        String titleName = courseMuluTitle.getTitlename() == null ? "" : courseMuluTitle.getTitlename();
        tv_title.setText(titleOrder + "、" + titleName);
        if (isExpanded) {
            iv_arrow.setImageResource(R.drawable.arrow_down);
        } else {
            iv_arrow.setImageResource(R.drawable.arrow_right);
        }

        String key = mGroup.get(groupPosition).getTitlename();
        List<SectionInfo> sectionInfos = mMap.get(key);
        if (null == sectionInfos || sectionInfos.size() == 0) {
            convertView.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        String key = mGroup.get(groupPosition).getTitlename();
        final List<SectionInfo> sectionInfos = mMap.get(key);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, null);
        }
        //章节信息
        final SectionInfo sectionInfo = sectionInfos.get(childPosition);
        if (null == sectionInfo) {
            convertView.setVisibility(View.GONE);
            return convertView;
        }
        ImageView iv_course_section_type = (ImageView) convertView
                .findViewById(R.id.iv_course_section_type);
        TextView tv_course_section_name = (TextView) convertView
                .findViewById(R.id.tv_course_section_name);
        TextView tv_course_section_progress = (TextView) convertView
                .findViewById(R.id.tv_course_section_progress);
        TextView tv_course_section_size = (TextView) convertView.findViewById(R.id.tv_course_section_size);
        View line = convertView.findViewById(R.id.course_section_line);
        TextView tv_download = (TextView) convertView.findViewById(R.id.tv_course_section_down);
        FrameLayout frameLayout = (FrameLayout) convertView.findViewById(R.id.fl_download);
        ImageView iv_download = (ImageView) convertView.findViewById(R.id.iv_course_section_down);
        CheckBox cb_section_check = (CheckBox) convertView.findViewById(R.id.cb_section_check);
        cb_section_check.setChecked(chooseAll == true ? true : false);
        cb_section_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                } else {

                }

            }
        });
        cb_section_check.setVisibility(mShowCheck ? View.VISIBLE : View.GONE);
        tv_course_section_name.setText(sectionInfo.getChapter_name());
        String progress = sectionInfo.getProcess().equals("") ? "0" : sectionInfo.getProcess();
        tv_course_section_progress.setText(progress);
        tv_course_section_progress.setText("已学习：" + sectionInfo.getProcess() + "%");
        String fielsize = "0";
        if (null == sectionInfo.getFile_size() || "".equals(sectionInfo.getFile_size())) {
            fielsize = "0";
        } else {
            fielsize = sectionInfo.getFile_size();
        }
        tv_course_section_size.setText(CommonUtils.FormentFileSize(Long.parseLong(fielsize)));
        String type = sectionInfo.getFile_type();
        if ("1".equals(type)) {
            iv_course_section_type.setImageResource(R.drawable.ic_web_course);
            tv_course_section_size.setVisibility(View.INVISIBLE);
        } else if ("2".equals(type)) {
            iv_course_section_type.setImageResource(R.drawable.ic_vedio_course);
        } else if ("3".equals(type)) {
            iv_course_section_type.setImageResource(R.drawable.ic_pdf_course);
        }
        if (childPosition == playing_child && groupPosition == playing_parent) {
            tv_course_section_name.setTextColor(mContext.getResources().getColor(R.color.title_color));
            tv_course_section_size.setTextColor(mContext.getResources().getColor(R.color.title_color));
            tv_course_section_progress.setTextColor(mContext.getResources().getColor(R.color.title_color));
        } else {
            tv_course_section_name.setTextColor(mContext.getResources().getColor(R.color.text_title));
            tv_course_section_size.setTextColor(mContext.getResources().getColor(R.color.text_title));
            tv_course_section_progress.setTextColor(mContext.getResources().getColor(R.color.text_title));
        }
        if (isLastChild) {
            line.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
        }
        //查询当前章节的状态状态
        //如果是企业课程,且为视频或pdf课程
//        if (!sectionInfo.getCompany_id().equals("0") && !sectionInfo.getFile_type().equals("1")) {
//            //设置下载按钮可见
//            frameLayout.setVisibility(View.VISIBLE);
//            //设置下载按钮的状态
//            CourseSectionEntity sectionEntity = mSectionDownloadDao
//                    .querySectionBySectionId(sectionInfo.getClass_chapter_id());
//            if (sectionEntity != null) {
//                int state = sectionEntity.getState();
//                changeState(state, tv_download, iv_download);
//            } else {
//                tv_download.setEnabled(true);
//                //数据库中没有该数据,可能被删除
//                tv_download.setVisibility(View.INVISIBLE);
//            }
//        } else {
//            frameLayout.setVisibility(View.GONE);
//        }
//        //点击下载的监听
//        iv_download.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!Utils.isWifi(mContext)) {
//                    AlertDialogUtils
//                            .showTowBtnDialog((Activity) mContext, "当前非wifi环境,确定要下载吗?", "取消", "确定",
//                                    new DialogClickInter() {
//                                        @Override
//                                        public void leftClick(AlertDialog dialog) {
//                                            dialog.dismiss();
//                                        }
//
//                                        @Override
//                                        public void rightClick(AlertDialog dialog) {
//                                            dialog.dismiss();
//                                            String url = sectionInfo.getUrl();
//                                            SectionDownloadEvent sectionDownloadEvent = new SectionDownloadEvent();
//                                            sectionDownloadEvent.setSectionUrl(url);
//                                            sectionDownloadEvent.setSectionId(sectionInfo.getClass_chapter_id());
//                                            EventBus.getDefault().post(sectionDownloadEvent);
//                                        }
//                                    });
//                } else {
//                    String url = sectionInfo.getUrl();
//                    SectionDownloadEvent sectionDownloadEvent = new SectionDownloadEvent();
//                    sectionDownloadEvent.setSectionUrl(url);
//                    sectionDownloadEvent.setSectionId(sectionInfo.getClass_chapter_id());
//                    EventBus.getDefault().post(sectionDownloadEvent);
//                }
//            }
//        });
        //当点击条目
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playing_parent = groupPosition;
                playing_child = childPosition;
                notifyDataSetChanged();
                if (mOnSectionItemClickListener != null) {
                    mOnSectionItemClickListener.onSectionItemClick(groupPosition, childPosition);
                }
            }
        });
        return convertView;
    }

    /**
     * 刷新列表的状态
     */
    private void changeState(int state, TextView tv_download, ImageView iv_download) {
        switch (state) {
            case 0:
                iv_download.setEnabled(true);
                iv_download.setVisibility(View.VISIBLE);
                tv_download.setVisibility(View.GONE);
                break;
            case 1:
                iv_download.setVisibility(View.GONE);
                tv_download.setVisibility(View.VISIBLE);
                tv_download.setText("下载中");
                tv_download.setTextColor(mContext.getResources().getColor(R.color.color_999));
                break;
            case 2:
                iv_download.setVisibility(View.GONE);
                tv_download.setVisibility(View.VISIBLE);
                tv_download.setText("已下载");
                tv_download.setTextColor(mContext.getResources().getColor(R.color.enterprise_act__text));
                break;
            case -1:
                iv_download.setVisibility(View.GONE);
                tv_download.setVisibility(View.VISIBLE);
                tv_download.setText("下载中");
                tv_download.setTextColor(mContext.getResources().getColor(R.color.color_999));
                break;
            case -2:
                iv_download.setVisibility(View.GONE);
                tv_download.setVisibility(View.VISIBLE);
                tv_download.setText("等待中");
                tv_download.setTextColor(mContext.getResources().getColor(R.color.color_999));
                break;
            case 3:
                iv_download.setVisibility(View.GONE);
                tv_download.setVisibility(View.VISIBLE);
                tv_download.setText("暂停");
                tv_download.setTextColor(mContext.getResources().getColor(R.color.enterprise_act__text));
                break;
            default:
                iv_download.setEnabled(true);
                iv_download.setVisibility(View.VISIBLE);
                tv_download.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setData(List<CourseMuluTitle> group, Map<String, List<SectionInfo>> map) {
        this.mGroup = group;
        this.mMap = map;
    }

    private String getTitleOrder(int groupPosition) {
        return CommonUtils.ToCH(groupPosition + 1);
    }
}

