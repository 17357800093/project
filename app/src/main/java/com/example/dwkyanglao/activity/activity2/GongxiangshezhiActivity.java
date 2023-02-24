package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.activity4.SzmmActivity;
import com.example.dwkyanglao.activity.activity4.WsgrxxActivity;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.GonxiangModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GongxiangshezhiActivity extends BaseActivity {

    private String id;
    private TextView mIdTv1;
    private ListView mIdListview;
    private  List<GonxiangModel.DataBean> data=new ArrayList<>();
    private CommonAdapter<GonxiangModel.DataBean> commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongxiangshezhi);
        id = getIntent().getStringExtra("id");
        initview();
        initadapter();
        initdata();

    }

    private void initadapter() {
        commonAdapter = new CommonAdapter<GonxiangModel.DataBean>(GongxiangshezhiActivity.this, R.layout.gonxianglayout, data) {
            @Override
            protected void convert(ViewHolder viewHolder, GonxiangModel.DataBean item, int position) {
                viewHolder.setText(R.id.id_tv1,item.getUserName());
                viewHolder.getView(R.id.id_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(GongxiangshezhiActivity.this)
                                .setMessage("确定删除？")
                                .setNegativeButton(android.R.string.cancel, null)
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HashMap<String, String> src = new HashMap<>();
                                        src.put("deviceId",id);
                                        UtilsOKHttp.getInstance().delete(Constant.URL_sharedevice + id + "/shareUser", new Gson().toJson(src), new UtilsOKHttp.OKCallback() {
                                            @Override
                                            public void onSuccess(String result) {
                                                CodeMsgModel codeMsgModel = new Gson().fromJson(result, CodeMsgModel.class);
                                                if(codeMsgModel!=null&&codeMsgModel.getCode()==0){
                                                    initdata();
                                                    ToastUtils.showToast(GongxiangshezhiActivity.this,"删除成功！");
                                                }else if(codeMsgModel!=null&&codeMsgModel.getErrorMessage()!=null){
                                                    ToastUtils.showToast(GongxiangshezhiActivity.this,codeMsgModel.getErrorMessage());
                                                }
                                            }

                                            @Override
                                            public void onFail(String failResult) {

                                            }
                                        });
                                    }
                                }).create().show();

                    }
                });
            }
        };
        mIdListview.setAdapter(commonAdapter);
    }

    private void initdata() {
        UtilsOKHttp.getInstance().get(Constant.URL_sharedevice + id + "/shareUser", new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess: "+result );
                GonxiangModel gonxiangModel = new Gson().fromJson(result, GonxiangModel.class);
                if(gonxiangModel!=null&&gonxiangModel.getData()!=null){
                    mIdTv1.setText(gonxiangModel.getData().size()+"");
                    data.clear();
                    data.addAll(gonxiangModel.getData());
                    commonAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void initview() {
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdListview = ((ListView) findViewById(R.id.id_listview));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GongxiangshezhiActivity.this, GonxiangActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
