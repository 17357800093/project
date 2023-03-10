package com.example.dwkyanglao.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.Main2Activity;
import com.example.dwkyanglao.activity.activity2.XzsbActivity;
import com.example.dwkyanglao.activity.activity4.SzmmActivity;
import com.example.dwkyanglao.activity.activity4.WsgrxxActivity;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.DeviceZuModel;
import com.example.dwkyanglao.activity.model.DevicesModel;
import com.example.dwkyanglao.activity.model.MqttMsgModel;
import com.example.dwkyanglao.activity.model.SmrbModel;
import com.example.dwkyanglao.activity.model.SyYjModel;
import com.example.dwkyanglao.event.RefreshDeviceName;
import com.example.dwkyanglao.event.RefreshShouye;
import com.example.dwkyanglao.event.RefreshYujin;
import com.example.dwkyanglao.event.Refreshshebei;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.manage.Popwindow;
import com.example.dwkyanglao.utils.HandlerAndSleep;
import com.example.dwkyanglao.utils.SharedPreferencesUtils;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.example.dwkyanglao.view.HorizontalListView;
import com.example.dwkyanglao.view.MyCircleProgress;

import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.client.android.CaptureActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.example.dwkyanglao.manage.Constant.mCheckDeviceUri;


/**
 * Created by Administrator on 2023-02-07.
 * ??????
 */

public class MainFragment extends BaseFragment {


    private HorizontalListView mIdLv;
    private List<DevicesModel.DataBean> mdata=new ArrayList<>();
    private View mIdLayout1,mIdLayout2,mIdLayout3;
    private MyCircleProgress mIdMcp;
    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//????????????????????????
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4,mIdTv5,mIdTvSm;
    private CommonAdapter<DevicesModel.DataBean> commonAdapter;
    private List<DeviceZuModel.DataBean> mDeviceData=new ArrayList<>();
    private TextView mIdTvName;


    private ScheduledExecutorService scheduler;
    private MqttClient client;
    private String host = "tcp://dev.bewatec.com.cn:1883";     // TCP??????
    private String userName = "app"; //mqtt????????????
    private String passWord = "DjCPqv991Uo9nhql1pcx";//mqtt????????????
    private String mqtt_id = "backend";//mqtt id
    private String mqtt_sub_topic = "iot";//mqtt????????????????????????
    private String mqtt_pub_topic = "iot/up/type/";//mqtt???????????????????????????
    private MqttConnectOptions options;
    private TextView mIdTvXl,mIdTvHxl,mIdTvTd;
    private String topicName;
    private String message;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            initYujin();
            if(client!=null){
                if(client.isConnected()){
                    try {
                        for (int i = 0; i < mdata.size(); i++) {
                            client.subscribe(mqtt_pub_topic+mdata.get(i).getDeviceType()+"/uri/"+mdata.get(i).getUri()+"/data");
                        }
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }else {
                    startReconnect(options);
                }
            }

        }
    };
    private RelativeLayout mIdLayoutTd;
    private int TdmeasuredWidth;
    private ImageView mIdImgTdXbd;
    private TextView mIdSmsc1,mIdSmsc2,mIdTvXiaolv;

    private class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initview(rootView);
        new Thread(new MyThread()).start();
        return rootView;
    }

    private void initview(View rootView) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mIdLayoutTd = ((RelativeLayout) rootView.findViewById(R.id.id_layout_td));
        mIdLayoutTd.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                mIdLayoutTd.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                TdmeasuredWidth = mIdLayoutTd.getMeasuredWidth();
            }
        });
        mIdImgTdXbd = ((ImageView) rootView.findViewById(R.id.id_img_xbd));
        mIdTvXl = ((TextView) rootView.findViewById(R.id.id_tv_xl));
        mIdTvHxl = ((TextView) rootView.findViewById(R.id.id_tv_hxl));
        mIdTvTd = ((TextView) rootView.findViewById(R.id.id_tv_td));
        mIdTvName = ((TextView) rootView.findViewById(R.id.id_tv_name));
        mIdLv = ((HorizontalListView) rootView.findViewById(R.id.id_listview));
        mIdLayout1 = rootView.findViewById(R.id.id_layout1);
        mIdLayout2 = rootView.findViewById(R.id.id_layout2);
        mIdLayout3 = rootView.findViewById(R.id.id_layout3);
        mIdMcp = ((MyCircleProgress) rootView.findViewById(R.id.id_mcp));
        mIdSpinner = ((Spinner) rootView.findViewById(R.id.id_spinner));

        mIdTv1 = ((TextView) rootView.findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) rootView.findViewById(R.id.id_tv2));
        mIdTv3 = ((TextView) rootView.findViewById(R.id.id_tv3));
        mIdTv4 = ((TextView) rootView.findViewById(R.id.id_tv4));
        mIdTv5 = ((TextView) rootView.findViewById(R.id.id_tv5));
        mIdTvSm = ((TextView) rootView.findViewById(R.id.id_tv_sm));

        mIdSmsc1 = ((TextView) rootView.findViewById(R.id.id_smsc1));
        mIdSmsc2 = ((TextView) rootView.findViewById(R.id.id_smsc2));
        mIdTvXiaolv = ((TextView) rootView.findViewById(R.id.id_tv_xiaolv));

        rootView.findViewById(R.id.id_img_saoma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScanPackageClick();
            }
        });
        rootView.findViewById(R.id.id_yjjl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RefreshYujin(1));
                EventBus.getDefault().post(new RefreshShouye(3));
            }
        });
        rootView.findViewById(R.id.id_layout_sm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                intent.putExtra("uri",mCheckDeviceUri);
                startActivity(intent);
            }
        });
        rootView.findViewById(R.id.id_img_tjsb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), XzsbActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
        initdevice();
        initYujin();
        mqttInit();

    }

    private void initdevice() {
//        mCheckDeviceUri="";
        mIdLayout1.setVisibility(View.INVISIBLE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.GONE);
        UtilsOKHttp.getInstance().get(Constant.URL_Devicelist, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                DeviceZuModel deviceZuModel = new Gson().fromJson(result, DeviceZuModel.class);
                if(deviceZuModel!=null&&deviceZuModel.getData()!=null){
                    refreshDevice(deviceZuModel);
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void startCamera() {
        //??????????????????????????????????????????
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("pp", "onActivityResult: "+ resultCode);
        if (resultCode == -1) {
            Bundle bundle = data.getExtras();
            //??????result???????????????????????????????????????key???result??????????????????????????????????????????????????????????????????????????????????????????????????????????????????url?????????????????????????????????????????????
            String result = bundle.getString("SCAN_RESULT");
            if(result!=null){
                HashMap<String, String> src = new HashMap<>();
                src.put("deviceId",result);
                UtilsOKHttp.getInstance().post(Constant.URL_sharedevice + result + "/shareUser", new Gson().toJson(src), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(getContext(),"???????????????");
                            EventBus.getDefault().post(new Refreshshebei(1));
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(getContext(),codeMsgModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {

                    }
                });
            }
        }

    }
    public void onScanPackageClick() {
        //??????????????????????????????????????????
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(MainActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
            startCamera();
        } else {
            //???????????????????????????
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //??????requestCode???100 ????????????
            case 100:
                //permissions[0].equals(Manifest.permission.CAMERA)
                //grantResults[0] == PackageManager.PERMISSION_GRANTED
                //?????????????????????????????????
                if (permissions[0].equals(Manifest.permission.CAMERA)) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //???????????????????????????????????????
                        startCamera();
                    } else {
                        //????????????????????????????????????????????? ????????????????????????????????????????????? ???????????????????????????????????????
                        //?????????????????????
                        Toast.makeText(getContext(), "?????????????????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(Constant.myself.getData().getDisplayName()==null){
            mIdTvName.setText(Constant.myself.getData().getUsername()+",?????????");
        }else {
            mIdTvName.setText(Constant.myself.getData().getDisplayName()+",?????????");
        }
    }
    private void refreshDevice(DeviceZuModel deviceZuModel) {
        mDeviceData.clear();
        if(deviceZuModel.getData().size()!=0){
            mDeviceData.addAll(deviceZuModel.getData());
            Sbdata.clear();
            for (int i = 0; i < mDeviceData.size(); i++) {
                Sbdata.add(mDeviceData.get(i).getName());
            }
            adapter.notifyDataSetChanged();
        }else {
            Sbdata.clear();
            Sbdata.add("????????????");
            adapter.notifyDataSetChanged();
        }
        mIdSpinner.setSelection(0);
        if(mDeviceData.size()!=0){
            UtilsOKHttp.getInstance().get(Constant.URL_Group + mDeviceData.get(0).getId() + "/deviceList", new UtilsOKHttp.OKCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.e("pp", "onSuccess: "+result );
                    DevicesModel device= new Gson().fromJson(result, DevicesModel.class);
                    mdata.clear();
                    if(device!=null&&device.getData()!=null){
                        mdata.addAll(device.getData());
                    }
                    for (int i = 0; i < mdata.size(); i++) {
                        if(mdata.get(i).getUri().equals(mCheckDeviceUri)){
                            mdata.get(i).setIscheck(true);
                            break;
                        }
                    }
                    commonAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(String failResult) {

                }
            });
        }else {
            mdata.clear();
            commonAdapter.notifyDataSetChanged();
        }
    }
    private void initYujin() {
        UtilsOKHttp.getInstance().get(Constant.URL_statisticsCount, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess??????: "+result );
                if(result!=null){
                    SyYjModel syYjModel = new Gson().fromJson(result, SyYjModel.class);
                    if(syYjModel!=null&&syYjModel.getData()!=null){
                        for (int i = 0; i < syYjModel.getData().size(); i++) {
                            SyYjModel.DataBean dataBean = syYjModel.getData().get(i);
                            switch (dataBean.getTagType()){
                                case 1:
                                    mIdTv1.setText(dataBean.getCount()+"");
                                    break;
                                case 2:
                                    mIdTv2.setText(dataBean.getCount()+"");
                                    break;
                                case 3:
                                    mIdTv3.setText(dataBean.getCount()+"");
                                    break;
                                case 4:
                                    mIdTv4.setText(dataBean.getCount()+"");
                                    break;
                                case 5:
                                    mIdTv5.setText(dataBean.getCount()+"");
                                    break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }
    public Runnable runnableUi= () -> {
        //????????????
        for (int i = 0; i < mdata.size(); i++) {
            DevicesModel.DataBean dataBean = mdata.get(i);
            if(topicName !=null && topicName.contains(dataBean.getUri())){
                MqttMsgModel mqttMsgModel = null;
                try {
                    mqttMsgModel = new Gson().fromJson(message, MqttMsgModel.class);
                }catch (JsonSyntaxException ex){

                }
                if(mqttMsgModel==null){
                    break;
                }
                if(dataBean.getState()!=mqttMsgModel.getState()){
                    dataBean.setState(mqttMsgModel.getState());
                    commonAdapter.notifyDataSetChanged();
                }
                if(!mCheckDeviceUri.isEmpty()&&mCheckDeviceUri.equals(dataBean.getUri())){
                    if(topicName.contains("10004")){
                        //?????????
                        if(mqttMsgModel!=null){
                            mIdTvXl.setText(mqttMsgModel.getHeart()+"");
                            mIdTvHxl.setText(mqttMsgModel.getBreath()+"");
                            String tidon="?????????";
                            RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) mIdImgTdXbd.getLayoutParams();

                            switch (mqttMsgModel.getMove()){
                                case 0:
                                    tidon="?????????";
                                    layoutParams.setMargins(0,0,0,0);
                                    break;
                                case 3:
                                    tidon="????????????";
                                    layoutParams.setMargins(TdmeasuredWidth/3,0,0,0);
                                    break;
                                case 4:
                                    tidon="????????????";
                                    layoutParams.setMargins(TdmeasuredWidth/3*2,0,0,0);
                                    break;
                                case 5:
                                    tidon="????????????";
                                    layoutParams.setMargins(TdmeasuredWidth-40,0,0,0);
                                    break;
                            }
                            mIdTvTd.setText(tidon);
                        }
                    }
                }
                break;
            }
        }

    };
    private void initdata() {
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.textlayout, Sbdata);
        adapter.setDropDownViewResource(R.layout.textlayout1);
        mIdSpinner.setAdapter(adapter);
        mIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("pp", "onItemSelected: "+ mDeviceData.size());
                if(mDeviceData.size()!=0){
                    mCheckDeviceUri="";
                    mIdLayout1.setVisibility(View.INVISIBLE);
                    mIdLayout2.setVisibility(View.GONE);
                    mIdLayout3.setVisibility(View.GONE);
                    UtilsOKHttp.getInstance().get(Constant.URL_Group + mDeviceData.get(position).getId() + "/deviceList", new UtilsOKHttp.OKCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("pp", "onSuccess: "+result );
                            DevicesModel device= new Gson().fromJson(result, DevicesModel.class);
                            mdata.clear();
                            if(device!=null&&device.getData()!=null){
                                mdata.addAll(device.getData());
                            }
                            commonAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFail(String failResult) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        commonAdapter = new CommonAdapter<DevicesModel.DataBean>(getActivity(), R.layout.sylvlayout, mdata) {
            @Override
            protected void convert(ViewHolder viewHolder, DevicesModel.DataBean item, int position) {
                ImageView img = (ImageView) viewHolder.getView(R.id.id_img);
                String name="";
                if(item.getDeviceType()==10004){
                    name="???????????????";
                }else if(item.getDeviceType()==10003){
                    name="???????????????";
                }else if(item.getDeviceType()==10005){
                    name="???????????????";
                }
                TextView mTvname = (TextView) viewHolder.getView(R.id.id_tv1);
                TextView mTvUri = (TextView) viewHolder.getView(R.id.id_tv2);
                if(item.getName()==null||item.getName().isEmpty()){
                    mTvUri.setVisibility(View.VISIBLE);
                    mTvname.setText(name);
                    mTvUri.setText(item.getUri());
                }else {
                    mTvname.setText(item.getName());
                    mTvUri.setVisibility(View.GONE);
                }

                if(item.getState()==4){
                    img.setBackgroundResource(R.drawable.xld);
                }else {
                    img.setBackgroundResource(R.drawable.xhd);
                }

                View layout = viewHolder.getView(R.id.id_layout);
                if(item.isIscheck()){
                    layout.setBackgroundResource(R.drawable.mylayout1);
                    mTvname.setTextColor(Color.WHITE);
                    mTvUri.setTextColor(Color.WHITE);
                    switch (mdata.get(position).getDeviceType()){
                        case 10004:
                            Click1();
                            String date = Utils.getDateTimeFromMillisecond2(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
                            String jsonmsgcache = SharedPreferencesUtils.getString(getContext(), mdata.get(position).getUri()  + date+"msg", "");
                            if(jsonmsgcache.isEmpty()){
                                UtilsOKHttp.getInstance().get(Constant.URL_SleepReport + mdata.get(position).getUri() + "/sleepdata?date=" + date + "&uri=" + mdata.get(position).getUri(), new UtilsOKHttp.OKCallback() {
                                    @Override
                                    public void onSuccess(String result) {
                                        SmrbModel smrbModel = new Gson().fromJson(result, SmrbModel.class);
                                        if(smrbModel!=null&&smrbModel.getData()!=null){
                                            mIdMcp.SetCurrent(smrbModel.getData().getScore());
                                            mIdTvXiaolv.setText(smrbModel.getData().getEfficiency()+"%");
                                            int sleepSpan = smrbModel.getData().getSleepSpan();
                                            if(sleepSpan<60){
                                                mIdSmsc1.setText("0");
                                            }else {
                                                mIdSmsc1.setText(sleepSpan/60+"");
                                            }
                                            mIdSmsc2.setText(sleepSpan%60+"");
                                            mIdTvSm.setText(smrbModel.getData().getScore()+"");
                                            SharedPreferencesUtils.putString(getContext(),mdata.get(position).getUri()  + date+"msg",result);
                                        }else {
                                            mIdMcp.SetCurrent(0);
                                            mIdTvXiaolv.setText("0%");
                                            mIdSmsc1.setText("0");
                                            mIdSmsc2.setText("0");
                                            mIdTvSm.setText("0");
                                        }
                                    }

                                    @Override
                                    public void onFail(String failResult) {

                                    }
                                });
                            }else {
                                SmrbModel smrbModel = new Gson().fromJson(jsonmsgcache, SmrbModel.class);
                                if(smrbModel!=null&&smrbModel.getData()!=null){
                                    mIdMcp.SetCurrent(smrbModel.getData().getScore());
                                    mIdTvXiaolv.setText(smrbModel.getData().getEfficiency()+"%");
                                    int sleepSpan = smrbModel.getData().getSleepSpan();
                                    if(sleepSpan<60){
                                        mIdSmsc1.setText("0");
                                    }else {
                                        mIdSmsc1.setText(sleepSpan/60+"");
                                    }
                                    mIdSmsc2.setText(sleepSpan%60+"");
                                    mIdTvSm.setText(smrbModel.getData().getScore()+"");
                                }else {
                                    mIdMcp.SetCurrent(0);
                                    mIdTvXiaolv.setText("0%");
                                    int sleepSpan = smrbModel.getData().getSleepSpan();
                                    mIdSmsc1.setText("0");
                                    mIdSmsc2.setText("0");
                                    mIdTvSm.setText("0");
                                }
                            }
                            break;
                        case 10005:
                            Click2();
                            break;
                        case 10003:
                            Click3();
                            break;
                    }

                }else {
                    layout.setBackgroundResource(R.drawable.mylayout);
                    mTvname.setTextColor(Color.BLACK);
                    mTvUri.setTextColor(Color.BLACK);
                }
            }
        };
        mIdLv.setAdapter(commonAdapter);
        mIdLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mdata.size(); i++) {
                    if(position==i){
                        mdata.get(i).setIscheck(true);
                    }else {
                        mdata.get(i).setIscheck(false);
                    }
                }
                commonAdapter.notifyDataSetChanged();
                mCheckDeviceUri=mdata.get(position).getUri();


            }
        });
    }

    // MQTT?????????
    public void mqttInit() {
        try {
            //host???????????????test???clientid?????????MQTT????????????ID?????????????????????????????????????????????MemoryPersistence??????clientid??????????????????????????????????????????
            client = new MqttClient(host, Constant.myself.getData().getId(),
                    new MemoryPersistence());
            //MQTT???????????????
            options = new MqttConnectOptions();
            //??????????????????session,?????????????????????false??????????????????????????????????????????????????????????????????true??????????????????????????????????????????????????????
            options.setCleanSession(false);
            //????????????????????????
//            options.setUserName(userName);
            options.setUserName(Constant.myself.getData().getId());
            //?????????????????????
            options.setPassword(Constant.myself.getData().getMqttToken().toCharArray());
//            options.setPassword(passWord.toCharArray());
            // ?????????????????? ????????????
            options.setConnectionTimeout(10);
            // ???????????????????????? ???????????? ??????????????????1.5*20????????????????????????????????????????????????????????????????????????????????????????????????????????????
            options.setKeepAliveInterval(20);

            //????????????
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    //????????????????????????????????????????????????
                    Log.e("pp", "connectionLost: ????????????");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish?????????????????????
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe???????????????????????????????????????
                    Log.e("pp", "messageArrived: "+ topicName +"  "+ message.toString());
                    if(message==null|| !message.toString().toString().contains("{") || !message.toString().toString().contains("}")){
                        return;
                    }
                    MainFragment.this.topicName = topicName;
                    MainFragment.this.message = message.toString();
                    handler.post(runnableUi);


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        startReconnect(options);

    }

    // MQTT??????????????????
    private void startReconnect(MqttConnectOptions options) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!client.isConnected()) {
                    mqttConnect(options);
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    // ????????????    (????????????/??????)
    public void publishmessageplus(String topic, String message2) {
        Log.e("pp", "publishmessageplus: "+topic );
        if (client == null || !client.isConnected()) {
            Log.e("pp", "publishmessageplus: !client.isConnected()");
            return;
        }
        MqttMessage message = new MqttMessage();
        message.setPayload(message2.getBytes());
        try {
            client.publish(topic, message);
            Log.e("pp", "publishmessageplus: ????????????" );
        } catch (MqttException e) {

            e.printStackTrace();
        }
    }

    // MQTT????????????
    private void mqttConnect(MqttConnectOptions options) {
        new Thread(() -> {
            try {
                if (!(client.isConnected()))  //??????????????????
                {
                    client.connect(options);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void Click1(){//??????
        mIdLayout1.setVisibility(View.VISIBLE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.GONE);
    }
    public void Click2(){//?????????
        mIdLayout1.setVisibility(View.INVISIBLE);
        mIdLayout2.setVisibility(View.VISIBLE);
        mIdLayout3.setVisibility(View.GONE);
    }
    public void Click3(){//??????
        mIdLayout1.setVisibility(View.INVISIBLE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true) //???ui????????????
    public void onEvent(Refreshshebei event) {
        if(event!=null) {
            initdevice();
        }
    }
}
