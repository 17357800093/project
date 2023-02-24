package com.example.dwkyanglao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.dwkyanglao.utils.SharedPreferencesUtils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.facebook.drawee.backends.pipeline.Fresco;

public class AppApplication extends Application {
    private static AppApplication instance;
    // 当前person对象
    private Typeface typeface;

    public static AppApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        UtilsOKHttp.getInstance(getApplicationContext());
        typeface = Typeface.createFromAsset(getAssets(), "fonts/ziti.TTF");
    }

    public Typeface getTypeface() {
        return typeface;
    }
}
