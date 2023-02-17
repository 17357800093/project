package com.example.dwkyanglao.activity.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.GjsjModel;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import static com.example.dwkyanglao.manage.Constant.TAG;

public class GjsjActivity extends BaseActivity {

    private ImageView mIdImg;
    private int mDeviceType;
    private TextView mIdTv1;
    private String mDeviceUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gjsj);
        initview();
        initdata();
    }

    private void initdata() {
        mDeviceUri = getIntent().getStringExtra("uri");
        mDeviceType = getIntent().getIntExtra("type", 0);
        switch (mDeviceType){
            case 10004:
                mIdImg.setImageResource(R.mipmap.tzd);
                break;
            case 10003:
                mIdImg.setImageResource(R.mipmap.ylc);
                break;
            case 10005:
                mIdImg.setImageResource(R.mipmap.frcd);
                break;
        }
        jianchaGX();
    }

    private void initview() {
        mIdTv1 = ((TextView) findViewById(R.id.id_tv1));
        mIdImg = ((ImageView) findViewById(R.id.img));
        findViewById(R.id.id_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jianchaGX();
            }
        });
        findViewById(R.id.id_bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mIdTv1.getText())){
                    ToastUtils.showToast(GjsjActivity.this,"请先检查更新 获取最新版本");
                    return;
                }
                HashMap<String, Object> src = new HashMap<>();
                src.put("uri",mDeviceUri);
                src.put("version",mIdTv1.getText().toString());
                UtilsOKHttp.getInstance().post(Constant.URL_upgrade ,new Gson().toJson(src), new UtilsOKHttp.OKCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess: "+result );
                        CodeMsgModel gjsjModel = new Gson().fromJson(result, CodeMsgModel.class);
                        if(gjsjModel!=null&&gjsjModel.getCode()==0){
                            ToastUtils.showToast(GjsjActivity.this,"发送升级指令成功");
                        }else if(gjsjModel!=null&&gjsjModel.getErrorMessage()!=null){
                            ToastUtils.showToast(GjsjActivity.this,gjsjModel.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFail(String failResult) {
                        Log.e(TAG, "failResult: "+failResult );
                    }
                });
            }
        });
    }

    private void jianchaGX() {
        UtilsOKHttp.getInstance().get(Constant.URL_getota + mDeviceType + "/lastversion", new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: "+result );
                GjsjModel gjsjModel = new Gson().fromJson(result, GjsjModel.class);
                if(gjsjModel!=null&&gjsjModel.getCode()==0){
                    mIdTv1.setText(gjsjModel.getData().get_$10004().getModuleVersions().getMattress().getVersion());
                }else {
                    mIdTv1.setText("当前已是最新版本");
                }
            }

            @Override
            public void onFail(String failResult) {
                Log.e(TAG, "failResult: "+failResult );
            }
        });
    }
}
