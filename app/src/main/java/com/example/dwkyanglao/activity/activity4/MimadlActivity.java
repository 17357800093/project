package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.utils.Utils;
import com.github.lazylibrary.util.ToastUtils;

import java.lang.reflect.Type;

public class MimadlActivity extends BaseActivity {
    private EditText mIdEt1,mIdEt2;
    private CheckBox mIdCb;
    private CheckBox mIdCbYan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimadl);
        initview();
    }

    private void initview() {
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        mIdEt2 = ((EditText) findViewById(R.id.id_et2));
        mIdCb = ((CheckBox) findViewById(R.id.id_cb));
        mIdCbYan = ((CheckBox) findViewById(R.id.id_cb_yan));
        mIdCbYan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mIdEt2.setInputType(InputType.TYPE_CLASS_TEXT);
                }else {
                    mIdEt2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD |
                            InputType.TYPE_CLASS_TEXT);
                }
            }
        });
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
                startActivity(new Intent(MimadlActivity.this, MainActivity.class));
            }
        });
        findViewById(R.id.id_yzmdl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MimadlActivity.this, SjhzcActivity.class));
            }
        });

    }

    private boolean CheckContent() {
        if(TextUtils.isEmpty(mIdEt1.getText())){
            ToastUtils.showToast(MimadlActivity.this,"手机号不能为空");
            return false;
        }
        if(!Utils.isMobileNO(mIdEt1.getText().toString())){
            ToastUtils.showToast(MimadlActivity.this,"手机号错误");
            return false;
        }
        if(TextUtils.isEmpty(mIdEt2.getText())){
            ToastUtils.showToast(MimadlActivity.this,"密码不能为空");
            return false;
        }
        if(mIdEt2.getText().toString().length()<6){
            ToastUtils.showToast(MimadlActivity.this,"密码长度为8-16");
            return false;
        }
        if(!mIdCb.isChecked()){
            ToastUtils.showToast(MimadlActivity.this,"请阅读并同意《软件许可及服务协议》");
            return false;
        }
        return true;
    }

}
