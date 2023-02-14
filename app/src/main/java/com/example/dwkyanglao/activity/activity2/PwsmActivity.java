package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PwsmActivity extends BaseActivity {

    private TextView mIdTv1,mIdTv2,mIdTv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwsm);
        initview();
        initdata();
    }

    private void initdata() {
        //1.长按体征垫黑色控制盒外侧的2号配网按键(下方图所示)，直至指示灯3呈现蓝色常亮。
        String msg1="<font color='#CCCCCC'>1.长按体征垫黑色控制盒外侧的2号配网按键</font>(下方图所示)<font color='#CCCCCC'>，直至</font>指示灯3<font color='#CCCCCC'>呈现蓝色常亮。</font>";
        mIdTv1.setText(Html.fromHtml(msg1));

        //2.确保手机开启蓝牙功能，点击底部”开始搜索”按钮系统会自动识别显示设备序列号。
        String msg2="<font color='#CCCCCC'>2.确保手机开启蓝牙功能，点击底部</font>”开始搜索”<font color='#CCCCCC'>按钮系统会自动识别显示设备序列号。</font>";
        mIdTv2.setText(Html.fromHtml(msg2));

        //4.配网完成。控制指示灯3变为绿灯时可开始正常使用。
        String msg3="<font color='#CCCCCC'>4.配网完成。控制</font>指示灯3<font color='#CCCCCC'>变为绿灯时可开始正常使用。</font>";
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
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PwsmActivity.this,LnayaliebiaoActivity.class));
            }
        });
    }


}
