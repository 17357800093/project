package com.example.dwkyanglao.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 本地持久化
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_date";


    /**
     * 保存数据的方法，需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param context
     * @param key
     * @param object
     */
    public static void putObject(Context context , String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }

        editor.commit();
    }


    public static void putString(Context context , String key, String value){
        putObject(context, key, value);
    }

    public static void putInt(Context context , String key, int value){
        putObject(context, key, value);
    }

    public static void putBoolean(Context context , String key, boolean value){
        putObject(context, key, value);
    }

    public static void putLong(Context context , String key, long value){
        putObject(context, key, value);
    }

    public static void putFloat(Context context , String key, float value){
        putObject(context, key, value);
    }

    public static String getString(Context context , String key, String defaultValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static int getInt(Context context , String key, int defaultValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static boolean getBoolean(Context context , String key, boolean defaultValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static float getFloat(Context context , String key, float defaultValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static float getLong(Context context , String key, long defaultValue){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }


    /**
     * 清除所有数据
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    /**
     * 清除指定数据
     * @param context
     */
    public static void clearAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("定义的键名");
        editor.commit();
    }

}
