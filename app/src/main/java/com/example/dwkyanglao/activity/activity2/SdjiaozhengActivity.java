package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;

public class SdjiaozhengActivity extends BaseActivity {
    private TextView mIdTv1,mIdTv2,mIdTv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdjiaozheng);
        initview();
        initdata();
    }
    private void initdata() {
        //2.长按体征垫黑色控制盒外侧的1号校正按键，直至指示灯3呈现青蓝色闪烁。
        String msg1="2.长按体征垫黑色控制盒外侧的<font color='#000000'>1号校正按键</font>，直至<font color='#000000'>指示灯3</font>呈现青蓝色闪烁。";
        mIdTv1.setText(Html.fromHtml(msg1));

        //3.当指示灯3由青蓝色转换为绿色闪烁时，请平躺于监测垫上。并再次长按1号校正按键直至指示灯3呈现紫色闪烁。
        String msg2="3.当<font color='#000000'>指示灯3</font>由青蓝色转换为绿色闪烁时，请平躺于监测垫上。并再次长按<font color='#000000'>1号校正按键</font>直至<font color='#000000'>指示灯3</font>呈现紫色闪烁。";
        mIdTv2.setText(Html.fromHtml(msg2));

        //4.当指示灯3由紫色转换为绿色时，设备校正成功。
        String msg3="4.当<font color='#000000'>指示灯3</font>由紫色转换为绿色时，设备校正成功。";
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
    }
}
