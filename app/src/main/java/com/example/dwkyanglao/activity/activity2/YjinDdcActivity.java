package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

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

public class YjinDdcActivity extends BaseActivity {
    private String mDeviceUri;
    private TextView mIdTv1;
    private OptionsPickerView mPickerView1;
    private List<String> data1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjin_ddc);
        initview();
        initPv();
        initdata();
    }

    private void initview() {
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        findViewById(R.id.id_layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPicker1();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YjinModel.DataBean getadditionalProp1 = GetadditionalProp1();
                List<YjinModel.DataBean> list=new ArrayList<>();
                list.add(getadditionalProp1);

                UtilsOKHttp.getInstance().put(Constant.URL_DeviceConfig+"?uri="+mDeviceUri,new Gson().toJson(list), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                            ToastUtils.showToast(YjinDdcActivity.this,"设置成功！");
                            finish();
                        }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                            ToastUtils.showToast(YjinDdcActivity.this,codeMsgModel.getErrorMessage());
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
        dataBean.setTag("guardrail");
        dataBean.setKeep(Integer.parseInt(mIdTv1.getText().toString()));
        return dataBean;
    }

    private void initPv() {
        for (int i = 5; i <= 60; i++) {
            data1.add(i+"");
        }

    }
    private void initdata() {
        mDeviceUri = getIntent().getStringExtra("uri");
        UtilsOKHttp.getInstance().get(Constant.URL_DeviceConfig + "?uri=" + mDeviceUri, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess: "+result );
                YjinModel yjinModel = new Gson().fromJson(result, YjinModel.class);
                if(yjinModel!=null&&yjinModel.getData()!=null){
                    List<YjinModel.DataBean> data = yjinModel.getData();
                    for (int i = 0; i < data.size(); i++) {
                        YjinModel.DataBean dataBean = data.get(i);
                        if(dataBean.getTag().equals("guardrail")){
                            mIdTv1.setText(dataBean.getKeep()+"");
                        }
                    }
                }else if(yjinModel!=null){
                    ToastUtils.showToast(YjinDdcActivity.this,yjinModel.getErrorMessage());
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }
    private void initPicker1() {
        if(mPickerView1!=null){
            mPickerView1.show();
            return;
        }
        mPickerView1 = new OptionsPickerBuilder(YjinDdcActivity.this, new OnOptionsSelectListener() {
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
                .setSelectOptions(10)//设置选择的值
                .build();

        mPickerView1.setPicker(data1);//添加数据
        mPickerView1.show();
    }
}
