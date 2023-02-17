package com.example.dwkyanglao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import androidx.annotation.Nullable;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.activity2.ShebeiActivity;
import com.example.dwkyanglao.activity.activity2.XzsbActivity;
import com.example.dwkyanglao.activity.activity4.SzmmActivity;
import com.example.dwkyanglao.activity.activity4.WsgrxxActivity;
import com.example.dwkyanglao.activity.model.CodeMsgModel;
import com.example.dwkyanglao.activity.model.DeviceZuModel;
import com.example.dwkyanglao.activity.model.DevicesModel;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.manage.Popwindow;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.github.lazylibrary.util.ToastUtils;
import com.google.gson.Gson;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017-02-24.
 * 设备
 */

public class LiveListFragment extends BaseFragment {


    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//我绑定的其他账号
    private List<DevicesModel.DataBean> mdata=new ArrayList<>();//我绑定的其他账号
    private GridView mIdGv;
    private CommonAdapter<DevicesModel.DataBean> commonAdapter;
    private List<DeviceZuModel.DataBean> mDeviceData=new ArrayList<>();

    @Override
    public int getContentViewId() {
        return R.layout.fragment_livelist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mIdSpinner = ((Spinner) rootView.findViewById(R.id.id_spinner));
        mIdGv = ((GridView) rootView.findViewById(R.id.id_gv));
        rootView.findViewById(R.id.id_img_tjsb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), XzsbActivity.class));

            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();

    }

     @Override
    public void onResume() {
        super.onResume();
        UtilsOKHttp.getInstance().get(Constant.URL_Devicelist, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess: "+result );
                DeviceZuModel deviceZuModel = new Gson().fromJson(result, DeviceZuModel.class);
                if(deviceZuModel!=null&&deviceZuModel.getData()!=null){
                    refreshDevice(deviceZuModel);
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void refreshDevice(DeviceZuModel deviceZuModel) {
        mDeviceData.clear();
        if(deviceZuModel.getData().size()!=0){
            mDeviceData.addAll(deviceZuModel.getData());
            Sbdata.clear();
            for (int i = 0; i < mDeviceData.size(); i++) {
                Sbdata.add(mDeviceData.get(i).getName());
            }
            adapter.notifyDataSetChanged();
        }else {
            Sbdata.clear();
            Sbdata.add("我的健康");
            adapter.notifyDataSetChanged();
        }
        mIdSpinner.setSelection(0);
        if(mDeviceData.size()!=0){
            UtilsOKHttp.getInstance().get(Constant.URL_Group + mDeviceData.get(0).getId() + "/deviceList", new UtilsOKHttp.OKCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.e("pp", "onSuccess: "+result );
                    DevicesModel device= new Gson().fromJson(result, DevicesModel.class);
                    mdata.clear();
                    if(device!=null&&device.getData()!=null){
                        mdata.addAll(device.getData());
                    }
                    commonAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFail(String failResult) {

                }
            });
        }else {
            mdata.clear();
            commonAdapter.notifyDataSetChanged();
        }
    }

    private void initdata() {
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.textlayout, Sbdata);
        adapter.setDropDownViewResource(R.layout.textlayout);
        mIdSpinner.setAdapter(adapter);
        mIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("pp", "onItemSelected: "+ mDeviceData.size());
                if(mDeviceData.size()!=0){
                    UtilsOKHttp.getInstance().get(Constant.URL_Group + mDeviceData.get(position).getId() + "/deviceList", new UtilsOKHttp.OKCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("pp", "onSuccess: "+result );
                            DevicesModel device= new Gson().fromJson(result, DevicesModel.class);
                            mdata.clear();
                            if(device!=null&&device.getData()!=null){
                                mdata.addAll(device.getData());
                            }
                            commonAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFail(String failResult) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        commonAdapter = new CommonAdapter<DevicesModel.DataBean>(getActivity(), R.layout.sblblayout, mdata) {
            @Override
            protected void convert(ViewHolder viewHolder, DevicesModel.DataBean item, int position) {
                ImageView img = (ImageView) viewHolder.getView(R.id.id_img);
                String name="";
                if(item.getDeviceType()==10004){
                    name="体征监测垫";
                    img.setImageResource(R.mipmap.tzd);
                }else if(item.getDeviceType()==10003){
                    name="养老电动床";
                    img.setImageResource(R.mipmap.ylc);
                }else if(item.getDeviceType()==10005){
                    name="防褥疮床垫";
                    img.setImageResource(R.mipmap.frcd);
                }
                viewHolder.setText(R.id.id_tv1,name);
                viewHolder.setText(R.id.id_tv2,item.getName());

            }
        };
        mIdGv.setAdapter(commonAdapter);
        mIdGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShebeiActivity.class);
                intent.putExtra("id",mdata.get(position).getId());
                intent.putExtra("type",mdata.get(position).getDeviceType());
                intent.putExtra("uri",mdata.get(position).getUri());
                startActivity(intent);
            }
        });
    }
}
