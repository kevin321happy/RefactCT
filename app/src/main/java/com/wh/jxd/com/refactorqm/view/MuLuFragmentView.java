package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.ChapterInfo;

import java.util.List;

/**
 * Created by kevin321vip on 2017/11/1.
 */

public interface MuLuFragmentView extends BaseView {
    /**
     * 加载失败
     * @param info
     */
    void onLoadFail(String info);

    /**
     * 数据加载成功
     * @param chapterInfos
     */
    void onLoadSuccess(List<ChapterInfo> chapterInfos);
}
