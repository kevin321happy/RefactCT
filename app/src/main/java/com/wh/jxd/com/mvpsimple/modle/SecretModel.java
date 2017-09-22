package com.wh.jxd.com.mvpsimple.modle;

/**
 * Created by kevin321vip on 2017/9/19.
 */

public class SecretModel {


    /**
     * status : 1
     * info : success
     * data : {"app_secret":"asd%#!123&"}
     * suc : y
     */

    private String status;
    private String info;
    private DataEntity data;
    private String suc;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public DataEntity getData() {
        return data;
    }

    public String getSuc() {
        return suc;
    }

    public static class DataEntity {
        /**
         * app_secret : asd%#!123&
         */

        private String app_secret;

        public void setApp_secret(String app_secret) {
            this.app_secret = app_secret;
        }

        public String getApp_secret() {
            return app_secret;
        }
    }
}
