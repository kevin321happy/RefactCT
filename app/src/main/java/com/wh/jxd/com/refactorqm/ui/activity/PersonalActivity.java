package com.wh.jxd.com.refactorqm.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
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
import com.wh.jxd.com.refactorqm.view.widget.SingleChooseDialog;

import java.util.ArrayList;
import java.util.Calendar;

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
    private ArrayList<String> mStrings;
    private String mMarry;
    private Object mDateTime;

    private boolean mFired;
    private int mYear;
    private int mMonth;
    private int mDay;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_DATAPICK = 0;



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
            setDateTime(null);
        } else {
            setDateTime(birthday);
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
        mMarry = userInfo.getMarry();
        if (TextUtils.isEmpty(mSex)) {
            mTvMarry.setText("未设置");
        } else if ("1".equals(mMarry)) {
            mTvMarry.setText("未婚");
        } else if ("2".equals(mMarry)) {
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
        mSex = sex;
        mTvSex.setText("1".equals(sex) ? "男" : "女");
    }
    @Override
    public void updataMarrySuccess(String marry) {
        mMarry = marry;
        if ("1".equals(marry)) {
            mTvMarry.setText("未婚");
        } else if ("2".equals(marry)) {
            mTvMarry.setText("已婚");
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
    public void updataSignTureSuccess(String value) {
        mTvSignature.setText(value);
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
                mFired = false;
                Message msg = new Message();
                msg.what = SHOW_DATAPICK;
                PersonalActivity.this.saleHandler.sendMessage(msg);
                break;
            case R.id.ll_sex:
                //性别
                mStrings = new ArrayList<>();
                mStrings.add("男");
                mStrings.add("女");
                showSingleChooseDialog("sex", "性別", mStrings);
                break;
            case R.id.ll_wedlock:
                mStrings = new ArrayList<>();
                mStrings.add("未婚");
                mStrings.add("已婚");
                showSingleChooseDialog("marry", "婚恋", mStrings);
                //婚否
                break;
            case R.id.ll_autograph:
                //个性签名
                showEditInfoDialog("signature", "个性签名");
                break;
            default:
                break;
        }
    }

    /**
     * 显示单选对话框
     */
    private void showSingleChooseDialog(final String marry, String title, final ArrayList<String> strings) {
        SingleChooseDialog singleChooseDialog = new SingleChooseDialog(this, title);
        singleChooseDialog.setListitems(strings);
        //显示当前的状态
        if ("marry".equals(marry)) {
            singleChooseDialog.setCheckedPosition("1".equals(mMarry) ? 0 : 1);
        } else if ("sex".equals(marry)) {
            singleChooseDialog.setCheckedPosition("1".equals(mSex) ? 0 : 1);
        }
        singleChooseDialog.show();
        singleChooseDialog.setOnChooseItemClick(new SingleChooseDialog.onChooseItemClick() {
            @Override
            public void onItemChoose(int position) {
                if (mPersonalPresenter == null) {
                    return;
                }
                if (position == 0) {
                    mPersonalPresenter.upDataInfo(marry, "1");
                } else if (position == 1) {
                    mPersonalPresenter.upDataInfo(marry, "2");
                } else {
                    mPersonalPresenter.upDataInfo(marry, "0");
                }
            }
        });
    }

    /**
     * 显示编辑信息的对话框
     *
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

    /**
     * 功能：初始化日期
     */
    private void setDateTime(String birthday) {
        if (null == birthday) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        } else {
            String[] birthdayString = birthday.split("-");
            mYear = Integer.valueOf(birthdayString[0]);
            mMonth = Integer.valueOf(birthdayString[1]) - 1;
            mDay = Integer.valueOf(birthdayString[2] == null ? "1" : birthdayString[2]);
        }
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            default:
                break;
        }
        return null;
    }


    /**
     * 更新日期
     */
    private void updateDisplay() {
        String birth = new StringBuilder().append(mYear).append("-").append(
                (mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-").append(
                (mDay < 10) ? "0" + mDay : mDay).toString();
        mTvBirthday.setText(birth);
        if (mPersonalPresenter!=null){
            mPersonalPresenter.upDataInfo("birthday",birth);
        }
    }


    /**
     * 日期控件的事件
     */
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            if (!mFired) {
                mFired = true;
                updateDisplay();
            }
        }
    };

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
            default:
                break;
        }
    }

    /**
     * 处理日期控件的Handler
     */
    Handler saleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    showDialog(DATE_DIALOG_ID);
                    break;
                default:
                    break;
            }
        }
    };

}
