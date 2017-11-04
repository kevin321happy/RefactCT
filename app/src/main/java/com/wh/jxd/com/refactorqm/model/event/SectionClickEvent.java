package com.wh.jxd.com.refactorqm.model.event;

/**
 * Created by kevin321vip on 2017/11/4.
 * 点击章节之后
 */

public class SectionClickEvent {
    private String SectionId;
    private String sectionName;
    private String sectionUrl;

    public String getSectionId() {
        return SectionId;
    }

    public void setSectionId(String sectionId) {
        SectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionUrl() {
        return sectionUrl;
    }

    public void setSectionUrl(String sectionUrl) {
        this.sectionUrl = sectionUrl;
    }
}
