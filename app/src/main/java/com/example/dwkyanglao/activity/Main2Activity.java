package com.example.dwkyanglao.activity;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.utils.MPChartUtils;
import com.example.dwkyanglao.utils.Utils;
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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends Activity {

    private BarChart mCombinedChart;
    private PieChart mPcChart;
    private LineChart mCcChart;
    private LineChart mQxChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mCombinedChart = ((BarChart) findViewById(R.id.id_chart));
        mPcChart = ((PieChart) findViewById(R.id.id_pcchart));
        mCcChart = ((LineChart) findViewById(R.id.id_ccchart));
        mQxChart = ((LineChart) findViewById(R.id.id_qxchart));
        TextView mIdTv1 = (TextView) findViewById(R.id.id_tv1);

        initdata();
        initPcdata();
        initCcdata();
        initQxdata();
    }

    private void initQxdata() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            Entry barEntry = new Entry(i,Utils.getRandomNumXt1());
            entries.add(barEntry);
        }
        MPChartUtils.configChartQuxian(mQxChart,entries);
    }

    private void initCcdata() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Entry barEntry = new Entry(i,Utils.getRandomNumXt());
            entries.add(barEntry);
        }
        MPChartUtils.configChart(mCcChart,entries);
    }

    private void initPcdata() {
        MPChartUtils.initPcchart(mPcChart);
    }

    private void initdata() {
        List<String> lable=new ArrayList<>();
        for (int i = 1; i <= 500; i++) {
            lable.add(i +"");
        }
        MPChartUtils.configBarChart(mCombinedChart, lable);

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            BarEntry barEntry = new BarEntry(i,Utils.getRandomNum());
            entries.add(barEntry);
            entries.add(barEntry);
            entries.add(barEntry);
            entries.add(barEntry);
            entries.add(barEntry);
        }
        //  3,初始化数据并绘制
        MPChartUtils.initBarChart(mCombinedChart,entries,"",Color.parseColor("#ff0000"));
    }
}
