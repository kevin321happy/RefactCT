package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/20.
 */

public class EntBannerInfo extends BaseModel {

    @Override
    public String toString() {
        return "EntBannerInfo{" +
                "img_url='" + img_url + '\'' +
                ", jump_url='" + jump_url + '\'' +
                '}';
    }

    private String img_url;
    private String jump_url;

    public EntBannerInfo() {
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getJump_url() {
        return jump_url;
    }

    public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }
}
