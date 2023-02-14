package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.view.SecurityCodeView;
import com.github.lazylibrary.util.ToastUtils;

public class YzmActivity extends BaseActivity implements SecurityCodeView.InputCompleteListener{

    private SecurityCodeView mIdScv;
    private String yzm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzm);
        initview();
    }

    private void initview() {
        mIdScv = ((SecurityCodeView) findViewById(R.id.scv_edittext));
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
                startActivity(new Intent(YzmActivity.this,SzmmActivity.class));
            }
        });
    }

    private boolean CheckContent() {
        if(mIdScv.getEditContent()==null||mIdScv.getEditContent().isEmpty()||mIdScv.getEditContent().length()!=6){
            ToastUtils.showToast(YzmActivity.this,"请输入正确的验证码");
            return false;
        }

        return true;
    }

    @Override
    public void inputComplete() {

    }

    @Override
    public void deleteContent(boolean isDelete) {

    }
}
