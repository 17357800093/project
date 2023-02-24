package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.activity.comm.Observer;
import com.example.dwkyanglao.activity.comm.ObserverManager;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.Cs;
import com.example.dwkyanglao.event.RefreshShouye;
import com.example.dwkyanglao.event.Refreshshebei;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.manage.Popwindow;
import com.example.dwkyanglao.utils.DigitalTrans;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import static com.example.dwkyanglao.manage.Constant.mCheckDeviceUri;
import static com.example.dwkyanglao.utils.Utils.getScreenHeight;
import static com.example.dwkyanglao.utils.Utils.getScreenWidth;

public class LanyapeizhiActivity extends BaseActivity  implements Observer{
    private static BleDevice bleDevice;

    private static String serviceuuid="0000A55A-0000-1000-8000-00805f9b34fb";
    private static String uuid_write="0000ff01-0000-1000-8000-00805f9b34fb";
    private static String uuid_notify="0000ff01-0000-1000-8000-00805f9b34fb";

    private EditText mIdEt1;
    private EditText mIdEt2;
    private View id_layout_game_main;
    private PopupWindow popupWindow;

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
        id_layout_game_main = findViewById(R.id.id_layout_game_main);
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

        popupWindow = Popwindow.Pop_game_cj(LanyapeizhiActivity.this);
        popupWindow.setWidth(getScreenWidth(LanyapeizhiActivity.this) * 6 / 8);
        popupWindow.getContentView().findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                EventBus.getDefault().post(new Refreshshebei(1));
                Intent intent = new Intent(LanyapeizhiActivity.this, MainActivity.class);
                startActivity(intent);
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
//                                Toast.makeText(LanyapeizhiActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                                Log.e("pp", "onSuccess: 配置" );
                                ceshi(Constant.Devicetype);
                            }
                        }
                    }
                });
    }

    private void ceshi(int type){
        HashMap<String, Object> map = new HashMap<>();
        map.put("uri", bleDevice.getName().substring(2));
        map.put("deviceType",type);
        UtilsOKHttp.getInstance().post(Constant.URL_DeviceInfo, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                    Log.e("pp", "onSuccess: 添加设备接口成功" );
                    mCheckDeviceUri=bleDevice.getName().substring(2);
                    if(!popupWindow.isShowing()){
                        popupWindow.showAtLocation(id_layout_game_main, Gravity.CENTER, 0, 0);
                    }
                }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                    ToastUtils.showToast(LanyapeizhiActivity.this,codeMsgModel.getErrorMessage());
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    @Override
    public void disConnected(BleDevice bleDevice) {
        if (bleDevice != null && bleDevice != null && bleDevice.getKey().equals(bleDevice.getKey())) {
            EventBus.getDefault().post(new Refreshshebei(1));
            if(popupWindow!=null&&popupWindow.isShowing()){
                popupWindow.dismiss();
            }
            startActivity(new Intent(LanyapeizhiActivity.this,MainActivity.class));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(popupWindow!=null&&popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        BleManager.getInstance().clearCharacterCallback(bleDevice);
        ObserverManager.getInstance().deleteObserver(this);
    }

}
