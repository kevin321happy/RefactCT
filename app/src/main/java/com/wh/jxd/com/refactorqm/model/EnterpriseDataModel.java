package com.wh.jxd.com.refactorqm.model;

import java.util.List;

/**
 * Created by kevin321vip on 2017/10/20.
 * 企业信息的Model
 */

public class EnterpriseDataModel extends BaseModel {
    // 企业信息
    private EnterpriseInfo enterpriseinfo;

    @Override
    public String toString() {
        return "EnterpriseDataModel{" +
                "enterpriseinfo=" + enterpriseinfo +
                ", entCourseList=" + entCourseList +
                ", categoryList=" + categoryList +
                ", bannerList=" + bannerList +
                ", member_rank=" + member_rank +
                '}';
    }

    // 企业课程列表
    private List<CourseInfo> entCourseList;
    private List<CategoryInfo>categoryList;//课程分类的图标
    private List<EntBannerInfo> bannerList;//banner广告位
    private MemberRankModel member_rank;

    public MemberRankModel getMember_rank() {
        return member_rank;
    }

    public void setMember_rank(MemberRankModel member_rank) {
        this.member_rank = member_rank;
    }

    public List<CategoryInfo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryInfo> categoryList) {
        this.categoryList = categoryList;
    }

    public List<EntBannerInfo> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<EntBannerInfo> bannerList) {
        this.bannerList = bannerList;
    }

    public EnterpriseInfo getEnterpriseinfo() {
        return enterpriseinfo;
    }

    public void setEnterpriseinfo(EnterpriseInfo enterpriseinfo) {
        this.enterpriseinfo = enterpriseinfo;
    }

    public List<CourseInfo> getEntCourseList() {
        return entCourseList;
    }

    public void setEntCourseList(List<CourseInfo> entCourseList) {
        this.entCourseList = entCourseList;
    }
}
