package com.wh.jxd.com.refactorqm.model;



/**
 * Created by kevin321vip on 2017/8/15.
 * 最近学习记录
 */


public class RecentStudyEntity {

    private long Id;

    private String course_Id;

    private String company_Id;

    private String course_name;

    private String course_Ima;

    private String last_Study_Time;

    private String is_Task;//“0”为任务课程,“1”为非任务

    public RecentStudyEntity() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(String course_Id) {
        this.course_Id = course_Id;
    }

    public String getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(String company_Id) {
        this.company_Id = company_Id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_Ima() {
        return course_Ima;
    }

    public void setCourse_Ima(String course_Ima) {
        this.course_Ima = course_Ima;
    }

    public String getLast_Study_Time() {
        return last_Study_Time;
    }

    public void setLast_Study_Time(String last_Study_Time) {
        this.last_Study_Time = last_Study_Time;
    }

    public String getIs_Task() {
        return is_Task;
    }

    public void setIs_Task(String is_Task) {
        this.is_Task = is_Task;
    }

    @Override
    public String toString() {
        return "RecentStudyEntity{" +
                "Id=" + Id +
                ", course_Id='" + course_Id + '\'' +
                ", company_Id='" + company_Id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_Ima='" + course_Ima + '\'' +
                ", last_Study_Time='" + last_Study_Time + '\'' +
                ", is_Task='" + is_Task + '\'' +
                '}';
    }
}
