package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/14.
 */

public class TeacherModel {
    private String teacher_id;
    private String teacher_name;
    private String teacher_photo_url;
    private String teacher_info;
    private String teacher_field;//擅长领域

    @Override
    public String toString() {
        return "TeacherModel{" +
                "teacher_id='" + teacher_id + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_photo_url='" + teacher_photo_url + '\'' +
                ", teacher_info='" + teacher_info + '\'' +
                ", teacher_field='" + teacher_field + '\'' +
                '}';
    }
    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_photo_url() {
        return teacher_photo_url;
    }

    public void setTeacher_photo_url(String teacher_photo_url) {
        this.teacher_photo_url = teacher_photo_url;
    }

    public String getTeacher_info() {
        return teacher_info;
    }

    public void setTeacher_info(String teacher_info) {
        this.teacher_info = teacher_info;
    }

    public String getTeacher_field() {
        return teacher_field;
    }

    public void setTeacher_field(String teacher_field) {
        this.teacher_field = teacher_field;
    }
}
