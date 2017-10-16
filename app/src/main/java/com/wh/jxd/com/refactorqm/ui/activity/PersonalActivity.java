package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.PersonalPresenterImpl;
import com.wh.jxd.com.refactorqm.view.PersonalView;
import com.wh.jxd.com.refactorqm.view.widget.CircleImageView;

import butterknife.Bind;

/**
 * Created by kevin321vip on 2017/10/12.
 * 个人信息界面
 */
public class PersonalActivity extends BaseMvpActivity<PersonalPresenterImpl, PersonalView> implements PersonalView {
    @Bind(R.id.toolbar_subtitle)
    TextView mToolbarSubtitle;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.iv_personalhead)
    CircleImageView mIvPersonalhead;
    @Bind(R.id.ll_personalhead)
    LinearLayout mLlPersonalhead;
    @Bind(R.id.tv_user_name)
    TextView mTvUserName;
    @Bind(R.id.ll_user_name)
    LinearLayout mLlUserName;
    @Bind(R.id.tv_truename)
    TextView mTvTruename;
    @Bind(R.id.ll_personalname)
    LinearLayout mLlPersonalname;
    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.tv_birthday)
    TextView mTvBirthday;
    @Bind(R.id.ll_birthday)
    LinearLayout mLlBirthday;
    @Bind(R.id.tv_sex)
    TextView mTvSex;
    @Bind(R.id.ll_sex)
    LinearLayout mLlSex;
    @Bind(R.id.tv_marry)
    TextView mTvMarry;
    @Bind(R.id.ll_wedlock)
    LinearLayout mLlWedlock;
    @Bind(R.id.tv_worknum)
    TextView mTvWorknum;
    @Bind(R.id.tv_dep)
    TextView mTvDep;
    @Bind(R.id.tv_job)
    TextView mTvJob;
    @Bind(R.id.tv_signature)
    TextView mTvSignature;
    @Bind(R.id.ll_autograph)
    LinearLayout mLlAutograph;
    private PersonalPresenterImpl mPersonalPresenter;
    private UserInfo mUserInfo;
    private String mSex;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_personalinfo;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPersonalPresenter == null) {
            mPersonalPresenter = creatPersenter(this);
        }
        mPersonalPresenter.getUserInfo();
    }

    protected void initView() {
        setToolBarTitle("个人信息");
    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

    @Override
    protected PersonalView creatView() {
        return this;
    }

    @Override
    public PersonalPresenterImpl creatPersenter(Context context) {
        if (mPersonalPresenter == null) {
            mPersonalPresenter = new PersonalPresenterImpl();
        }
        return mPersonalPresenter;
    }

    /**
     * 获取用户信息成功
     *
     * @param userInfo
     */
    @Override
    public void getUserInfoSuccess(UserInfo userInfo) {
        KLog.d("用户信息：" + userInfo.toString());
        if (null == userInfo) {
            return;
        }
        String birthday = userInfo.getBirthday();
        if (null == birthday || birthday.isEmpty()) {
//            setDateTime(null);
        } else {
//            setDateTime(birthday);
        }
        mTvUserName.setText(userInfo.getNickname());
        mTvTruename.setText(userInfo.getUser_name());
        mTvPhone.setText(userInfo.getTel());
        // 空是未设置，1，男 2，女
        mSex = userInfo.getSex();
        if (TextUtils.isEmpty(mSex)) {
            mTvSex.setText("未设置");
        } else if ("1".equals(mSex)) {
            mTvSex.setText("男");
        } else if ("2".equals(mSex)) {
            mTvSex.setText("女");
        }
        // 空是未设置，1，未婚 2，已婚
        String marry = userInfo.getMarry();
        if (TextUtils.isEmpty(mSex)) {
            mTvMarry.setText("未设置");
        } else if ("1".equals(marry)) {
            mTvMarry.setText("未婚");
        } else if ("2".equals(marry)) {
            mTvMarry.setText("已婚");
        }
        mTvWorknum.setText(userInfo.getWork_number());
        mTvDep.setText(userInfo.getDepartment_name());
        mTvJob.setText(userInfo.getJob_name());
        mTvSignature.setText(userInfo.getSignature());
        mTvBirthday.setText(userInfo.getBirthday() == null ? "未设置" : userInfo.getBirthday());
        Glide.with(this).load(userInfo.getHead_image()).into(mIvPersonalhead);
    }
}
