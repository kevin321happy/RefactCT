package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.CourseDetailModel;

/**
 * Created by kevin321vip on 2017/10/31.
 */

public interface CourseDetailView extends BaseView {
    /**
     * 获取课程详情成功了
     * @param data
     */
    void getCourseDetailSuccess(CourseDetailModel data);
}
