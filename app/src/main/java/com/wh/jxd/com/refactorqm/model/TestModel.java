package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/17.
 */

public class TestModel {

    /**
     * status : 1
     * info : success
     * data : true
     * suc : y
     * is_login : 1
     */
    private String status;
    private String info;
    private boolean data;
    private String suc;
    private String is_login;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public boolean getData() {
        return data;
    }

    public String getSuc() {
        return suc;
    }

    public String getIs_login() {
        return is_login;
    }
}
