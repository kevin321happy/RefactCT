package com.wh.jxd.com.refactorqm.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.GridViewBean;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by kevin321vip on 2017/10/12.
 */

public class PersonalMenuAdapter extends BaseAdapter {

    private GridViewBean[] mBeanList;

    public PersonalMenuAdapter(GridViewBean[] gridViewBeans) {
        this.mBeanList=gridViewBeans;
    }
    @Override
    public int getCount() {
        return mBeanList == null ? 0 : mBeanList.length;
    }

    @Override
    public Object getItem(int position) {
        return mBeanList==null?null:mBeanList[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_personal_menu, null);
            AutoUtils.autoSize(convertView);
            viewHodler = new ViewHodler();
            viewHodler.iv_menu = (ImageView) convertView.findViewById(R.id.iv_menu);
            viewHodler.tv_menu = (TextView) convertView.findViewById(R.id.tv_menu);
            viewHodler.view_division = convertView.findViewById(R.id.view_division);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        GridViewBean gridViewBean = mBeanList[position];
        if (gridViewBean != null) {
            viewHodler.view_division.setVisibility(position == mBeanList.length - 1 ? View.VISIBLE : View.GONE);
            viewHodler.tv_menu.setText(gridViewBean.getTitle() == null ? "" : gridViewBean.getTitle());
            viewHodler.iv_menu.setImageResource(gridViewBean.getRes());
        }
        return convertView;
    }

    private class ViewHodler {
        private ImageView iv_menu;
        private TextView tv_menu;
        private View view_division;
    }
}
