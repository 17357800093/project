package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.DeviceXQModel;
import com.example.dwkyanglao.activity.model.DevicesModel;
import com.example.dwkyanglao.event.Refreshshebei;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

public class SbxxActivity extends BaseActivity {

    private String id;
    private EditText mIdEt1;
    private TextView mIdTv1,mIdTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbxx);
        initview();
        initdata();
    }

    private void initdata() {
        id = getIntent().getStringExtra("id");
        UtilsOKHttp.getInstance().get(Constant.URL_DeviceInfo + "?deviceid=" + id, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                DeviceXQModel devicesModel = new Gson().fromJson(result, DeviceXQModel.class);
                if(devicesModel!=null&&devicesModel.getData()!=null){
                    mIdEt1.setText(devicesModel.getData().getName());
                    mIdTv1.setText(devicesModel.getData().getMac());
                    mIdTv2.setText(devicesModel.getData().getUri());
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void initview() {
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) findViewById(R.id.id_tv2));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mIdEt1.getText())){
                    ToastUtils.showToast(SbxxActivity.this,"设备别名不能为空");
                    return;
                }
                HashMap<String, String> src = new HashMap<>();
                src.put("name",mIdEt1.getText().toString());
                UtilsOKHttp.getInstance().put(Constant.URL_DeviceInfo + "?id=" + id,new Gson().toJson(src), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel devicesModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(devicesModel!=null&&devicesModel.getCode()==0){
                            ToastUtils.showToast(SbxxActivity.this,"修改成功");
                            EventBus.getDefault().post(new Refreshshebei(1));
                            finish();
                        }
                    }

                    @Override
                    public void onFail(String failResult) {

                    }
                });
            }
        });
    }
}
