package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/8/2.
 * 企业底部菜单的数据模型
 */

public class EntButtomMenuModel {
    private int res_id;
    private String content_text;
    private String is_new;
    private String unread_count;

    public EntButtomMenuModel(int res_id, String content_text, String unread_count) {
        this.res_id = res_id;
        this.content_text = content_text;
        this.is_new = is_new;
        this.unread_count = unread_count;
    }

    public String getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(String unread_count) {
        this.unread_count = unread_count;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public EntButtomMenuModel() {
    }

    public EntButtomMenuModel(int res_id, String content_text) {
        this.res_id = res_id;
        this.content_text = content_text;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getContent_text() {
        return content_text;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text;
    }
}
