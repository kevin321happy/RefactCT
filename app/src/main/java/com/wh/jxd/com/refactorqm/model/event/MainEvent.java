package com.wh.jxd.com.refactorqm.model.event;

/**
 * Created by kevin321vip on 2017/11/3.
 * 主界面收到的Event
 */

public class MainEvent {
    private String type;

    public MainEvent() {
    }

    public MainEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
