package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.activity.model.LoginModel;
import com.example.dwkyanglao.activity.model.PhoneLoginModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.example.dwkyanglao.view.SecurityCodeView;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class YzmActivity extends BaseActivity implements SecurityCodeView.InputCompleteListener{

    private SecurityCodeView mIdScv;
    private String yzm;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzm);
        phone = getIntent().getStringExtra("phone");
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
                HashMap<String, String> map = new HashMap<>();
                map.put("username",phone);
                map.put("code",mIdScv.getEditContent());
                UtilsOKHttp.getInstance().post(Constant.URL_mobileLogin, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("pp", "onSuccess: "+result );
                        LoginModel phoneLoginModel = new Gson().fromJson(result, LoginModel.class);
                        if(phoneLoginModel!=null&&phoneLoginModel.getCode()==0){
                            Constant.TOKEN=phoneLoginModel.getData().getToken();
                            if(phoneLoginModel.getData().getBirthDay()==null){
                                startActivity(new Intent(YzmActivity.this,SzmmActivity.class));
                            }else {
                                Constant.myself=phoneLoginModel;
                                ToastUtils.showToast(YzmActivity.this,"登陆成功！");
                                startActivity(new Intent(YzmActivity.this, MainActivity.class));
                            }
                        }else{
                            ToastUtils.showToast(YzmActivity.this,phoneLoginModel.getErrorMessage());
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
