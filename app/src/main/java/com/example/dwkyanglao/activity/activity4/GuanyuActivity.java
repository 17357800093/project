package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.event.RefreshShouye;
import com.example.dwkyanglao.manage.BaseActivity;

import org.greenrobot.eventbus.EventBus;

public class GuanyuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        initview();
    }

    private void initview() {
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
