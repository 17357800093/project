package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.DeviceXQModel;
import com.example.dwkyanglao.event.Refreshshebei;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

public class ShebeiActivity extends BaseActivity {
    //mDeviceType 养老10003 体征10004 褥疮10005
    private String mDeviceId;
    private String mDeviceUri;
    private int mDeviceType;
    private ImageView mIdImg;
    private View mIdLayout3;
    private View mIdLayout5;
    private TextView mIdTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shebei);
        initview();
        initdata();
    }

    private void initdata() {
        mDeviceId = getIntent().getStringExtra("id");
        mDeviceUri = getIntent().getStringExtra("uri");
        mDeviceType = getIntent().getIntExtra("type", 0);

        switch (mDeviceType){
            case 10004:
                mIdImg.setImageResource(R.mipmap.tzd);
                break;
            case 10003:
                mIdImg.setImageResource(R.mipmap.ylc);
                mIdLayout3.setVisibility(View.GONE);
                break;
            case 10005:
                mIdImg.setImageResource(R.mipmap.frcd);
                mIdLayout3.setVisibility(View.GONE);
                mIdLayout5.setVisibility(View.GONE);
                break;
        }

        UtilsOKHttp.getInstance().get(Constant.URL_DeviceInfo + "?deviceid=" + mDeviceId, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                DeviceXQModel devicesModel = new Gson().fromJson(result, DeviceXQModel.class);
                if(devicesModel!=null&&devicesModel.getData()!=null){
                    if(devicesModel.getData().getConnected()==0){
                        mIdTv1.setText("断线");
                        mIdTv1.setBackgroundResource(R.drawable.huise);
                    }
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void initview() {
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdImg = ((ImageView) findViewById(R.id.img));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ShebeiActivity.this)
                        .setMessage("确定删除设备？")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("guid",mDeviceId);
                                UtilsOKHttp.getInstance().delete(Constant.URL_DeviceInfo+"?id="+mDeviceId, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                                    @Override
                                    public void onSuccess(String result) {
                                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                                            ToastUtils.showToast(ShebeiActivity.this,"删除设备成功！");
                                            EventBus.getDefault().post(new Refreshshebei(1));
                                            finish();
                                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                                            ToastUtils.showToast(ShebeiActivity.this,codeMsgModel.getErrorMessage());
                                        }
                                    }

                                    @Override
                                    public void onFail(String failResult) {
                                        Log.e("pp", "failResult: "+failResult );
                                    }
                                });
                            }
                        }).create().show();

            }
        });
        findViewById(R.id.id_layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShebeiActivity.this,SyznActivity.class));
            }
        });
        findViewById(R.id.id_layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShebeiActivity.this, SbxxActivity.class);
                intent.putExtra("id",mDeviceId);
                startActivity(intent);
            }
        });
        mIdLayout3 = findViewById(R.id.id_layout3);
        mIdLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShebeiActivity.this,SbjzActivity.class));
            }
        });
        findViewById(R.id.id_layout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShebeiActivity.this, GjsjActivity.class);
                intent.putExtra("type",mDeviceType);
                intent.putExtra("uri",mDeviceUri);
                startActivity(intent);
            }
        });
        mIdLayout5 = findViewById(R.id.id_layout5);
        mIdLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDeviceType==10004){
                    Intent intent = new Intent(ShebeiActivity.this, YjinTzdActivity.class);
                    intent.putExtra("uri",mDeviceUri);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ShebeiActivity.this, YjinDdcActivity.class);
                    intent.putExtra("uri",mDeviceUri);
                    startActivity(intent);
                }
            }
        });
        findViewById(R.id.id_layout6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShebeiActivity.this, GongxiangshezhiActivity.class);
                intent.putExtra("id",mDeviceId);
                startActivity(intent);
            }
        });
        findViewById(R.id.id_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShebeiActivity.this, GonxiangActivity.class);
                intent.putExtra("id",mDeviceId);
                startActivity(intent);
            }
        });
    }
}
