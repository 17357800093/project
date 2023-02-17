package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.comm.Observer;
import com.example.dwkyanglao.activity.comm.ObserverManager;
import com.example.dwkyanglao.activity.model.Cs;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.utils.DigitalTrans;
import com.google.gson.Gson;

public class LanyapeizhiActivity extends BaseActivity  implements Observer{
    private static BleDevice bleDevice;

    private static String serviceuuid="0000A55A-0000-1000-8000-00805f9b34fb";
    private static String uuid_write="0000ff01-0000-1000-8000-00805f9b34fb";
    private static String uuid_notify="0000ff01-0000-1000-8000-00805f9b34fb";

    private EditText mIdEt1;
    private EditText mIdEt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanyapeizhi);
        bleDevice = getIntent().getParcelableExtra("device");
        if (bleDevice == null){
            finish();

        }
        ObserverManager.getInstance().addObserver(this);
        initview();
    }

    private void initview() {
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mIdEt1 = ((EditText) findViewById(R.id.id_et1));
        mIdEt2 = ((EditText) findViewById(R.id.id_et2));
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mIdEt1.getText())&&!TextUtils.isEmpty(mIdEt2.getText())) {
                    Cs cs = new Cs();
                    cs.setSERVER_POST("1883");
                    cs.setTIMEZONE("Shanghai");
                    cs.setBSSID(bleDevice.getMac());
                    cs.setSSID(mIdEt1.getText().toString());
//                    cs.setLOCATION("105230201001");
                    cs.setPASSWORD(mIdEt2.getText().toString());
                    cs.setSERVER_IP("dev.bewatec.com.cn");
                    String json = new Gson().toJson(cs);
                    Log.e("pp", "onClick: 发送的数据"+json );

                    String parseAscii = DigitalTrans.parseAscii(json);

                    BleManager.getInstance().write(
                            bleDevice,
                            serviceuuid,
                            uuid_write,
                            DigitalTrans.hex2byte(parseAscii),
                            new BleWriteCallback() {
                                @Override
                                public void onWriteSuccess(final int current, final int total, final byte[] justWrite) {
                                    Log.e("pp", "onWriteSuccess: 发送成功" );
                                }

                                @Override
                                public void onWriteFailure(final BleException exception) {
                                    Log.e("pp", "onWriteFailure: 发送失败" );
                                }
                            });
                } else {
                    Toast.makeText(LanyapeizhiActivity.this, "参数不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });


        BleManager.getInstance().notify(
                bleDevice,
                serviceuuid,
                uuid_write,
                new BleNotifyCallback() {

                    @Override
                    public void onNotifySuccess() {
                        Log.e("pp", "onNotifySuccess: " );
                    }

                    @Override
                    public void onNotifyFailure(final BleException exception) {
                    }

                    @Override
                    public void onCharacteristicChanged(byte[] data) {
                        if (data != null) {
                            String s = DigitalTrans.byte2hex(data);
                            if(!s.isEmpty()&&s.equals("7B227769666920737461747573223A22636F6E6E6563746564227D0D0A")){
                                Toast.makeText(LanyapeizhiActivity.this,"修改成功",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });
    }

    @Override
    public void disConnected(BleDevice bleDevice) {
        if (bleDevice != null && bleDevice != null && bleDevice.getKey().equals(bleDevice.getKey())) {
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BleManager.getInstance().clearCharacterCallback(bleDevice);
        ObserverManager.getInstance().deleteObserver(this);
    }

}
