package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;

public class Xzsb2Activity extends BaseActivity {

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzsb2);
        type = getIntent().getExtras().getInt("type", 1);
        initview();
    }

    private void initview() {
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){
                    startActivity(new Intent(Xzsb2Activity.this,PwsmActivity.class));
                }else if(type==2){
                    startActivity(new Intent(Xzsb2Activity.this,Pwsm2Activity.class));
                }else if(type==3){
                    startActivity(new Intent(Xzsb2Activity.this,Pwsm3Activity.class));
                }
            }
        });
    }
}
