package com.wh.jxd.com.refactorqm.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wh.jxd.com.refactorqm.AppcationEx;


/**
 * Created by lgpeng on 2016/2/16 0016.
 */
public class PreferenceUtils {

    public static final String CHECK_TOKEN = "access_token";
    public static final String USER_ID = "member_id";
    public static final String LOGIN_TIME = "login_time";
    public static final String USER_INFO = "user_info_bean";
    public static final String USER_PWD = "pwd";
    public static final String USER_Name = "login_name";
    public static final String CITY_Name = "city";
    public static final String readstatus = "readstatus";
    public static final String Registerid_ID = "registerid";
    public static final String Company_ID = "companyid";

    public static final String RY_TOKEN = "ry_token";
    public static final String QMCT_TOKEN = "qmct_token";
    public static final String APP_SECRET = "app_secret";
    public static final String NEW_VERSION = "new_version";
    public static final String USER_PSW = "user_psw";//用户密码
    public static final String USER_PHONE = "user_phone";//用户密码
    private static final String IS_TEACHER = "is_teacher";//是否为讲师
    private static String companyId;

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences("easycode", Context.MODE_PRIVATE);
    }
    //设置最新的版本
    public static void set_Version(String version) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(NEW_VERSION, version).apply();
    }

    //获取新版本
    public static String get_New_Version() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(NEW_VERSION, "");
    }


    //APP_Secret
    public static void setAPP_Secret(String token) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(APP_SECRET, token).apply();

    }

    //APP_Secret
    public static String getAPP_Secret() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(APP_SECRET, "asd%#!123&");

    }

    //设置融云的Token
    public static void setRY_Token(String token) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(RY_TOKEN, token).apply();

    }

    //获取融云的Token
    public static String getRY_Token() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(RY_TOKEN, "");
    }

    //设置裘马的Token
    public static void setQM_Token(String token) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(QMCT_TOKEN, token).apply();

    }

    //获取裘马的Token
    public static String getQM_Token() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(QMCT_TOKEN, "");

    }

    public static void setToken(String token) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(CHECK_TOKEN, token).apply();

    }

    public static String getToken() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(CHECK_TOKEN, "");
    }

    /**
     * 存放boolean值
     */
    public static void setBoolean(Context ctx, String key, boolean value) {
        getSharedPreferences(ctx).edit().putBoolean(key, value).apply();
    }

    /**
     * 取出boolean值
     */
    public static boolean getBoolean(Context ctx, String key) {
        return getSharedPreferences(ctx).getBoolean(key, false);
    }

    /**
     * 保存userId
     */
    public static void setUserId(String id) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(USER_ID, id).apply();

    }

    /**
     * 获取userId
     */
    public static String getUserId() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(USER_ID, "");
    }

    /**
     * 保存用户第一次登陆时间
     */
    public static void setLoginTime(String loginTime) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(LOGIN_TIME, loginTime)
                .apply();

    }

    /**
     * 获取用户第一次登陆时间
     */
    public static String getLoginTime() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(LOGIN_TIME, "");
    }

    /**
     * 去掉用户第一次登陆时间
     */
    public static void removeLoginTime() {
        getSharedPreferences(AppcationEx.getInstance()).edit().remove("LOGIN_TIME").apply();

    }

    /**
     * 保存到userInfo
     */
    public static void setUserInfoBean(String str) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(USER_INFO, str).apply();

    }

    /**
     * 读取userInfo
     */
    public static String getUserInfoBean() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(USER_INFO, "");
    }

    /**
     * 保存pwd
     */
    public static void setPwd(String pwd) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(USER_PWD, pwd).apply();

    }

    /**
     * 获取pwd
     */
    public static String getPwd() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(USER_PWD, "");
    }

    /**
     * 城市
     */
    public static void setCity(String city) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(CITY_Name, city).apply();

    }

    /**
     * 获取城市
     */
    public static String getCity() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(CITY_Name, "");
    }

    /**
     * 获取是否有消息
     */
    public static void setReadstatus(String id) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(readstatus, id).apply();

    }

    /**
     * 获取是否有消息
     */
    public static String getReadstatus() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(readstatus, "");
    }

    public static void setLoginName(String id) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(USER_Name, id).apply();

    }

    public static String getLoginName() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(USER_Name, "");
    }

    public static void setRegisterid_ID(String id) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(Registerid_ID, id).apply();

    }

    public static String getRegisterid_ID() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(Registerid_ID, "");
    }

    //保存企业ID
    public static void setCompanyId(String companyId) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(Company_ID, companyId)
                .apply();
    }

    //获取企业ID
    public static String getCompanyId() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(Company_ID, "");
    }

    //保存用户密码
    public static void setUser_PSW(String password) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(USER_PSW, password)
                .apply();

    }

    //获取密码
    public static String getUser_Psw() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(USER_PSW, "");
    }

    //保存用户密码
    public static void setUser_Phone(String phone) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putString(USER_PHONE, phone)
                .apply();

    }

    //获取密码
    public static String getUser_Phone() {
        return getSharedPreferences(AppcationEx.getInstance()).getString(USER_PHONE, "");
    }

    //设置是否为讲师
    public static void setIs_Teacher(boolean is_teacher) {
        getSharedPreferences(AppcationEx.getInstance()).edit().putBoolean(IS_TEACHER, is_teacher)
                .apply();
    }
    //获取是否为讲师
    public static boolean getIs_Teacher(){
        return getSharedPreferences(AppcationEx.getInstance()).getBoolean(IS_TEACHER,false);
    }
}
