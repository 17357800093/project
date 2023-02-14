package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.utils.Utils;
import com.github.lazylibrary.util.ToastUtils;

public class SjhzcActivity extends BaseActivity {

    private EditText mIdEt1;
    private CheckBox mIdCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjhzc);
        initview();
    }

    private void initview() {
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        mIdCb = ((CheckBox) findViewById(R.id.id_cb));
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
                startActivity(new Intent(SjhzcActivity.this,YzmActivity.class));
            }
        });
        findViewById(R.id.id_mmdl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SjhzcActivity.this,MimadlActivity.class));
            }
        });
    }

    private boolean CheckContent() {
        if(TextUtils.isEmpty(mIdEt1.getText())){
            ToastUtils.showToast(SjhzcActivity.this,"手机号不能为空");
            return false;
        }
        if(!Utils.isMobileNO(mIdEt1.getText().toString())){
            ToastUtils.showToast(SjhzcActivity.this,"手机号错误");
            return false;
        }
        if(!mIdCb.isChecked()){
            ToastUtils.showToast(SjhzcActivity.this,"请阅读并同意《软件许可及服务协议》");
            return false;
        }
        return true;
    }


}
