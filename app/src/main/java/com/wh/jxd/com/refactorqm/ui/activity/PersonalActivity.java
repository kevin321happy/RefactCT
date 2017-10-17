package com.wh.jxd.com.refactorqm.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.model.UserInfo;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.PersonalPresenterImpl;
import com.wh.jxd.com.refactorqm.utils.AlertDialogUtils;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.PersonalView;
import com.wh.jxd.com.refactorqm.view.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
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

    @Override
    public void updataNameSuccess(String name) {
        //修改名字成功了
        mTvTruename.setText(name);
        mPersonalPresenter.getUserInfo();
        ToastUtils.showShortToast(this, "修改成功了 ：" + name);
    }

    @Override
    public void updataNicknameSuccess(String nickname) {
        mTvUserName.setText(nickname);
        mPersonalPresenter.getUserInfo();
    }
    @Override
    public void updataBirthDaySuccess(String birthDay) {

    }

    @Override
    public void updataSexSuccess(String sex) {
        mTvSex.setText("1".equals(sex) ? "男" : "女");

    }

    @Override
    public void updataMarrySuccess(String marry) {
        if ("1".equals(marry)) {

        } else if ("2".equals(marry)) {

        } else {
            mTvSex.setText("暂未设置");
        }
    }

    @Override
    public void updataSignaTureSuccess(String signature) {
        mTvSignature.setText(signature);

    }

    @Override
    public void updataHeadImaSuccess(String headIma) {
        Glide.with(this).load(headIma).into(mIvPersonalhead);

    }

    @Override
    public void updataInfoFail(String s) {
        ToastUtils.showShortToast(this, "修改失败了:" + s);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_personalhead, R.id.ll_user_name, R.id.ll_personalname, R.id.ll_birthday, R.id.ll_sex, R.id.ll_wedlock, R.id.ll_autograph})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_personalhead:
                //更换头像
                break;
            case R.id.ll_user_name:
                showEditInfoDialog("nickname", "更换昵称");
                //用户名
                break;
            case R.id.ll_personalname:
                //真名
                showEditInfoDialog("member_name", "更换姓名");
                break;
            case R.id.ll_birthday:
                //生日
                break;
            case R.id.ll_sex:
                //性别
                break;
            case R.id.ll_wedlock:
                //婚否
                break;
            case R.id.ll_autograph:
                //个性签名
                break;
            default:
                break;
        }
    }

    /**
     * 显示编辑信息的对话框
     * @param title
     */
    private void showEditInfoDialog(final String key, String title) {
        AlertDialogUtils.showTowBtnInputDialog(this, title, "取消", "确定", new AlertDialogUtils.DialogInputInter() {
            @Override
            public void leftClick(AlertDialog dialog) {
                dialog.dismiss();
            }
            @Override
            public void submit(String content, AlertDialog dialog) {
                dialog.dismiss();
                if (mPersonalPresenter != null) {
                    mPersonalPresenter.upDataInfo(key, content);
                }
            }
        }).show();
    }
}
