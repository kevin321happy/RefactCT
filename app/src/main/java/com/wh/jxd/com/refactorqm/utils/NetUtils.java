package com.wh.jxd.com.refactorqm.utils;

import android.content.Context;


import com.wh.jxd.com.refactorqm.AppcationEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 规则工具类,接口加密,以及用户登录验证
 * autour: Kevin
 * company:锦绣氘(武汉)科技有限公司
 * date: 2017/5/27 16:14
 * update: 2017/5/27
 * version: 1.21
 * 站在峰顶 看世界
 * 落在谷底 思人生
 */
public class NetUtils {

    private Map<String, String> signMap = new HashMap<>();
    private static Object info;


    //获取签名后的数据
    public static String[] getSignData(Context context, Map<String, String> map) {
        List<String> dataList = new ArrayList<>();
        StringBuffer signString = new StringBuffer();
        StringBuffer str = new StringBuffer();
        //传入信息放在数组中
        String[] signArray = new String[2];
        //遍历map集合将key值存入新的集合
        for (String key : map.keySet()) {
            dataList.add(key);
        }
        //将data集合排序
        // 升序
        Collections.sort(dataList);
        for (String s : dataList) {
            signString.append("&" + s + "=" + map.get(s));
            str.append(s + "|");
        }
        String s = "asd%#!123&";
        String md5 = Md5Utils.encodeBy32BitMD5(s);
        signString.append("&" + md5.toLowerCase());
        String substring = signString.toString().substring(1);
        signArray[0] = Md5Utils.encodeBy32BitMD5(substring).toLowerCase();
        StringBuffer finStr = str.deleteCharAt(str.length() - 1);
        signArray[1] = finStr.toString();
        if (signArray != null && signArray.length > 0) {
            return signArray;
        }
        return null;
    }

    /**
     * String json
     * 判断是否已经登录了
     */
    public static boolean checkIsLogined(String s) {
        //判断是否已经登录了
        boolean islogined = false;
        try {
            JSONObject jsonObject = new JSONObject(s);
            String is_login = jsonObject.getString("is_login");
            if (is_login.equals("-99")) {
                islogined = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return islogined;
    }

    public static boolean isNewestVersion(String s) {
        boolean isnewest = true;
        try {
            JSONObject jsonObject = new JSONObject(s);
            String is_login = jsonObject.getString("is_login");
            if (is_login.equals("-100")) {
                isnewest = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isnewest;
    }

    /**
     * 获取json里面的数据
     */
    public static String getData(String s) {
        //判断是否已经登录了
        String dataString = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            dataString = jsonObject.getString("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataString;
    }

    //返回data数组
    public static String getDataArray(String s) {
        String dataString = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            //json转数组
            JSONArray data = jsonObject.getJSONArray("data");
            dataString = data.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataString;
    }

    //判断是否正确
    public static boolean isRight(String s) {
        //判断是否已经登录了
        String state = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            state = jsonObject.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "1".equals(state);
    }

    //获取服务器的返回的描述信息
    public static String getInfo(String s) {
        //判断是否已经登录了
        String info = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            info = jsonObject.getString("info");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info;
    }


    //获取服务器的返回的描述信息
    public static String getStatus(String s) {
        //判断是否已经登录了
        String status = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            status = jsonObject.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String[] getSignatureArray(String timestamp, String userid) {
        HashMap<String, String> stringHashMap = new HashMap<>();
        stringHashMap.put("timestamp", timestamp);
        stringHashMap.put("userid", userid);
        return NetUtils.getSignData(AppcationEx.getInstance(), stringHashMap);
    }


}
