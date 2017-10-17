package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.UserInfo;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public interface PersonalView extends BaseView {
    /**
     * 获得用户信息成功
     *
     * @param userInfo
     */
    void getUserInfoSuccess(UserInfo userInfo);

    /**
     * 更新用户姓名
     */
    void updataNameSuccess(String name);

    /**
     * 昵称
     */
    void updataNicknameSuccess(String nickname);

    /**
     * 生日
     */
    void updataBirthDaySuccess(String birthDay);

    /**
     * 性别
     */
    void updataSexSuccess(String sex);

    /**
     * 婚恋
     */
    void updataMarrySuccess(String marry);

    /**
     * 个性签名
     */
    void updataSignaTureSuccess(String signature);
    /**
     * 用户头像
     */
    void updataHeadImaSuccess(String headIma);

    /**
     * 修改失败
     * @param s
     */
    void updataInfoFail(String s);
}
