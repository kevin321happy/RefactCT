package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/11/1.
 */

public class CourseDetailModel extends BaseModel {
    private String iscollect;
    private String isriokin;
    private CourseInfo coursr_detail;
    private CommentInfo comments;
    private String buytype;
    private String company_id;
    private SectionInfo first_chapter;
    private String study_process;
    private String is_study;

    public String getIs_study() {
        return is_study;
    }

    public void setIs_study(String is_study) {
        this.is_study = is_study;
    }

    public String getStudy_process() {
        return study_process;
    }

    public void setStudy_process(String study_process) {
        this.study_process = study_process;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public CommentInfo getComments() {
        return comments;
    }

    public void setComments(CommentInfo comments) {
        this.comments = comments;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    public String getIsriokin() {
        return isriokin;
    }

    public void setIsriokin(String isriokin) {
        this.isriokin = isriokin;
    }

    public CourseInfo getCoursr_detail() {
        return coursr_detail;
    }

    public void setCoursr_detail(CourseInfo coursr_detail) {
        this.coursr_detail = coursr_detail;
    }

    public String getBuytype() {
        return buytype;
    }

    public void setBuytype(String buytype) {
        this.buytype = buytype;
    }

    public SectionInfo getFirst_chapter() {
        return first_chapter;
    }

    public void setFirst_chapter(SectionInfo first_chapter) {
        this.first_chapter = first_chapter;
    }

    @Override
    public String toString() {
        return "CourseDetailModel{" +
                "iscollect='" + iscollect + '\'' +
                ", isriokin='" + isriokin + '\'' +
                ", coursr_detail=" + coursr_detail +
                ", comments=" + comments +
                ", buytype='" + buytype + '\'' +
                ", company_id='" + company_id + '\'' +
                ", first_chapter=" + first_chapter +
                ", study_process='" + study_process + '\'' +
                ", is_study='" + is_study + '\'' +
                '}';
    }
}
