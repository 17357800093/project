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
                            ToastUtils.showToast(YjinDdcActivity.this,"???????????????");
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
                //?????????????????????????????????????????????
                mIdTv1.setText(data1.get(options1));
            }
        })
                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//?????????RelativeLayout????????????setDecorView??????????????????????????????????????????????????????????????????
                .setTitleSize(20)//??????????????????
                .setTitleColor(getResources().getColor(R.color.pickerview_title_text_color))//??????????????????
                .setCancelText("??????")//??????????????????
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))//????????????????????????
                .setSubmitText("??????")//??????????????????
                .setSubmitColor(getResources().getColor(R.color.pickerview_submit_text_color))//????????????????????????
                .setContentTextSize(20)//??????????????????
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))//??????????????????????????????
                .setLineSpacingMultiplier(1.8f)//?????????
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))//????????????????????????
                .setSelectOptions(10)//??????????????????
                .build();

        mPickerView1.setPicker(data1);//????????????
        mPickerView1.show();
    }
}
