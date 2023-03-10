package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class XgmmActivity extends BaseActivity {

    private EditText mIdEt1,mIdEt2,mIdEt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xgmm);
        initview();
    }

    private void initview() {
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        mIdEt2 = ((EditText) findViewById(R.id.id_et2));
        mIdEt3 = ((EditText) findViewById(R.id.id_et3));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CheckContent()){
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("oldSecret",mIdEt1.getText().toString());
                map.put("secret",mIdEt2.getText().toString());
                UtilsOKHttp.getInstance().put(Constant.URL_updpassword, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(XgmmActivity.this,"???????????????");
                            finish();
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(XgmmActivity.this,codeMsgModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {
                        Log.e("pp", "failResult: "+failResult );
                    }
                });
            }
        });
    }
    private boolean CheckContent() {
        if(TextUtils.isEmpty(mIdEt1.getText())||TextUtils.isEmpty(mIdEt2.getText())||TextUtils.isEmpty(mIdEt3.getText())){
            ToastUtils.showToast(XgmmActivity.this,"??????????????????");
            return false;
        }
        if(mIdEt1.getText().toString().length()<8||mIdEt2.getText().toString().length()<8||mIdEt3.getText().toString().length()<8){
            ToastUtils.showToast(XgmmActivity.this,"???????????????8-16");
            return false;
        }
        if(!mIdEt2.getText().toString().equals(mIdEt3.getText().toString())){
            ToastUtils.showToast(XgmmActivity.this,"?????????????????????????????????");
            return false;
        }
        return true;
    }
}
