package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.LoginModel;
import com.example.dwkyanglao.activity.model.ZhMmSjModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.SharedPreferencesUtils;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MimadlActivity extends BaseActivity {
    private EditText mIdEt1,mIdEt2;
    private CheckBox mIdCb;
    private CheckBox mIdCbYan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimadl);
        initview();
        initdata();
    }

    private void initdata() {
        String phone = SharedPreferencesUtils.getString(MimadlActivity.this, "phone", "");
        if(!phone.isEmpty()){
            ZhMmSjModel zhMmSjModel = new Gson().fromJson(phone, ZhMmSjModel.class);
            mIdEt1.setText(zhMmSjModel.getZh());
        }
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
                HashMap<String, String> map = new HashMap<>();
                map.put("username",mIdEt1.getText().toString());
                map.put("secret",mIdEt2.getText().toString());
                UtilsOKHttp.getInstance().post(Constant.URL_mimalogin, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("pp", "onSuccess: "+result );
                        LoginModel codeMsgModel = new Gson().fromJson(result, LoginModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ZhMmSjModel zhMmSjModel = new ZhMmSjModel();
                            zhMmSjModel.setZh(mIdEt1.getText().toString());
                            zhMmSjModel.setMm(mIdEt2.getText().toString());
                            zhMmSjModel.setTime(System.currentTimeMillis());
                            SharedPreferencesUtils.putString(MimadlActivity.this,"phone",new Gson().toJson(zhMmSjModel));
                            Constant.myself=codeMsgModel;
                            ToastUtils.showToast(MimadlActivity.this,"登陆成功！");
                            startActivity(new Intent(MimadlActivity.this, MainActivity.class));
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(MimadlActivity.this,codeMsgModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {
                        Log.e("pp", "failResult: "+failResult );
                    }
                });
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
        if(mIdEt2.getText().toString().length()<8){
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
