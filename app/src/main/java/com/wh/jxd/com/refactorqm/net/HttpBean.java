package com.wh.jxd.com.refactorqm.net;

/**
 * Created by kevin321vip on 2017/9/29.
 */

public class HttpBean<T> {
    private String status;
    private String info;
    private String is_login;
    private String suc;
    private T data;

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

    public String getIs_login() {
        return is_login;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public String getSuc() {
        return suc;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpBean{" +
                "status='" + status + '\'' +
                ", info='" + info + '\'' +
                ", is_login='" + is_login + '\'' +
                ", suc='" + suc + '\'' +
                ", data=" + data +
                '}';
    }
}
