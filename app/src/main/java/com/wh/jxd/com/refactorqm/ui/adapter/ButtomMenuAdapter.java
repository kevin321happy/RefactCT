package com.wh.jxd.com.refactorqm.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.model.EntButtomMenuModel;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/8/2.
 * 底部菜单的适配器
 */

public class ButtomMenuAdapter extends RecyclerView.Adapter {
    private List<EntButtomMenuModel> mMenuModelList = new ArrayList<>();
    private onButtomMenuClick mOnButtomMenuClick;

    public void setOnButtomMenuClick(onButtomMenuClick onButtomMenuClick) {
        mOnButtomMenuClick = onButtomMenuClick;
    }

    public ButtomMenuAdapter(List<EntButtomMenuModel> buttomMenuData) {
        this.mMenuModelList = buttomMenuData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_ent_buttom_menu, null);
        AutoUtils.autoSize(view);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        EntButtomMenuModel buttomMenuModel = mMenuModelList.get(position);
        ViewHodler viewHodler = (ViewHodler) holder;
        if (buttomMenuModel != null) {
            viewHodler.iv_buttom_menu.setImageResource(buttomMenuModel.getRes_id());
            viewHodler.tv_buttom_menu.setText(buttomMenuModel.getContent_text());
        }
        viewHodler.ll_button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnButtomMenuClick != null) {
                    mOnButtomMenuClick.onMenuClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMenuModelList == null ? 0 : mMenuModelList.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        private ImageView iv_buttom_menu;
        private TextView tv_buttom_menu;
        private AutoLinearLayout ll_button_menu;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_buttom_menu = (ImageView) itemView.findViewById(R.id.iv_bottom_menu);
            tv_buttom_menu = (TextView) itemView.findViewById(R.id.tv_buttom_menu);
            ll_button_menu = (AutoLinearLayout) itemView.findViewById(R.id.ll_buttom_menu);
        }
    }

    public interface onButtomMenuClick {
        void onMenuClick(int position);
    }
}
