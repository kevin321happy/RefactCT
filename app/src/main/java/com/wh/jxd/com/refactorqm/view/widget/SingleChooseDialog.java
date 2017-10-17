package com.wh.jxd.com.refactorqm.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.utils.DisplayUtil;

import java.util.List;

/**
 * Created by kevin321vip on 2017/9/24.
 * 单选的对话框
 */

public class SingleChooseDialog extends Dialog {
    private Context mContext;
    private List<String> Listitems;
    private SingleChooseAdapter mChooseAdapter;
    private int checkedPosition = -1;//选中的位置
    private TextView mTvTitleName;
    private ListView mLvItems;
    private onChooseItemClick mOnChooseItemClick;
    private SingleChooseDialog mSingleChooseDialog;
    private String mTitle;
    private View mDialogContent;

    public void setOnChooseItemClick(onChooseItemClick onChooseItemClick) {
        mOnChooseItemClick = onChooseItemClick;
    }

    public SingleChooseDialog(@NonNull Context context, String title) {
        this(context, 0);
        this.mContext = context;
        this.mTitle = title;
        this.mSingleChooseDialog = this;
    }
    public SingleChooseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.single_choose_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        View contentView = View.inflate(mContext, R.layout.single_choose_layout, null);
        setContentView(contentView);
        mTvTitleName = (TextView) contentView.findViewById(R.id.tv_title_name);
        mTvTitleName.setText(mTitle);
        mLvItems = (ListView) contentView.findViewById(R.id.lv_items);
        mChooseAdapter = new SingleChooseAdapter();
        mLvItems.setAdapter(mChooseAdapter);
        resizeDialog();
        mDialogContent = getWindow().getDecorView().findViewById(android.R.id.content);
    }

    /**
     * 单选列表的适配器
     */
    public class SingleChooseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Listitems == null ? 0 : Listitems.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View contentview, ViewGroup viewGroup) {
            final ViewHodler hodler;
            if (contentview == null) {
                contentview = View.inflate(mContext, R.layout.item_single_choose, null);
                hodler = new ViewHodler();
                hodler.mTextView = (TextView) contentview.findViewById(R.id.tv_content);
                hodler.mCheckBox = (CheckBox) contentview.findViewById(R.id.cb_choose);
                contentview.setTag(hodler);
            } else {
                hodler = (ViewHodler) contentview.getTag();
            }
            hodler.mTextView.setText(Listitems.get(i));
            hodler.mCheckBox.setChecked(i == checkedPosition);
            //设置勾选的状态
            if (i != checkedPosition) {
                hodler.mCheckBox.setChecked(false);
            }
            hodler.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hodler.mCheckBox.isChecked()) {
                        changeChooseeItem(i);
                    } else {
                        checkedPosition = -1;
                        notifyDataSetChanged();
                    }
                }
            });
            contentview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeChooseeItem(i);
                }
            });
            return contentview;
        }

        /**
         * 改变选中的条目位置
         *
         * @param
         */
        private void changeChooseeItem(int i) {
            checkedPosition = i;
            notifyDataSetChanged();
            if (mOnChooseItemClick != null) {
                if (mSingleChooseDialog != null) {
                    mSingleChooseDialog.dismiss();
                }
                mOnChooseItemClick.onItemChoose(i);
            }
            mSingleChooseDialog.dismiss();
        }
    }


    public class ViewHodler {
        //单选勾选框
        private CheckBox mCheckBox;
        //条目内容
        private TextView mTextView;
    }

    /**
     * 设置条目的内容
     *
     * @param listitems
     */
    public void setListitems(List<String> listitems) {
        Listitems = listitems;
        if (mChooseAdapter != null) {
            mChooseAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置选中条目的位置
     *
     * @param checkedPosition
     */
    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        if (mChooseAdapter != null) {
            mChooseAdapter.notifyDataSetChanged();
        }
    }
    //调整对话框的位置
    private void resizeDialog() {
        Window window = getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (DisplayUtil.getScreenSize(getContext()).x * 0.8);
        getWindow().setAttributes(attributes);
    }

    /**
     * 定义选中的接口
     */
    public interface onChooseItemClick {
        void onItemChoose(int position);
    }
}
