package com.example.dwkyanglao.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.example.dwkyanglao.manage.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：admin
 * 时间：2018/5/23 0023
 * 用途：
 */

public class UtilsOKHttp {
    private static OkHttpClient client;
    private volatile static UtilsOKHttp manager;
    private Handler handler;

    private UtilsOKHttp(Context context) {
        client = new OkHttpClient.Builder()
                .build();
        handler = new Handler(Looper.getMainLooper());
    }

    public static UtilsOKHttp getInstance(Context context) {
        if (manager == null) {
            manager = new UtilsOKHttp(context);
        }
        return manager;
    }

    public static UtilsOKHttp getInstance() {
        return manager;
    }

    public void get(String url, final OKCallback callBack) {
        Log.e("pp", "get: "+url );
        get(url, null, callBack);
    }

    public void get(String url, HashMap<String, String> params, final OKCallback callBack) {
        if (params != null && params.size() > 0) {
            url = makeGetUrl(url, params);
        }
        Request request =  new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onFailJsonStringMethod("请求失败", callBack);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    String string = response.body().string();
                    onSuccessJsonStringMethod(string, callBack);
                } else {
                    onFailJsonStringMethod("请求失败", callBack);
                }
            }
        });
    }


    public void post(String url, String params, final OKCallback callBack) {
        Log.e("phw", "post: "+url );
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        Request request = new Request.Builder().url(url).post(requestBody).build();

        //采用post方式提交
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onFailJsonStringMethod("请求失败", callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonStringMethod(response.body().string(), callBack);
//                    if(Constant.COOKIE!=null&&Constant.COOKIE.isEmpty()){
//                        String cook = response.headers().get("Set-Cookie");
//                        if(cook!=null&&!cook.isEmpty()){
//                            Constant.COOKIE=cook;
//                        }
//                    }
                } else {
                    onFailJsonStringMethod("请求失败", callBack);
                    Log.e("pp", "onFailure:ww "+response.message() );

                }
            }
        });
    }
    public void put(String url, String params, final OKCallback callBack) {
        Log.e("phw", "put: "+url );
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        Request request = new Request.Builder().url(url).put(requestBody).build();

        //采用post方式提交
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                onFailJsonStringMethod("请求失败", callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonStringMethod(response.body().string(), callBack);
//                    if(Constant.COOKIE!=null&&Constant.COOKIE.isEmpty()){
//                        String cook = response.headers().get("Set-Cookie");
//                        if(cook!=null&&!cook.isEmpty()){
//                            Constant.COOKIE=cook;
//                        }
//                    }
                } else {
                    onFailJsonStringMethod("请求失败", callBack);
                    Log.e("pp", "onFailure:ww "+response.message() );

                }
            }
        });
    }



    /**
     * 请求返回的结果是json字符串 * * @param jsonValue * @param callBack
     */
    private void onSuccessJsonStringMethod(final String jsonValue, final OKCallback callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onSuccess(jsonValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void onFailJsonStringMethod(final String str, final OKCallback callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onFail(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public interface OKCallback {
        void onSuccess(String result);

        void onFail(String failResult);
    }

    /**
     * 拼接get的url * * @param params * @return
     */
    private String makeGetUrl(String oldUrl, HashMap<String, String> params) {
        String url = oldUrl;
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            url += sb.toString();
        }
        return url;
    }
}