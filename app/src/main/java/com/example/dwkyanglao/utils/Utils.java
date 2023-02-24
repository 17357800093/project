package com.example.dwkyanglao.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017-02-24.
 */

public class Utils {
    private static long lastClickTime = 0;//上次点击的时间
    private static int spaceTime = 500;//时间间隔

    /**
     * 字符串复制到剪贴板
     *
     * @param context
     * @param text
     */
    public static void copy2clipboard(Context context, String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clip", text);
        cm.setPrimaryClip(clip);
    }

    /**
     * 手指点击是否太快
     *
     * @return true点击得太快了，false没有太快
     */
    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();//当前系统时间
        boolean isAllowClick;//是否允许点击
        if (currentTime - lastClickTime > spaceTime) {
            isAllowClick = false;
        } else {
            isAllowClick = true;
        }
        lastClickTime = currentTime;
        return isAllowClick;
    }

    public static int getRandomNum(){

        Random random = new Random();
        int i = random.nextInt(4);
        if(i==1){
            return 20;
        }else if(i==2){
            return 40;
        }else if(i==3){
            return 60;
        }
        return 80;
    }
    public static int getRandomNumXt(){

        Random random = new Random();
        return random.nextInt(100)+40;

    }
    public static int getRandomNumXt1(){

        Random random = new Random();
        return random.nextInt(80);

    }
    /**
     * 延时执行
     * @param delayTime 延迟时间，单位毫秒
     * @param runnable
     */
    public static void delay2Do(int delayTime, Runnable runnable) {
        new Handler().postDelayed(runnable, delayTime);
    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int rows;
        int columns=0;
        int horizontalBorderHeight=0;
        Class<?> clazz=gridView.getClass();
        try {
            //利用反射，取得每行显示的个数
            Field column=clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            columns=(Integer)column.get(gridView);
            //利用反射，取得横向分割线高度
            Field horizontalSpacing=clazz.getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            horizontalBorderHeight=(Integer)horizontalSpacing.get(gridView);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        //判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
        if(listAdapter.getCount()%columns>0){
            rows=listAdapter.getCount()/columns+1;
        }else {
            rows=listAdapter.getCount()/columns;
        }
        int totalHeight = 0;
        for (int i = 0; i < rows; i++) { //只计算每项高度*行数
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight+horizontalBorderHeight*(rows-1)+25;//最后加上分割线总高度
        gridView.setLayoutParams(params);
    }

    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern
                .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /** * 纯数字

     * @param str

     * @return */

    public static boolean isNumeric(String str){

        for (int i = str.length();--i>=0;){

            if (!Character.isDigit(str.charAt(i))){

                return false;

            }

        }

        return true;}


    /** * 纯字母

     * @param data

     * @return */

    public static boolean isChar(String data) {

        {
            for (int i = data.length(); --i >= 0; ) {

                char c = data.charAt(i);

                if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                    return true;

                } else {

                    return false;

                }

            }
            return true;

        }
    }
    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
    public static long switchCreateTime(String createTime) {
        long formatStr2 = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//注意格式化的表达式
        try {
            Date time = format.parse(createTime );
            String date = time.toString();
            //将西方形式的日期字符串转换成java.util.Date对象
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.US);
            Date datetime = (Date) sdf.parse(date);
            //再转换成自己想要显示的格式
            formatStr2 = datetime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr2+8*60*60*1000;
    }
    public static long switchCreateTime1(String createTime) {
        long formatStr2 = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意格式化的表达式
        try {
            Date time = format.parse(createTime );
            formatStr2 = time.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr2;
    }
    public static long switchCreateTime2(String createTime) {
        long formatStr2 = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//注意格式化的表达式
        try {
            Date time = format.parse(createTime );
            formatStr2 = time.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr2;
    }
    public static String getDateTimeFromMillisecond(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    public static String getDateTimeFromMillisecond1(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    public static String getDateTimeFromMillisecond2(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
    public static String getDateTime(int millisecond){
        if(millisecond<60){
            return millisecond+"分钟";
        }else {
            return millisecond/60+"小时"+millisecond%60+"分钟";
        }
    }
    public static int getDateTimeY(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return Integer.parseInt(dateStr);
    }
    public static int getDateTimeM(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return Integer.parseInt(dateStr);
    }
    public static int getDateTimeD(Long millisecond){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return Integer.parseInt(dateStr);
    }
}
