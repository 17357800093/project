package com.example.dwkyanglao.activity.activity2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.YjinModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class YjinTzdActivity extends BaseActivity {

    private String mDeviceUri;
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4,mIdTv5,mIdTv6,mIdTv7;
    private OptionsPickerView mPickerView1,mPickerView2,mPickerView3,mPickerView4,mPickerView5,mPickerView6,mPickerView7;
    private List<String> data1=new ArrayList<>();
    private List<String> data2=new ArrayList<>();
    private List<String> data3=new ArrayList<>();
    private List<String> data4=new ArrayList<>();
    private List<String> data5=new ArrayList<>();
    private List<String> data6=new ArrayList<>();
    private List<String> data7=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjin_tzd);
        initview();
        initPv();
        initdata();
    }

    private void initPv() {
        for (int i = 80; i <= 200; i++) {
            data1.add(i+"");
        }
        for (int i = 40; i <= 100; i++) {
            data2.add(i+"");
        }
        for (int i = 1; i <= 60; i++) {
            data3.add(i+"");
        }
        for (int i = 10; i <= 90; i++) {
            data4.add(i+"");
        }
        for (int i = 4; i <= 20; i++) {
            data5.add(i+"");
        }
        for (int i = 1; i <= 60; i++) {
            data6.add(i+"");
        }
        for (int i = 1; i <= 60; i++) {
            data7.add(i+"");
        }
    }

    private void initdata() {
        mDeviceUri = getIntent().getStringExtra("uri");
        UtilsOKHttp.getInstance().get(Constant.URL_DeviceConfig + "?uri=" + mDeviceUri, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                YjinModel yjinModel = new Gson().fromJson(result, YjinModel.class);
                if(yjinModel!=null&&yjinModel.getData()!=null){
                    List<YjinModel.DataBean> data = yjinModel.getData();
                    for (int i = 0; i < data.size(); i++) {
                        YjinModel.DataBean dataBean = data.get(i);
                        if(dataBean.getTag().equals("heart")){
                            mIdTv1.setText(dataBean.getState().getMax()+"");
                            mIdTv2.setText(dataBean.getState().getMin()+"");
                            mIdTv3.setText(dataBean.getKeep()+"");
                        }else if(dataBean.getTag().equals("breath")){
                            mIdTv4.setText(dataBean.getState().getMax()+"");
                            mIdTv5.setText(dataBean.getState().getMin()+"");
                            mIdTv6.setText(dataBean.getKeep()+"");
                        }else if(dataBean.getTag().equals("move")){
                            mIdTv7.setText(dataBean.getKeep()+"");
                        }
                    }
                }else if(yjinModel!=null){
                    ToastUtils.showToast(YjinTzdActivity.this,yjinModel.getErrorMessage());
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
        mIdTv5= ((TextView) findViewById(R.id.id_tv5));
        mIdTv6= ((TextView) findViewById(R.id.id_tv6));
        mIdTv7= ((TextView) findViewById(R.id.id_tv7));
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
        findViewById(R.id.id_layout5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker5();
            }
        });
        findViewById(R.id.id_layout6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker6();
            }
        });
        findViewById(R.id.id_layout7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker7();
            }
        });
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YjinModel.DataBean getadditionalProp1 = GetadditionalProp1();
                YjinModel.DataBean getadditionalProp2 = GetadditionalProp2();
                YjinModel.DataBean getadditionalProp3 = GetadditionalProp3();
                List<YjinModel.DataBean> list=new ArrayList<>();
                list.add(getadditionalProp1);
                list.add(getadditionalProp2);
                list.add(getadditionalProp3);

                UtilsOKHttp.getInstance().put(Constant.URL_DeviceConfig+"?uri="+mDeviceUri,new Gson().toJson(list), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(YjinTzdActivity.this,"设置成功！");
                            finish();
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(YjinTzdActivity.this,codeMsgModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {

                    }
                });
            }
        });
    }

    private YjinModel.DataBean GetadditionalProp1() {
        YjinModel.DataBean dataBean = new YjinModel.DataBean();
        dataBean.setTag("heart");
        dataBean.setKeep(Integer.parseInt(mIdTv3.getText().toString()));
        YjinModel.DataBean.StateBean stateBean = new YjinModel.DataBean.StateBean();
        stateBean.setMax(Integer.parseInt(mIdTv1.getText().toString()));
        stateBean.setMin(Integer.parseInt(mIdTv2.getText().toString()));
        dataBean.setState(stateBean);
        return dataBean;
    }
    private YjinModel.DataBean GetadditionalProp2() {
        YjinModel.DataBean dataBean = new YjinModel.DataBean();
        dataBean.setTag("breath");
        dataBean.setKeep(Integer.parseInt(mIdTv6.getText().toString()));
        YjinModel.DataBean.StateBean stateBean = new YjinModel.DataBean.StateBean();
        stateBean.setMax(Integer.parseInt(mIdTv4.getText().toString()));
        stateBean.setMin(Integer.parseInt(mIdTv5.getText().toString()));
        dataBean.setState(stateBean);
        return dataBean;
    }
    private YjinModel.DataBean GetadditionalProp3() {
        YjinModel.DataBean dataBean = new YjinModel.DataBean();
        dataBean.setTag("move");
        dataBean.setKeep(Integer.parseInt(mIdTv7.getText().toString()));
        return dataBean;
    }
    private void initPicker1() {
        if(mPickerView1!=null){
            mPickerView1.show();
            return;
        }
        mPickerView1 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv1.setText(data1.get(options1));
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
                .setSelectOptions(20)//设置选择的值
                .build();

        mPickerView1.setPicker(data1);//添加数据
        mPickerView1.show();
    }
    private void initPicker2() {
        if(mPickerView2!=null){
            mPickerView2.show();
            return;
        }
        mPickerView2 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv2.setText(data2.get(options1));
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
                .setSelectOptions(20)//设置选择的值
                .build();

        mPickerView2.setPicker(data2);//添加数据
        mPickerView2.show();
    }
    private void initPicker3() {
        if(mPickerView3!=null){
            mPickerView3.show();
            return;
        }
        mPickerView3 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv3.setText(data3.get(options1));
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
                .setSelectOptions(1)//设置选择的值
                .build();

        mPickerView3.setPicker(data3);//添加数据
        mPickerView3.show();
    }
    private void initPicker4() {
        if(mPickerView4!=null){
            mPickerView4.show();
            return;
        }
        mPickerView4 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv4.setText(data4.get(options1));
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
                .setSelectOptions(10)//设置选择的值
                .build();

        mPickerView4.setPicker(data4);//添加数据
        mPickerView4.show();
    }
    private void initPicker5() {
        if(mPickerView5!=null){
            mPickerView5.show();
            return;
        }
        mPickerView5 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv5.setText(data5.get(options1));
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
                .setSelectOptions(8)//设置选择的值
                .build();

        mPickerView5.setPicker(data5);//添加数据
        mPickerView5.show();
    }
    private void initPicker6() {
        if(mPickerView6!=null){
            mPickerView6.show();
            return;
        }
        mPickerView6 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv6.setText(data6.get(options1));
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
                .setSelectOptions(1)//设置选择的值
                .build();

        mPickerView6.setPicker(data6);//添加数据
        mPickerView6.show();
    }
    private void initPicker7() {
        if(mPickerView7!=null){
            mPickerView7.show();
            return;
        }
        mPickerView7 = new OptionsPickerBuilder(YjinTzdActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mIdTv7.setText(data7.get(options1));
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
                .setSelectOptions(1)//设置选择的值
                .build();

        mPickerView7.setPicker(data7);//添加数据
        mPickerView7.show();
    }
}
