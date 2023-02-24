package com.example.dwkyanglao.activity.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.SleepDateModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.google.gson.Gson;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.HashMap;
import java.util.Map;


public class DateCheckActivity extends BaseActivity {

    private String uri;
    private CalendarView mCalendarView;


    private TextView mTextMonthDay;

    private TextView mTextYear;

    private TextView mTextLunar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_check);
        uri = getIntent().getStringExtra("uri");
        initview();
        initdata();
    }

    private void initdata() {
        UtilsOKHttp.getInstance().get(Constant.URL_SleepReport + uri + "/date?uri=" + uri, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess: "+result );
                SleepDateModel sleepDateModel = new Gson().fromJson(result, SleepDateModel.class);
                if(sleepDateModel!=null&&sleepDateModel.getData()!=null){
                    Map<String, Calendar> map = new HashMap<>();
                    for (int i = 0; i < sleepDateModel.getData().size(); i++) {
                        map.put(getSchemeCalendar(Integer.parseInt(sleepDateModel.getData().get(i).substring(6)), Integer.parseInt(sleepDateModel.getData().get(i).substring(0,2)), Integer.parseInt(sleepDateModel.getData().get(i).substring(3,5)), 0xFF5d80f5, "").toString(),
                                getSchemeCalendar(Integer.parseInt(sleepDateModel.getData().get(i).substring(6)), Integer.parseInt(sleepDateModel.getData().get(i).substring(0,2)),  Integer.parseInt(sleepDateModel.getData().get(i).substring(3,5)), 0xFF5d80f5, ""));
                    }
                    mCalendarView.setSchemeDate(map);
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });

    }
    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }
    private void initview() {
        mTextMonthDay = findViewById(R.id.tv_month_day);
        mTextYear = findViewById(R.id.tv_year);
        mTextLunar = findViewById(R.id.tv_lunar);
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                mTextLunar.setVisibility(View.VISIBLE);
                mTextYear.setVisibility(View.VISIBLE);
                mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
                mTextYear.setText(String.valueOf(calendar.getYear()));
                mTextLunar.setText(calendar.getLunar());
                if(isClick){
                    Intent intent = new Intent();
                    intent.putExtra("date",calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
        mCalendarView.setRange(2022,1,1,Utils.getDateTimeY(System.currentTimeMillis()),Utils.getDateTimeM(System.currentTimeMillis()),Utils.getDateTimeD(System.currentTimeMillis()));
        mCalendarView.setSelectedColor(0x00000000,0xFF000000,0xFF000000);


//        mCalendarView.setSelectSingleMode();
    }


}
