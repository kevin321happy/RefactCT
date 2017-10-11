package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.HomeInfo;

/**
 * Created by kevin321vip on 2017/10/11.
 */

public interface HomeFragmentView extends BaseView {
    /**
     * 加载首页数据成功
     */
    void onLoadSuccess(HomeInfo homeInfo);

    /**
     * 加载数据失败了
     */
    void onLoadFail(String info);
}
