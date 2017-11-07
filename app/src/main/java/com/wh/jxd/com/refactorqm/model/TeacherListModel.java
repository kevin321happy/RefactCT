package com.wh.jxd.com.refactorqm.model;

import java.util.List;

/**
 * Created by kevin321vip on 2017/11/6.
 */

public class TeacherListModel extends BaseModel{
    private String status;
    private String info;
    private String suc;
    private String is_login;
    private List<TeacherModel> data;

    public List<TeacherModel> getData() {
        return data;
    }

    public void setData(List<TeacherModel> data) {
        this.data = data;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSuc() {
        return suc;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public String getIs_login() {
        return is_login;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    @Override
    public String toString() {
        return "TeacherListModel{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", suc='" + suc + '\'' +
                ", is_login='" + is_login + '\'' +
                ", data=" + data +
                '}';
    }
}
