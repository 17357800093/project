package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class XgMcActivity extends BaseActivity {
    private EditText mIdEt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xg_mc);
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
        findViewById(R.id.id_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CheckContent()){
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("displayName",mIdEt1.getText().toString());
                UtilsOKHttp.getInstance().put(Constant.URL_displayName, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(XgMcActivity.this,"修改成功！");
                            Constant.myself.getData().setDisplayName(mIdEt1.getText().toString());
                            finish();
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(XgMcActivity.this,codeMsgModel.getErrorMessage());
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
        if(TextUtils.isEmpty(mIdEt1.getText())){
            ToastUtils.showToast(XgMcActivity.this,"密码不能为空");
            return false;
        }
        return true;
    }
}
