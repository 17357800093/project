package com.example.dwkyanglao.activity.activity4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

import java.util.HashMap;

import static android.content.Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;

public class SjhzcActivity extends BaseActivity {

    private EditText mIdEt1;
    private CheckBox mIdCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjhzc);
        initview();
        initdata();
    }

    private void initdata() {
        String phone = SharedPreferencesUtils.getString(SjhzcActivity.this, "phone", "");
        if(!phone.isEmpty()){
            ZhMmSjModel zhMmSjModel = new Gson().fromJson(phone, ZhMmSjModel.class);
            Log.e("pp", "initdata: "+zhMmSjModel.getMm()+"  "+(System.currentTimeMillis()-zhMmSjModel.getTime()) );
            if(zhMmSjModel.getMm()!=null&&!zhMmSjModel.getMm().isEmpty()&&System.currentTimeMillis()-zhMmSjModel.getTime()<604800000){
                //自动登陆
                HashMap<String, String> map = new HashMap<>();
                map.put("username",zhMmSjModel.getZh());
                map.put("secret",zhMmSjModel.getMm());
                UtilsOKHttp.getInstance().post(Constant.URL_mimalogin, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("pp", "onSuccess: "+result );
                        LoginModel codeMsgModel = new Gson().fromJson(result, LoginModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            zhMmSjModel.setTime(System.currentTimeMillis());
                            SharedPreferencesUtils.putString(SjhzcActivity.this,"phone",new Gson().toJson(zhMmSjModel));
                            Constant.myself=codeMsgModel;
                            Constant.TOKEN=codeMsgModel.getData().getToken();
                            ToastUtils.showToast(SjhzcActivity.this,"登陆成功！");
                            startActivity(new Intent(SjhzcActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onFail(String failResult) {
                        Log.e("pp", "failResult: "+failResult );
                    }
                });
            }else if(zhMmSjModel.getZh()!=null&&!zhMmSjModel.getZh().isEmpty()){
                mIdEt1.setText(zhMmSjModel.getZh());
            }
        }
    }

    private void initview() {
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        mIdCb = ((CheckBox) findViewById(R.id.id_cb));
        findViewById(R.id.id_xieyi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SjhzcActivity.this,XieyiActivity.class));
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
                UtilsOKHttp.getInstance().get(Constant.URL_MOBELE + mIdEt1.getText().toString() + "/type/1", new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if(result!=null){
                            CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                            if(codeMsgModel!=null){
                                if(codeMsgModel.getCode()==0){
                                    //发送成功
                                    ToastUtils.showToast(SjhzcActivity.this,"发送成功！");
                                    ZhMmSjModel zhMmSjModel = new ZhMmSjModel();
                                    zhMmSjModel.setZh(mIdEt1.getText().toString());
                                    zhMmSjModel.setTime(System.currentTimeMillis());
                                    SharedPreferencesUtils.putString(SjhzcActivity.this,"phone",new Gson().toJson(zhMmSjModel));
                                    Intent intent = new Intent(SjhzcActivity.this, YzmActivity.class);
                                    intent.putExtra("phone",mIdEt1.getText().toString());
                                    startActivity(intent);
                                }else {
                                    ToastUtils.showToast(SjhzcActivity.this,codeMsgModel.getErrorMessage());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFail(String failResult) {
                    }
                });
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
