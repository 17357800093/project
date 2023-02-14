package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.utils.Utils;
import com.github.lazylibrary.util.ToastUtils;

public class SzmmActivity extends BaseActivity {

    private EditText mIdEt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szmm);
        initview();
    }

    private void initview() {
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CheckContent()){
                    return;
                }
                startActivity(new Intent(SzmmActivity.this,WsgrxxActivity.class));
            }
        });
    }

    private boolean CheckContent() {
        if(TextUtils.isEmpty(mIdEt1.getText())){
            ToastUtils.showToast(SzmmActivity.this,"密码不能为空");
            return false;
        }
        String mima = mIdEt1.getText().toString();
        if(mima.length()<6){
            ToastUtils.showToast(SzmmActivity.this,"密码长度为8-16");
            return false;
        }
        if(Utils.isNumeric(mima)){
            ToastUtils.showToast(SzmmActivity.this,"密码不能由纯数字组成");
            return false;
        }
        return true;
    }
}
