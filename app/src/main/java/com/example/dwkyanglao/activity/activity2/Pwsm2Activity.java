package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;

public class Pwsm2Activity extends BaseActivity {
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwsm2);
        initview();
        initdata();
    }

    private void initdata() {
        //1.保证防褥疮垫已接通电源。指示灯2呈绿色。
        String msg1="<font color='#CCCCCC'>1.保证防褥疮垫已接通电源。</font>";
        mIdTv1.setText(Html.fromHtml(msg1));

        //2.长按控制盒左侧的锁定/解锁按键3秒，直至指示灯2熄灭。
        String msg2="<font color='#CCCCCC'>2.长按控制盒左侧的</font>锁定/解锁按键<font color='#CCCCCC'>3秒，直至</font>指示灯2<font color='#CCCCCC'>熄灭。</font>";
        mIdTv2.setText(Html.fromHtml(msg2));

      //3.连续按5次锁定/解锁按键。
        String msg4="<font color='#CCCCCC'>3.连续按5次</font>锁定/解锁按键。";
        mIdTv4.setText(Html.fromHtml(msg4));

        //3.确保手机开启蓝牙功能，点击底部”开始搜索”按钮系统会自动识别显示设备序列号。
        String msg3="<font color='#CCCCCC'>3.确保手机开启蓝牙功能，点击底部</font>”开始搜索”<font color='#CCCCCC'>按钮系统会自动识别显示设备序列号。</font>";
        mIdTv3.setText(Html.fromHtml(msg3));
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
        mIdTv4 = ((TextView) findViewById(R.id.id_tv4));
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pwsm2Activity.this,LnayaliebiaoActivity.class));
            }
        });
    }

}
