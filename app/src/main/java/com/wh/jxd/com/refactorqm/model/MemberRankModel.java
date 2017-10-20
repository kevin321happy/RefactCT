package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/20.
 */

class MemberRankModel extends BaseModel {
    private String study_time;//我的学时 秒
    private String study_time_rank; //学时排名
    private String study_time_max; //企业内学时最长的那个屌毛
    private String score_rate; //得分率
    private String score_rate_rank;//得分率排名
    private String total_member;//企业总人数

    public String getTotal_member() {
        return total_member;
    }

    public void setTotal_member(String total_member) {
        this.total_member = total_member;
    }

    public String getStudy_time() {
        return study_time;
    }

    public void setStudy_time(String study_time) {
        this.study_time = study_time;
    }

    public String getStudy_time_rank() {
        return study_time_rank;
    }

    public void setStudy_time_rank(String study_time_rank) {
        this.study_time_rank = study_time_rank;
    }

    public String getStudy_time_max() {
        return study_time_max;
    }

    public void setStudy_time_max(String study_time_max) {
        this.study_time_max = study_time_max;
    }

    public String getScore_rate() {
        return score_rate;
    }

    public void setScore_rate(String score_rate) {
        this.score_rate = score_rate;
    }

    public String getScore_rate_rank() {
        return score_rate_rank;
    }

    public void setScore_rate_rank(String score_rate_rank) {
        this.score_rate_rank = score_rate_rank;
    }
}
