package com.wh.jxd.com.refactorqm.view;

import com.wh.jxd.com.refactorqm.base.BaseView;
import com.wh.jxd.com.refactorqm.model.EnterpriseDataModel;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public interface EnterpriseFragmentView extends BaseView {
    /**
     * 界面数据加载成功
     * @param data
     */
    void onLoadSuccess(EnterpriseDataModel data);

}
