package com.example.dwkyanglao.utils;


import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.ColorInt;

import com.example.dwkyanglao.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MPChartUtils {
    /**
     * 不显示无数据的提示
     *
     * @param mChart
     */
    public static void NotShowNoDataText(Chart mChart) {
        mChart.clear();
        mChart.notifyDataSetChanged();
        mChart.setNoDataText("你还没有记录数据");
        mChart.setNoDataTextColor(Color.WHITE);
        mChart.invalidate();
    }
    /**
     *  配置Chart 基础设置
     * @param mChart 图表
     */
    public static void configChart(LineChart mChart, List<Entry> entries) {
        // no description text
        // mChart.getDescription().setEnabled(false);
        //设置是否绘制chart边框的线
        mChart.setDrawBorders(false);
        //设置chart边框线颜色
        mChart.setBorderColor(Color.RED);
        //设置chart边框线宽度
        mChart.setBorderWidth(1f);
        //设置chart是否可以触摸
        mChart.setTouchEnabled(false);
        mChart.setDragDecelerationFrictionCoef(0.9f);

        //设置是否可以拖拽
        mChart.setDragEnabled(true);
        //设置是否可以缩放 x和y，默认true
        mChart.setScaleEnabled(true);
        //设置是否可以通过双击屏幕放大图表。默认是true
        mChart.setDoubleTapToZoomEnabled(false);
        //是否启用网格背景
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        //设置chart动画 x轴y轴都有动画
        //mChart.animateXY(2000, 2000);
        // false代表缩放时X与Y轴分开缩放,true代表同时缩放
        mChart.setPinchZoom(true);
        // set an alternative background color
        //图表背景颜色的设置
//        mChart.setBackgroundColor(Color.LTGRAY);
//        //图表进入的动画时间
//        mChart.animateX(2500);

        //描述信息
        Description description = new Description();
        description.setText("");
        //设置描述信息
        mChart.setDescription(description);
        //设置没有数据时显示的文本
        mChart.setNoDataText("没有数据撒...");


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(8f);
        //X轴显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        //X轴
        xAxis.setSpaceMin(0.5f);
        //X轴数据的颜色
        xAxis.setTextColor(Color.BLUE);
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);
        //图表第一个和最后一个label数据不超出左边和右边的Y轴
        // xAxis.setAvoidFirstLastClipping(true);
//左侧Y轴属性设置
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        //TODO 什么属性?
        leftAxis.setGranularityEnabled(false);
        //左边Y轴添加限制线
        //右侧Y轴坐标
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setAxisMaximum(200);
        rightAxis.setAxisMinimum(30);
        rightAxis.setDrawGridLines(false);
        //是否绘制等0线
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setDrawAxisLine(false);
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(entries, "");
        set1.setDrawCircles(false);
        set1.setCubicIntensity(1.0f);
        //数据对应的是左边还是右边的Y值
        set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
        //线条的颜色
        set1.setColor(Color.RED);
        set1.setDrawCircles(false);
        //设置线面部分是否填充
        set1.setDrawFilled(true);
        //填充的颜色透明度
        //填充颜色
        set1.setFillColor(Color.parseColor("#ffe7ec"));
//        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        LineData data = new LineData(set1);
        //设置图标中显示数字的颜色
        data.setValueTextColor(Color.RED);
        //设置图标中显示数字的大小
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        mChart.setData(data);
        mChart.invalidate();
    }
    public static void configChartQuxian(LineChart mChart, List<Entry> entries ) {
        // no description text
        // mChart.getDescription().setEnabled(false);
        //设置是否绘制chart边框的线
        mChart.setDrawBorders(false);
        //设置chart边框线颜色
        mChart.setBorderColor(Color.RED);
        //设置chart边框线宽度
        mChart.setBorderWidth(1f);
        //设置chart是否可以触摸
        mChart.setTouchEnabled(false);
        mChart.setDragDecelerationFrictionCoef(0.9f);

        //设置是否可以拖拽
        mChart.setDragEnabled(true);
        //设置是否可以缩放 x和y，默认true
        mChart.setScaleEnabled(true);
        //设置是否可以通过双击屏幕放大图表。默认是true
        mChart.setDoubleTapToZoomEnabled(false);
        //是否启用网格背景
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        //设置chart动画 x轴y轴都有动画
        //mChart.animateXY(2000, 2000);
        // false代表缩放时X与Y轴分开缩放,true代表同时缩放
        mChart.setPinchZoom(true);
        // set an alternative background color
        //图表背景颜色的设置
//        mChart.setBackgroundColor(Color.LTGRAY);
//        //图表进入的动画时间
//        mChart.animateX(2500);

        //描述信息
        Description description = new Description();
        description.setText("");
        //设置描述信息
        mChart.setDescription(description);
        //设置没有数据时显示的文本
        mChart.setNoDataText("没有数据撒...");


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(8f);
        //X轴显示的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        //X轴
        xAxis.setSpaceMin(0.5f);
        //X轴数据的颜色
        xAxis.setTextColor(Color.BLUE);
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);
        //图表第一个和最后一个label数据不超出左边和右边的Y轴
        // xAxis.setAvoidFirstLastClipping(true);
//左侧Y轴属性设置
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        //TODO 什么属性?
        leftAxis.setGranularityEnabled(true);
        //左边Y轴添加限制线
        //右侧Y轴坐标
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setAxisMaximum(80);
        rightAxis.setAxisMinimum(0);
        rightAxis.setDrawGridLines(false);
        //是否绘制等0线
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
        rightAxis.setDrawAxisLine(false);
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(entries, "");
        set1.setDrawCircles(false);
        set1.setCubicIntensity(1.0f);
        //数据对应的是左边还是右边的Y值
        set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
        //线条的颜色
        set1.setColor(Color.GREEN);
        set1.setDrawCircles(false);
        //设置线面部分是否填充
        set1.setDrawFilled(true);
        //填充的颜色透明度
        //填充颜色
        set1.setFillColor(ColorTemplate.rgb("e3f7e8"));
        set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        LineData data = new LineData(set1);
        //设置图标中显示数字的颜色
        data.setValueTextColor(Color.RED);
        //设置图标中显示数字的大小
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        // set data
        mChart.setData(data);
        mChart.invalidate();
    }
    /**
     * 配置柱状图基础设置
     * @param barChart
     * @param xLabels
     */
    public static void configBarChart(BarChart barChart, final List<String> xLabels) {
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(false);//设置按比例放缩柱状图
        barChart.setScaleEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setNoDataText(""); // 没有数据时的提示文案
//        //x坐标轴设置
//        // IAxisValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);//设置自定义的x轴值格式化器
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setDrawLabels(false);
//        // 显示x轴标签
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Log.e("pp", "getFormattedValue: "+value );
                return super.getFormattedValue(value);
            }
        });
        xAxis.setTextSize(10);//设置标签字体大小
        xAxis.setTextColor(barChart.getResources().getColor(R.color.rad));
        xAxis.setAxisLineColor(Color.parseColor("#00000000"));
        xAxis.setLabelCount(xLabels.size());//设置标签显示的个数
//
//        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();//获取左侧y轴
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//设置y轴标签显示在外侧
        leftAxis.setAxisMinimum(0f);//设置Y轴最小值
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);//禁止绘制y轴标签
        leftAxis.setDrawAxisLine(false);//禁止绘制y轴
        leftAxis.setAxisLineColor(Color.parseColor("#4cffffff"));
////        leftAxis.setTextColor(barChart.getResources().getColor(R.color.char_text_color));
////        leftAxis.setValueFormatter(new IAxisValueFormatter() {
////            @Override
////            public String getFormattedValue(float value, AxisBase axis) {
////                return ((int) (value * 100)) + "%";
////            }
////        });
//
        barChart.getAxisRight().setEnabled(false);//禁用右侧y轴
        barChart.getLegend().setEnabled(false);
//
//
//        Matrix matrix = new Matrix();
//        // 根据数据量来确定 x轴缩放大倍
//        if (xLabels.size() <= 10) {
//            matrix.postScale(1.0f, 1.0f);
//        } else if (xLabels.size() <= 15) {
//            matrix.postScale(1.5f, 1.0f);
//        } else if (xLabels.size() <= 20) {
//            matrix.postScale(2.0f, 1.0f);
//        } else {
//            matrix.postScale(3.0f,1.0f);
//        }
//        barChart.getViewPortHandler().refresh(matrix, barChart, true);
//        barChart.setExtraBottomOffset(10);//距视图窗口底部的偏移，类似与paddingbottom
//        barChart.setExtraTopOffset(30);//距视图窗口顶部的偏移，类似与paddingtop
//        barChart.setFitBars(true);//使两侧的柱图完全显示
//        barChart.animateX(1500);//数据显示动画，从左往右依次显示
    }

    /**
     * 初始化柱状图图表数据
     * @param chart
     * @param entries
     * @param title
     * @param barColor
     */
    public static void initBarChart(BarChart chart, List<BarEntry> entries, String title, @ColorInt int barColor) {
        BarDataSet set1 = new BarDataSet(entries, "");
        set1.setValueTextColor(Color.WHITE);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < entries.size(); i++) {
            if(entries.get(i).getY()==20){
                list.add(Color.rgb(237, 201, 245));//设置颜色
            }else if (entries.get(i).getY()==40){
                list.add(Color.rgb(197, 75, 227));
            }else if (entries.get(i).getY()==60){
                list.add(Color.rgb(130, 63, 240));
            }else {
                list.add(Color.rgb(252, 128, 131));
            }
        }
        set1.setColors(list);

//        set1.setColor(barColor);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        // 设置bar的宽度，但是点很多少的时候好像没作用，会拉得很宽
        data.setBarWidth(1f);
        // 设置value值 颜色
        data.setValueTextColor(Color.WHITE);
        //设置y轴显示的标签
//        data.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return ((int) (value * 100)) + "%";
//            }
//        });
//



        chart.setData(data);
        chart.invalidate();
    }

    public static void initPcchart(PieChart mChart){
        List<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(10, "REM  1小时10分钟"));
        entries.add(new PieEntry(12, "清醒  10分钟"));
        entries.add(new PieEntry(17, "浅睡  1小时40分钟"));
        entries.add(new PieEntry(20, "深睡  1小时10分钟"));

        mChart.setUsePercentValues(true); //设置是否显示数据实体(百分比，true:以下属性才有意义)
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 5, 5, 5);//饼形图上下左右边距

        mChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]

        //mChart.setCenterTextTypeface(mTfLight);//设置所有DataSet内数据实体（百分比）的文本字体样式
        mChart.setCenterText("睡眠比例");//设置PieChart内部圆文字的内容
//        mChart.setDrawHoleEnabled(true);//是否显示PieChart内部圆环(true:下面属性才有意义)
//        mChart.setHoleColor(Color.WHITE);//设置PieChart内部圆的颜色

//        mChart.setTransparentCircleColor(Color.WHITE);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
//        mChart.setTransparentCircleAlpha(110);//设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        mChart.setHoleRadius(33f);//设置PieChart内部圆的半径(这里设置28.0f)
        mChart.setTransparentCircleRadius(37f);//设置PieChart内部透明圆的半径(这里设置31.0f)

        mChart.setDrawCenterText(true);//是否绘制PieChart内部中心文本（true：下面属性才有意义）

        mChart.setRotationAngle(0);//设置pieChart图表起始角度
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);//设置pieChart图表是否可以手动旋转
        mChart.setHighlightPerTapEnabled(true);//设置piecahrt图表点击Item高亮是否可用

        mChart.animateY(1400, Easing.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        // 获取pieCahrt图列
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f); //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(10f); //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);//设置比例块Y轴偏移量

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);//设置pieChart图表文本字体颜色
//        mChart.setEntryLabelTypeface(mTfRegular);//设置pieChart图表文本字体样式
        mChart.setEntryLabelTextSize(6f);//设置pieChart图表文本字体大小

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        List<Integer> list = new ArrayList<Integer>();
                list.add(Color.rgb(237, 201, 245));//设置颜色
                list.add(Color.rgb(197, 75, 227));
                list.add(Color.rgb(130, 63, 240));
                list.add(Color.rgb(252, 128, 131));

        dataSet.setColors(list);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();

    }

}
