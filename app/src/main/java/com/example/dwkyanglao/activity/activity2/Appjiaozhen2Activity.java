package com.example.dwkyanglao.activity.activity2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.view.ProgressBarView2;

public class Appjiaozhen2Activity extends BaseActivity {

    private ProgressBarView2 mPbv;
    private TextView mIdTv1,mIdTv2;
    private Button mIdBt1;
    private Handler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appjiaozhen2);
        mhandler=new Handler();
        initview();
        initdata();
    }
    public Runnable runnaUi=new Runnable() {
        @Override
        public void run() {
            mIdTv1.setText("空床校正成功");
            mIdTv2.setVisibility(View.VISIBLE);
            mIdBt1.setVisibility(View.VISIBLE);
            mIdBt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIdTv1.setText("负重校正中");
                    fuzhon();
                    mIdTv2.setVisibility(View.GONE);
                    mIdBt1.setVisibility(View.GONE);
                }
            });
        }
    };
    public Runnable runnaUi1=new Runnable() {
        @Override
        public void run() {
            mIdTv1.setText("负重校正成功");
            mIdTv2.setVisibility(View.VISIBLE);
            mIdTv2.setText("您可以正常使用体征垫了");
            mIdBt1.setText("完成");
            mIdBt1.setVisibility(View.VISIBLE);
            mIdBt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();
                }
            });
        }
    };
    private void initdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 1; i <= 100; i++) {
                    mPbv.setProgressSync(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mhandler.post(runnaUi);

            }
        }).start();
    }
    private void fuzhon() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    mPbv.setProgressSync(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mhandler.post(runnaUi1);

            }
        }).start();
    }

    private void initview() {
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPbv = ((ProgressBarView2) findViewById(R.id.id_pbv));
        mPbv.setMax(100);
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) findViewById(R.id.id_tv2));
        mIdBt1 = ((Button) findViewById(R.id.id_bt1));
    }
}
