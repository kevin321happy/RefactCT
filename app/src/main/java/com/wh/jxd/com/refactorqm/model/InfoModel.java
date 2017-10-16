package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/13.
 */

public class InfoModel extends BaseModel {
    private String status;
    private String info;
    private String data;
    private String suc;
    private String is_login;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    //    "status": "1",
//            "info": "success",
//            "data": true,
//            "suc": "y",
//            "is_login": "1"
}
