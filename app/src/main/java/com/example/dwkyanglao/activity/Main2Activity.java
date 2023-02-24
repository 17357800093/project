package com.example.dwkyanglao.activity;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.activity1.DateCheckActivity;
import com.example.dwkyanglao.activity.model.SmfxModel;
import com.example.dwkyanglao.activity.model.SmrbModel;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.MPChartUtils;
import com.example.dwkyanglao.utils.SharedPreferencesUtils;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends Activity {

    private BarChart mCombinedChart;
    private PieChart mPcChart;
    private LineChart mCcChart;
    private LineChart mQxChart;
    private String uri;
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4,mIdTv5,mIdTv6,mIdTv7,mIdTv8,mIdTv9,mIdTv10,mIdTv11;
    private TextView mIdTvTime,mIdTvXs,mIdTvFen;
    private View mIdLeft,mIdRight;
    private long nowtime=System.currentTimeMillis() - 24 * 60 * 60 * 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();
        initdata( Utils.getDateTimeFromMillisecond2(nowtime));
        initdataMsg(Utils.getDateTimeFromMillisecond2(nowtime));
        mIdTvTime.setText(Utils.getDateTimeFromMillisecond2(nowtime)+"夜间睡眠");
    }

    private void initview() {
        mCombinedChart = ((BarChart) findViewById(R.id.id_chart));
        mPcChart = ((PieChart) findViewById(R.id.id_pcchart));
        mCcChart = ((LineChart) findViewById(R.id.id_ccchart));
        mQxChart = ((LineChart) findViewById(R.id.id_qxchart));
        uri = getIntent().getStringExtra("uri");

        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) findViewById(R.id.id_tv2));
        mIdTv3 = ((TextView) findViewById(R.id.id_tv3));
        mIdTv4 = ((TextView) findViewById(R.id.id_tv4));
        mIdTv5 = ((TextView) findViewById(R.id.id_tv5));
        mIdTv6 = ((TextView) findViewById(R.id.id_tv6));
        mIdTv7 = ((TextView) findViewById(R.id.id_tv7));
        mIdTv8 = ((TextView) findViewById(R.id.id_tv8));
        mIdTv9 = ((TextView) findViewById(R.id.id_tv9));
        mIdTv10 = ((TextView) findViewById(R.id.id_tv10));
        mIdTv11 = ((TextView) findViewById(R.id.id_tv11));

        mIdTvTime = ((TextView) findViewById(R.id.id_tv_time));
        mIdTvXs = ((TextView) findViewById(R.id.id_tv_xs));
        mIdTvFen = ((TextView) findViewById(R.id.id_tv_f));

        mIdLeft = findViewById(R.id.id_left);
        mIdLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIdRight.setVisibility(View.VISIBLE);
                nowtime=nowtime-24 * 60 * 60 * 1000;
                initdata( Utils.getDateTimeFromMillisecond2(nowtime));
                initdataMsg(Utils.getDateTimeFromMillisecond2(nowtime));
                mIdTvTime.setText(Utils.getDateTimeFromMillisecond2(nowtime)+"夜间睡眠");
            }
        });
        mIdRight= findViewById(R.id.id_right);
        mIdRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowtime=nowtime+24 * 60 * 60 * 1000;
                initdata( Utils.getDateTimeFromMillisecond2(nowtime));
                initdataMsg(Utils.getDateTimeFromMillisecond2(nowtime));
                mIdTvTime.setText(Utils.getDateTimeFromMillisecond2(nowtime)+"夜间睡眠");
                if(Utils.getDateTimeFromMillisecond2(nowtime).equals(Utils.getDateTimeFromMillisecond2(System.currentTimeMillis() - 24 * 60 * 60 * 1000))){
                    mIdRight.setVisibility(View.INVISIBLE);
                }
            }
        });
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, DateCheckActivity.class);
                intent.putExtra("uri",uri);
                startActivityForResult(intent,1);
            }
        });
    }

    private void initdataMsg(String date) {
        String jsonmsgcache = SharedPreferencesUtils.getString(Main2Activity.this, uri + date+"msg", "");
        if(jsonmsgcache.isEmpty()){
            UtilsOKHttp.getInstance().get(Constant.URL_SleepReport + uri + "/sleepdata?date=" + date + "&uri=" + uri, new UtilsOKHttp.OKCallback() {
                @Override
                public void onSuccess(String result) {
                    SmrbModel smrbModel = new Gson().fromJson(result, SmrbModel.class);
                    if(smrbModel!=null&&smrbModel.getData()!=null){
                        SmrbModel.DataBean dataBean = smrbModel.getData();
                        mIdTv1.setText(dataBean.getHeartAvg()+"");
                        mIdTv2.setText(dataBean.getBreathAvg()+"");
                        mIdTv3.setText(dataBean.getRollOver()+"");
                        mIdTv5.setText(dataBean.getEfficiency()+"%");
                        mIdTv6.setText(dataBean.getMaxHeart()+"");
                        mIdTv7.setText(dataBean.getMinHeart()+"");
                        mIdTv8.setText(dataBean.getHeartAvg()+"");
                        mIdTv9.setText(dataBean.getMaxBreath()+"");
                        mIdTv10.setText(dataBean.getMinBreath()+"");
                        mIdTv11.setText(dataBean.getBreathAvg()+"");

                        mIdTvFen.setText(dataBean.getSleepSpan()%60+"");
                        mIdTvXs.setText(dataBean.getSleepSpan()/60+"");
                        SharedPreferencesUtils.putString(Main2Activity.this,uri + date+"msg",result);
                    }
                }

                @Override
                public void onFail(String failResult) {

                }
            });
        }else {
            SmrbModel smrbModel = new Gson().fromJson(jsonmsgcache, SmrbModel.class);
            if(smrbModel!=null&&smrbModel.getData()!=null){
                SmrbModel.DataBean dataBean = smrbModel.getData();
                mIdTv1.setText(dataBean.getHeartAvg()+"");
                mIdTv2.setText(dataBean.getBreathAvg()+"");
                mIdTv3.setText(dataBean.getRollOver()+"");
                mIdTv5.setText(dataBean.getEfficiency()+"%");
                mIdTv6.setText(dataBean.getMaxHeart()+"");
                mIdTv7.setText(dataBean.getMinHeart()+"");
                mIdTv8.setText(dataBean.getHeartAvg()+"");
                mIdTv9.setText(dataBean.getMaxBreath()+"");
                mIdTv10.setText(dataBean.getMinBreath()+"");
                mIdTv11.setText(dataBean.getBreathAvg()+"");

                mIdTvFen.setText(dataBean.getSleepSpan()%60+"");
                mIdTvXs.setText(dataBean.getSleepSpan()/60+"");
            }
        }
    }

    private void initdata(String date) {
        String jsoncache = SharedPreferencesUtils.getString(Main2Activity.this, uri + date, "");
        if(jsoncache.isEmpty()){
            UtilsOKHttp.getInstance().get(Constant.URL_SleepReport + uri + "/report?date=" + date + "&uri=" + uri, new UtilsOKHttp.OKCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.e("pp", "onSuccess:睡眠分析 "+result );
                    SmfxModel smfxModel = new Gson().fromJson(result, SmfxModel.class);
                    if(smfxModel!=null&&smfxModel.getData()!=null){
                        List<Integer> stage = smfxModel.getData().getStage();
                        initchar1(stage);
                        List<Integer> heart = smfxModel.getData().getHeart();
                        initchar2(heart);
                        List<Integer> breath = smfxModel.getData().getBreath();
                        initchar3(breath);
                        SharedPreferencesUtils.putString(Main2Activity.this,uri + date,result);
                    }else {
                        Chishihua();
                    }
                }

                @Override
                public void onFail(String failResult) {

                }
            });
        }else {
            SmfxModel smfxModel = new Gson().fromJson(jsoncache, SmfxModel.class);
            if(smfxModel!=null&&smfxModel.getData()!=null){
                List<Integer> stage = smfxModel.getData().getStage();
                initchar1(stage);
                List<Integer> heart = smfxModel.getData().getHeart();
                initchar2(heart);
                List<Integer> breath = smfxModel.getData().getBreath();
                initchar3(breath);
            }else {
                Chishihua();
            }
        }


    }

    private void Chishihua() {
        mIdTvXs.setText("0");
        mIdTvFen.setText("0");
        mIdTv1.setText("0");
        mIdTv2.setText("0");
        mIdTv3.setText("0");
        mIdTv4.setText("0");
        mIdTv5.setText("0");
        mIdTv6.setText("0");
        mIdTv7.setText("0");
        mIdTv8.setText("0");
        mIdTv9.setText("0");
        mIdTv10.setText("0");
        mIdTv11.setText("0");
        List<Integer> stage = new ArrayList<>();
        initchar1(stage);
        List<Integer> heart = new ArrayList<>();
        initchar2(heart);
        List<Integer> breath = new ArrayList<>();
        initchar3(breath);
    }

    private void initchar3(List<Integer> breath) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < breath.size(); i++) {
            Entry barEntry = new Entry(i,breath.get(i));
            entries.add(barEntry);
        }
        MPChartUtils.configChartQuxian(mQxChart,entries);
    }

    private void initchar2(List<Integer> heart) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < heart.size(); i++) {
            Entry barEntry = new Entry(i,heart.get(i));
            entries.add(barEntry);
        }
        MPChartUtils.configChart(mCcChart,entries);
    }

    private void initchar1(List<Integer> stage) {
        int time1=0;
        int time2=0;
        int time3=0;
        int time4=0;
        List<String> lable=new ArrayList<>();
        for (int i = 0; i < stage.size(); i++) {
            lable.add(i +"");
        }
        MPChartUtils.configBarChart(mCombinedChart, lable);

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < stage.size(); i++) {
            int Yzhou=0;
            switch (stage.get(i)){
                case 0://醒着
                    Yzhou=55;
                    time2++;
                    break;
                case 1://潜睡
                    Yzhou=40;
                    time3++;
                    break;
                case 2://REM
                    Yzhou=60;
                    time1++;
                    break;
                case 3://深睡
                    Yzhou=80;
                    time4++;
                    break;
            }
            BarEntry barEntry = new BarEntry(i, Yzhou);
            entries.add(barEntry);
        }
        //  3,初始化数据并绘制
        MPChartUtils.initBarChart(mCombinedChart,entries,"",Color.parseColor("#ff0000"));

        List<PieEntry> entries1=new ArrayList<>();
        entries1.add(new PieEntry(time1, "REM  "+Utils.getDateTime(time1)));
        entries1.add(new PieEntry(time2, "清醒  "+Utils.getDateTime(time2)));
        entries1.add(new PieEntry(time3, "浅睡  "+Utils.getDateTime(time3)));
        entries1.add(new PieEntry(time4, "深睡  "+Utils.getDateTime(time4)));
        MPChartUtils.initPcchart(mPcChart,entries1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){//判断返回结果是否成功
                    String date=data.getStringExtra("date");
                    nowtime=Utils.switchCreateTime2(date);
                    initdata( Utils.getDateTimeFromMillisecond2(nowtime));
                    initdataMsg(Utils.getDateTimeFromMillisecond2(nowtime));
                    mIdTvTime.setText(Utils.getDateTimeFromMillisecond2(nowtime)+"夜间睡眠");
                    if(Utils.getDateTimeFromMillisecond2(nowtime).equals(Utils.getDateTimeFromMillisecond2(System.currentTimeMillis() - 24 * 60 * 60 * 1000))){
                        mIdRight.setVisibility(View.INVISIBLE);
                    }else if(Utils.getDateTimeFromMillisecond2(nowtime).equals(Utils.getDateTimeFromMillisecond2(System.currentTimeMillis()))){
                        mIdRight.setVisibility(View.INVISIBLE);
                    }else {
                        mIdRight.setVisibility(View.VISIBLE);

                    }
                }
                break;
            default:

        }
    }
}
