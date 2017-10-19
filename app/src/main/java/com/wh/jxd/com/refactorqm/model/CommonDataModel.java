package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/13.
 */

public class CommonDataModel extends BaseModel{

    /**
     * status : 1
     * info : success
     * data : true
     * suc : y
     * is_login : 1
     */

    private String status;
    private String info;
    private Object data;
    private String suc;
    private String is_login;

    @Override
    public String toString() {
        return "CommonDataModel{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", data=" + data +
                ", suc='" + suc + '\'' +
                ", is_login='" + is_login + '\'' +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
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



    public String getSuc() {
        return suc;
    }

    public String getIs_login() {
        return is_login;
    }
}
