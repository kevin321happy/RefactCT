package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/11.
 * 首页的
 */

public class GridViewBean {
    private String title;
    private int res;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }
    public void setRes(int res) {
        this.res = res;
    }

    public GridViewBean(int res, String title) {
        this.res=res;
        this.title=title;

    }
    @Override
    public String toString() {
        return "GridViewBean{" +
                "title='" + title + '\'' +
                ", res=" + res +
                '}';
    }
}
