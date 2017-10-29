package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.UserInfo;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public interface PersonalFragmentView extends BaseView {
    /**
     * 加载个人数据成功
     * @param data
     */
    void onLoadSuccess(UserInfo data);

    /**
     * 加载失败了
     * @param s
     */
    void onLoadFail(String s);

    /**
     * 清除了用户信息
     */
    void clearInfoSuccess();

    /**
     * Token失效了
     */
    void onTokenLose();
}
