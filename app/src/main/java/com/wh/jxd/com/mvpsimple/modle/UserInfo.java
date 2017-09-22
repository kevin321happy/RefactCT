package com.wh.jxd.com.mvpsimple.modle;

import java.io.Serializable;

/**
 * Created by kevin321vip on 2017/8/28.
 */

public class UserInfo implements Serializable {

    private String id;
    private String userid;
    private String user_name;
    private String member_name;
    private String nickname;
    private String company_id;
    private String file_id;
    private String status;
    private int phone_verified;
    private String tel;
    private String ry_token;
    private String email;
    private String tel_verified;
    private int email_verified;
    private String created_at;
    private String last_login;
    private String password;
    private String qmct_token;
    private Object member_info_detail;
    private String sex;//性别
    private String birthday;//生日
    private String marry;
    private String leavename;//等级的名字
    private String bean_points;//用户魔豆
    private String work_number;//工号
    private String department_name;//部门
    private String job_name;//岗位
    private String signature;//签名
    private String total_points;//积分
    private String head_image;//头像路径
    private String member_level;//用户昵称
    private String is_teacher;//是否是讲师

    public String getIs_teacher() {
        return is_teacher;
    }

    public void setIs_teacher(String is_teacher) {
        this.is_teacher = is_teacher;
    }

    public String getMember_level() {
        return member_level;
    }

    public void setMember_level(String member_level) {
        this.member_level = member_level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhone_verified() {
        return phone_verified;
    }

    public void setPhone_verified(int phone_verified) {
        this.phone_verified = phone_verified;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRy_token() {
        return ry_token;
    }

    public void setRy_token(String ry_token) {
        this.ry_token = ry_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel_verified() {
        return tel_verified;
    }

    public void setTel_verified(String tel_verified) {
        this.tel_verified = tel_verified;
    }

    public int getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(int email_verified) {
        this.email_verified = email_verified;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQmct_token() {
        return qmct_token;
    }

    public void setQmct_token(String qmct_token) {
        this.qmct_token = qmct_token;
    }

    public Object getMember_info_detail() {
        return member_info_detail;
    }

    public void setMember_info_detail(Object member_info_detail) {
        this.member_info_detail = member_info_detail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
    }

    public String getBean_points() {
        return bean_points;
    }

    public void setBean_points(String bean_points) {
        this.bean_points = bean_points;
    }

    public String getWork_number() {
        return work_number;
    }

    public void setWork_number(String work_number) {
        this.work_number = work_number;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTotal_points() {
        return total_points;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", user_name='" + user_name + '\'' +
                ", member_name='" + member_name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", company_id='" + company_id + '\'' +
                ", file_id='" + file_id + '\'' +
                ", status='" + status + '\'' +
                ", phone_verified=" + phone_verified +
                ", tel='" + tel + '\'' +
                ", ry_token='" + ry_token + '\'' +
                ", email='" + email + '\'' +
                ", tel_verified='" + tel_verified + '\'' +
                ", email_verified=" + email_verified +
                ", created_at='" + created_at + '\'' +
                ", last_login='" + last_login + '\'' +
                ", password='" + password + '\'' +
                ", qmct_token='" + qmct_token + '\'' +
                ", member_info_detail=" + member_info_detail +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", marry='" + marry + '\'' +
                ", leavename='" + leavename + '\'' +
                ", bean_points='" + bean_points + '\'' +
                ", work_number='" + work_number + '\'' +
                ", department_name='" + department_name + '\'' +
                ", job_name='" + job_name + '\'' +
                ", signature='" + signature + '\'' +
                ", total_points='" + total_points + '\'' +
                ", head_image='" + head_image + '\'' +
                ", member_level='" + member_level + '\'' +
                ", is_teacher='" + is_teacher + '\'' +
                '}';
    }
}
