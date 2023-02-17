package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class XzsbActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzsb);
        initview();
    }

    private void initview() {
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ceshi(10004);
//                Intent intent = new Intent(XzsbActivity.this, Xzsb2Activity.class);
//                intent.putExtra("type",1);
//                startActivity(intent);


            }
        });
        findViewById(R.id.id_layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ceshi(10005);
//                Intent intent = new Intent(XzsbActivity.this, Xzsb2Activity.class);
//                intent.putExtra("type",2);
//                startActivity(intent);
            }
        });
        findViewById(R.id.id_layout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ceshi(10003);
//                Intent intent = new Intent(XzsbActivity.this, Xzsb2Activity.class);
//                intent.putExtra("type",3);
//                startActivity(intent);
            }
        });
    }

    private void ceshi(int type){
        HashMap<String, Object> map = new HashMap<>();
                map.put("uri", Utils.getRandomNumXt());
                map.put("deviceType",type);
                UtilsOKHttp.getInstance().post(Constant.URL_DeviceInfo, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(XzsbActivity.this,"添加成功！");
                            finish();
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(XzsbActivity.this,codeMsgModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {

                    }
                });
    }

}
