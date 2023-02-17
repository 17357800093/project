package com.example.dwkyanglao.activity.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.MainActivity;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.GrzlModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.DateTimeHelper;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GrzlActivity extends BaseActivity {
    private List<String> data1=new ArrayList<>();
    private List<String> data3=new ArrayList<>();
    private List<String> data4=new ArrayList<>();
    private OptionsPickerView mPickerView1;
    private TimePickerView mPickerView2;
    private OptionsPickerView mPickerView3;
    private OptionsPickerView mPickerView4;
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzl);
        initview();
        initdata();
    }

    private void initdata() {
        data1.add("男");
        data1.add("女");

        for (int i = 80; i < 250; i++) {
            data3.add(i+"厘米");
        }

        for (int i = 10; i < 200; i++) {
            data4.add(i+"公斤");
        }

        UtilsOKHttp.getInstance().get(Constant.URL_getuser, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess: "+result );
                GrzlModel data = new Gson().fromJson(result, GrzlModel.class);
                if(data!=null){
                    mIdTv1.setText(data.getData().getSex()==0?"男":"女");
                    mIdTv2.setText(data.getData().getBirthDay());
                    mIdTv3.setText(data.getData().getHeight()+"厘米");
                    mIdTv4.setText(data.getData().getWeight()+"公斤");
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void initview() {
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) findViewById(R.id.id_tv2));
        mIdTv3 = ((TextView) findViewById(R.id.id_tv3));
        mIdTv4= ((TextView) findViewById(R.id.id_tv4));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker1();
            }
        });
        findViewById(R.id.id_layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker2();
            }
        });
        findViewById(R.id.id_layout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker3();
            }
        });
        findViewById(R.id.id_layout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker4();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("birthDay",mIdTv2.getText().toString());
                map.put("sex",mIdTv1.getText().toString().equals("男")?0:1);
                map.put("height",Integer.parseInt(mIdTv3.getText().toString().substring(0,mIdTv3.getText().toString().length()-2)));
                map.put("weight",Integer.parseInt(mIdTv4.getText().toString().substring(0,mIdTv4.getText().toString().length()-2)));
                UtilsOKHttp.getInstance().put(Constant.URL_Userinfo, new Gson().toJson(map), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(GrzlActivity.this,"设置成功！");
                            finish();
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(GrzlActivity.this,codeMsgModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {
                        Log.e("pp", "failResult: "+failResult );
                    }
                });
            }
        });
    }


    //性别
    private void initPicker1() {
        if(mPickerView1!=null){
            mPickerView1.show();
            return;
        }
        mPickerView1 = new OptionsPickerBuilder(GrzlActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String xb = data1.get(options1);
                mIdTv1.setText(xb);
            }
        })
                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                .setTitleSize(20)//标题文字大小
                .setTitleColor(getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setSelectOptions(0)//设置选择的值
                .build();

        mPickerView1.setPicker(data1);//添加数据
        mPickerView1.show();
    }
    //身高
    private void initPicker3() {
        if(mPickerView3!=null){
            mPickerView3.show();
            return;
        }
        mPickerView3 = new OptionsPickerBuilder(GrzlActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String xb = data3.get(options1);
                mIdTv3.setText(xb);
            }
        })
                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                .setTitleSize(20)//标题文字大小
                .setTitleColor(getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setSelectOptions(100)//设置选择的值
                .build();

        mPickerView3.setPicker(data3);//添加数据
        mPickerView3.show();
    }
    //体重
    private void initPicker4() {
        if(mPickerView4!=null){
            mPickerView4.show();
            return;
        }
        mPickerView4 = new OptionsPickerBuilder(GrzlActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String xb = data4.get(options1);
                mIdTv4.setText(xb);
            }
        })
                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                .setTitleSize(20)//标题文字大小
                .setTitleColor(getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setSelectOptions(60)//设置选择的值
                .build();

        mPickerView4.setPicker(data4);//添加数据
        mPickerView4.show();
    }

    /**日期选择器控件*/
    private void initPicker2() {
        if(mPickerView2!=null){
            mPickerView2.show();
            return;
        }
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(DateTimeHelper.parseStringToDate("1999-01-01"));//设置为2006年4月28日
        //设置最小日期和最大日期
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(DateTimeHelper.parseStringToDate("1900-01-01"));//设置为2006年4月28日
        Calendar endDate = Calendar.getInstance();//最大日期是今天

        //时间选择器
        mPickerView2 = new TimePickerBuilder(GrzlActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                mIdTv2.setText(DateTimeHelper.formatToString(date,"yyyy年MM月dd日"));
            }
        })
                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)//是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setTitleSize(20)//标题文字大小
                .setTitleColor(getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setRangDate(startDate, endDate)//设置最小和最大日期
                .setDate(selectedDate)//设置选中的日期
                .build();
        mPickerView2.show();
    }

}
