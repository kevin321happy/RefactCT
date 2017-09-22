package com.wh.jxd.com.mvpsimple.utils;

import android.Manifest.permission;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //验证手机号

    /**
     * 验证手机格式
     */
    public static boolean isnumber(String s) {
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        return b;
    }

    //判断是否有网络
    public static boolean isNetWork(Context context) {
        //得到网络的管理者
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null;
    }

    /**
     * make true current connect service is wifi
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 判断服务是否开启
     */
    public static boolean isServiceWork(Context context, String ServiceName) {
        boolean isWork = false;
        ActivityManager am = (ActivityManager) ((Activity) context)
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> runningServices = am.getRunningServices(40);
        if (runningServices.size() < 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            String mName = runningServices.get(i).service.getClassName().toString();
            if (mName.equals(ServiceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        // 有存储的SDCard
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    //保存图片
    public static void saveMyBitmap(Bitmap mBitmap, String pic_pathload) {
        File f = new File("storage/sdcard0/" + pic_pathload);

        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 时间戳转化为Date（or String）
     */
    public static String TimeStampToData() {
        String data = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = System.currentTimeMillis();
        data = format.format(time);
        return data;
    }

    /**
     * 长整型数字转日期, 返回字符串形式的日期
     */
    public static String getTime(long timeMillis) {
        return sdf.format(new Date(timeMillis));
    }

    public static String getMoney(String money) {
        BigDecimal bd = new BigDecimal(money);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }

    public static String[] getStr(String a) {
        String[] split = a.split(",");
        return split;
    }

    /*需要17才能使用*/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Bitmap bitmap, Context context) {

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap
                .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(10.f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;


    }

    public static int ScreenOrient(Activity activity) {
        int orient = activity.getRequestedOrientation();
        if (orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        }
        return orient;
    }

    public static int compareString(String a, String b) {
        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        BigDecimal a2 = BigDecimal.valueOf(a1);
        BigDecimal b2 = BigDecimal.valueOf(b1);
        int c = a2.compareTo(b2);
        return c;
    }

    /*
   * 将时间转换为时间戳
   */
    public static long dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        // res = String.valueOf(ts);
        return ts;
    }

    /*
  * 将时间戳转化为时间格式的文件路径
  *
  */
    public static String stampToDateString(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        String s1 = res.substring(0, res.length() - 2).replaceAll("-", "").replaceAll(":", "")
                .replaceAll(" ", "");
        return s1;
    }

    /*
   * 将时间2016-02-01转化为2016年02月01日
   */
    public static String formatDateString(String activitytime) {
        Log.i("test", activitytime);
        StringBuffer stringBuffer = new StringBuffer(activitytime);
        stringBuffer.replace(4, 5, "年");
        stringBuffer.replace(7, 8, "月");
        return stringBuffer.toString();
    }

    //获取随机数
    public static int getRandom(int a) {
        int val = (int) (Math.random() * a + 1);
        return val; //第一种
    }

    //获取文件的拓展名
    public static String getFileExt(String fileName) {
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }

    /**
     * 转换文件大小
     */
    public static String FormentFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 加载本地图片
     * http://bbs.3gstdy.com
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间戳的秒值,String类型的
     */
    public static String getCurrentTimestamp() {
        long time = System.currentTimeMillis() / 1000;
        String timestamp = String.valueOf(time);
        return timestamp;
    }

    /**
     * 获取当前时间戳的秒值,long类型的秒值
     */
    public static long getCurrentTime() {
        long time = System.currentTimeMillis() / 1000;
        return time;
    }

    /**
     * 获取定位信息
     */
    public static Location getLocation(Context context) {

        String locationProvider;

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            return null;
        }
        //获取Location
        if (ActivityCompat.checkSelfPermission(context, permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location location = lm.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            return location;
        }
        return null;
    }

    public static String ToCH(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) // 個
        {
            sd += GetCH(intInput);
            return sd;
        } else if (si.length() == 2)// 十
        {
            if (si.substring(0, 1).equals("1"))
                sd += "十";
            else
                sd += (GetCH(intInput / 10) + "十");
            sd += ToCH(intInput % 10);
        } else if (si.length() == 3)// 百
        {
            sd += (GetCH(intInput / 100) + "百");
            if (String.valueOf(intInput % 100).length() < 2)
                sd += "零";
            sd += ToCH(intInput % 100);
        } else if (si.length() == 4)// 千
        {
            sd += (GetCH(intInput / 1000) + "千");
            if (String.valueOf(intInput % 1000).length() < 3)
                sd += "零";
            sd += ToCH(intInput % 1000);
        } else if (si.length() == 5)// 萬
        {
            sd += (GetCH(intInput / 10000) + "萬");
            if (String.valueOf(intInput % 10000).length() < 4)
                sd += "零";
            sd += ToCH(intInput % 10000);
        }

        return sd;
    }

    private static String GetCH(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

    /**
     * 将毫秒值转化为时分秒的格式
     */
    public static String timestampToFormatTime(long duration) {
        String time = "";
        long minute = duration / 60000;
        long seconds = duration % 60000;
        long second = Math.round((float) seconds / 1000);
        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }
}
