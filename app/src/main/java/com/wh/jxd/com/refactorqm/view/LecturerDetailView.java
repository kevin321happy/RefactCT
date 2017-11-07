package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.TeacherListModel;
import com.wh.jxd.com.refactorqm.model.TeacherModel;

import java.util.List;

/**
 * Created by kevin321vip on 2017/11/6.
 */

public interface LecturerDetailView extends BaseView {
    /**
     * 加载讲师数据成功
     * @param data
     */
    void loadTeacherDataSuccess(List<TeacherModel> data);

    void onLoadFail(String info);
}
