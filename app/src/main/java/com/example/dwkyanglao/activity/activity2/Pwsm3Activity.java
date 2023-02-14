package com.example.dwkyanglao.activity.activity2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;

public class Pwsm3Activity extends BaseActivity {
    private TextView mIdTv1,mIdTv2,mIdTv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwsm3);
        initview();
        initdata();
    }
    private void initdata() {
        //1.同时长按控制盒右侧的1号+2号按键8秒左右(下方图所示)，直至显示屏3出现 闪烁。
        String msg1="<font color='#CCCCCC'>1.同时长按控制盒右侧的</font>1号+2号<font color='#CCCCCC'>按键8秒左右</font>(下方图所示)<font color='#CCCCCC'>，直至</font>显示屏3<font color='#CCCCCC'>出现</font><img src='"+R.drawable.wifi+"'> 闪烁";
        mIdTv1.setText(Html.fromHtml(msg1, new Html.ImageGetter() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override  public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                Drawable drawable = getResources().getDrawable(id, null);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth() ,
                        drawable.getIntrinsicHeight());    return drawable;
            }
        }, null));
        //2.确保手机开启蓝牙功能，点击底部”开始搜索”按钮系统会自动识别显示设备序列号。
        String msg2="<font color='#CCCCCC'>2.确保手机开启蓝牙功能，点击底部</font>”开始搜索”<font color='#CCCCCC'>按钮系统会自动识别显示设备序列号。</font>";
        mIdTv2.setText(Html.fromHtml(msg2));

        //4.配网完成。显示屏3中
        String msg3="<font color='#CCCCCC'>4.配网完成。</font>显示屏3<font color='#CCCCCC'>中</font><img src='"+R.drawable.wifi+"'> 和"+"<img src='"+R.drawable.fuwuqi+"'> 常亮显示。";
        mIdTv3.setText(Html.fromHtml(msg3, new Html.ImageGetter() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override  public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                Drawable drawable = getResources().getDrawable(id, null);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth() ,
                        drawable.getIntrinsicHeight());    return drawable;
            }
        }, null));
    }

    private void initview() {
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) findViewById(R.id.id_tv2));
        mIdTv3 = ((TextView) findViewById(R.id.id_tv3));
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Pwsm3Activity.this,LnayaliebiaoActivity.class));
            }
        });
    }
}
