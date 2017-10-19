package com.wh.jxd.com.refactorqm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wh.jxd.com.refactorqm.R;
import com.wh.jxd.com.refactorqm.base.BaseMvpActivity;
import com.wh.jxd.com.refactorqm.presenter.presenterImpl.ChangePhonePresenter;
import com.wh.jxd.com.refactorqm.utils.CountDownTimerUtils;
import com.wh.jxd.com.refactorqm.utils.ToastUtils;
import com.wh.jxd.com.refactorqm.view.ChangePhoneView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by kevin321vip on 2017/7/24.
 * 更换手机号
 */

public class ChangePhoneNumActivity extends BaseMvpActivity<ChangePhonePresenter, ChangePhoneView> implements ChangePhoneView {

    @Bind(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @Bind(R.id.tv_verificationcode)
    TextView mTvVerificationcode;
    @Bind(R.id.et_verificationcode)
    EditText mEtVerificationcode;
    @Bind(R.id.bt_change_phonenum)
    Button mBtChangePhoneNum;
    ;

    private String mPhone_num;
    private CountDownTimerUtils mCountDownTimerUtils;
    private ChangePhonePresenter mChangePhonePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_phonenum;
    }

    @Override
    protected ChangePhoneView creatView() {
        return this;
    }

    @Override
    public ChangePhonePresenter creatPersenter(Context context) {
        if (mChangePhonePresenter == null) {
            mChangePhonePresenter = new ChangePhonePresenter();
        }
        return mChangePhonePresenter;
    }

    @Override
    public void initView() {
        setToolBarTitle("更换手机号");

    }

    @Override
    public boolean isSystemBarTranclucent() {
        return false;
    }

    @OnClick({R.id.tv_verificationcode, R.id.bt_change_phonenum})
    public void onViewClicked(View view) {
        if (mChangePhonePresenter == null) {
            mChangePhonePresenter = creatPersenter(ChangePhoneNumActivity.this);
        }
        switch (view.getId()) {
            case R.id.tv_verificationcode:
                mPhone_num = mEtPhoneNumber.getText().toString().trim();
                if (TextUtils.isEmpty(mPhone_num)) {
                    ToastUtils.showShortToast(this, "手机号不能为空！");
                    return;
                }
                mCountDownTimerUtils = new CountDownTimerUtils(mTvVerificationcode, 60000, 1000);
                //获取验证码
                mChangePhonePresenter.getVerificationCode(mPhone_num);
                break;
            case R.id.bt_change_phonenum:
                String verificationCode = mEtVerificationcode.getText().toString().trim();
                //校验验证码和手机号
                mChangePhonePresenter.CheckParameters(verificationCode, mPhone_num);
                submitNewNum(verificationCode);
                break;
            default:
                break;
        }
    }

    //提交新号码
    private void submitNewNum(String verificationCode) {
//        String timestamp = Utils.getCurrentTimestamp();
//        String[] signatureArray = NetUtils.getSignatureArray(timestamp, PreferenceUtils.getUserId());
//        OkGo.post(URLConstants.USER_CHANGEPHONE)
//                .params(getString(R.string.手机号), mPhone_num)
//                .params(getString(R.string.时间戳), timestamp)
//                .params(getString(R.string.签名str), signatureArray[1])
//                .params(getString(R.string.签名), signatureArray[0])
//                .params("code", verificationCode)
//                .params(getString(R.string.用户ID), PreferenceUtils.getUserId())
//                .params(getString(R.string.裘马Token),PreferenceUtils.getQM_Token())
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        CheckTheLogin(s);
//                        if ("1".equals(NetUtils.getStatus(s))) {
//                            Intent intent = new Intent(ChangePhoneNumActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }else {
//                            ToastUtil.getInstant().show(NetUtils.getInfo(s));
//                            return;
//                        }
//                    }
//                });


    }

    @Override
    public void onCheckFail(String s) {
        ToastUtils.showShortToast(this, s);

    }

    @Override
    public void oncheckSuccess(String phone_num, String verificationCode) {
        //开始更换手机号
        mChangePhonePresenter.submitNewNum(mPhone_num, verificationCode);
    }

    @Override
    public void getVerificationCodeSuccess() {


    }

    @Override
    public void changePhoneSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    //获取验证码
//    private void getVerificationCode() {
//        String timestamp = Utils.getCurrentTimestamp();
//        HashMap<String, String> signdata = new HashMap<>();
//        signdata.put(getString(R.string.手机号), mPhone_num);
//        signdata.put(getString(R.string.时间戳), timestamp);
//        String[] signData = NetUtils.getSignData(this, signdata);
//        OkGo.post(URLConstants.GET_PHONECODE)
//                .params(getString(R.string.手机号), mPhone_num)
//                .params(getString(R.string.时间戳), timestamp)
//                .params(getString(R.string.签名str), signData[1])
//                .params(getString(R.string.签名), signData[0])
//                .params("type", "3")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        mToastUtil.show("验证码发送失败了");
//                    }
//
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        mToastUtil.show("验证码发送了");
//                        StatusInfo statusInfo = gson.fromJson(s, StatusInfo.class);
//                        if (NetUtils.isRight(s)) {
//                            mToastUtil.show("验证码已发送，请查收!");
//                            if (mCountDownTimerUtils != null) {
//                                mCountDownTimerUtils.start();
//                            }
//                        } else {
//                            mToastUtil.show(statusInfo.getData());
//                        }
//                    }
//                });
//    }
}
