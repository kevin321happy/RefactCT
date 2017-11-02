package com.wh.jxd.com.refactorqm.model;

import java.util.List;

/**
 * Created by kevin321vip on 2017/11/1.
 */

public class ChapterListModel extends BaseModel {

    private List<ChapterInfo> data;

    public List<ChapterInfo> getData() {
        return data;
    }

    public void setData(List<ChapterInfo> data) {
        this.data = data;
    }

}
