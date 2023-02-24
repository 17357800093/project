package com.example.dwkyanglao.activity.activity2;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;

import com.just.library.AgentWeb;

public class WebActivity extends BaseActivity {

    private LinearLayout layout;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        layout = ((LinearLayout) findViewById(R.id.id_layout));

        int type = getIntent().getIntExtra("type",1);
        if(type==1){
            mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                    .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .createAgentWeb()//
                    .ready()
                    .go("https://m.tb.cn/h.UpZaA9J");
        }else {
            mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                    .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .createAgentWeb()//
                    .ready()
                    .go("https://shop.m.jd.com/?shopId=12021661");
        }


    }

    public void onBackPressed() {
        if (mAgentWeb!=null&&mAgentWeb.getIEventHandler().back()){
            Log.i("callBack", "go back");
        }else {
            Log.i("callBack", "finish");
            finish();
        }
    }
    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
