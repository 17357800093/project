package com.example.dwkyanglao.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.Main2Activity;
import com.example.dwkyanglao.activity.activity2.XzsbActivity;
import com.example.dwkyanglao.activity.model.DeviceZuModel;
import com.example.dwkyanglao.activity.model.DevicesModel;
import com.example.dwkyanglao.activity.model.SyYjModel;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.manage.Popwindow;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.example.dwkyanglao.view.HorizontalListView;
import com.example.dwkyanglao.view.MyCircleProgress;

import com.google.gson.Gson;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017-02-24.
 * 首页
 */

public class MainFragment extends BaseFragment {


    private HorizontalListView mIdLv;
    private List<DevicesModel.DataBean> mdata=new ArrayList<>();
    private View mIdLayout1,mIdLayout2,mIdLayout3;
    private MyCircleProgress mIdMcp;
    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//我绑定的其他账号
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4,mIdTv5;
    private CommonAdapter<DevicesModel.DataBean> commonAdapter;
    private List<DeviceZuModel.DataBean> mDeviceData=new ArrayList<>();
    private TextView mIdTvName;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        initview(rootView);
        return rootView;
    }

    private void initview(View rootView) {
        mIdTvName = ((TextView) rootView.findViewById(R.id.id_tv_name));
        mIdLv = ((HorizontalListView) rootView.findViewById(R.id.id_listview));
        mIdLayout1 = rootView.findViewById(R.id.id_layout1);
        mIdLayout2 = rootView.findViewById(R.id.id_layout2);
        mIdLayout3 = rootView.findViewById(R.id.id_layout3);
        mIdMcp = ((MyCircleProgress) rootView.findViewById(R.id.id_mcp));
        mIdSpinner = ((Spinner) rootView.findViewById(R.id.id_spinner));

        mIdTv1 = ((TextView) rootView.findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) rootView.findViewById(R.id.id_tv2));
        mIdTv3 = ((TextView) rootView.findViewById(R.id.id_tv3));
        mIdTv4 = ((TextView) rootView.findViewById(R.id.id_tv4));
        mIdTv5 = ((TextView) rootView.findViewById(R.id.id_tv5));

        rootView.findViewById(R.id.id_layout_sm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Main2Activity.class));
            }
        });
        rootView.findViewById(R.id.id_img_tjsb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), XzsbActivity.class));
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
        initYujin();
        mIdMcp.SetCurrent(80);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(Constant.myself.getData().getDisplayName()==null){
            mIdTvName.setText(Constant.myself.getData().getUsername()+",欢迎您");
        }else {
            mIdTvName.setText(Constant.myself.getData().getDisplayName()+",欢迎您");
        }
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
    private void initYujin() {
        UtilsOKHttp.getInstance().get(Constant.URL_statisticsCount, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("pp", "onSuccess预警: "+result );
                if(result!=null){
                    SyYjModel syYjModel = new Gson().fromJson(result, SyYjModel.class);
                    if(syYjModel!=null&&syYjModel.getData()!=null){
                        for (int i = 0; i < syYjModel.getData().size(); i++) {
                            SyYjModel.DataBean dataBean = syYjModel.getData().get(i);
                            switch (dataBean.getTagType()){
                                case 1:
                                    mIdTv1.setText(dataBean.getCount()+"");
                                    break;
                                case 2:
                                    mIdTv2.setText(dataBean.getCount()+"");
                                    break;
                                case 3:
                                    mIdTv3.setText(dataBean.getCount()+"");
                                    break;
                                case 4:
                                    mIdTv4.setText(dataBean.getCount()+"");
                                    break;
                                case 5:
                                    mIdTv5.setText(dataBean.getCount()+"");
                                    break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
    }

    private void initdata() {
        UtilsOKHttp.getInstance().get(Constant.URL_Devicelist, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                DeviceZuModel deviceZuModel = new Gson().fromJson(result, DeviceZuModel.class);
                if(deviceZuModel!=null&&deviceZuModel.getData()!=null){
                    refreshDevice(deviceZuModel);
                }
            }

            @Override
            public void onFail(String failResult) {

            }
        });
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

        commonAdapter = new CommonAdapter<DevicesModel.DataBean>(getActivity(), R.layout.sylvlayout, mdata) {
            @Override
            protected void convert(ViewHolder viewHolder, DevicesModel.DataBean item, int position) {
                ImageView img = (ImageView) viewHolder.getView(R.id.id_img);
                String name="";
                if(item.getDeviceType()==10004){
                    name="体征监测垫";
                }else if(item.getDeviceType()==10003){
                    name="养老电动床";
                }else if(item.getDeviceType()==10005){
                    name="防褥疮床垫";
                }
                TextView mTvname = (TextView) viewHolder.getView(R.id.id_tv1);
                mTvname.setText(name);
                if(item.getConnected()==0){
                    img.setBackgroundResource(R.drawable.xhd);
                }else {
                    img.setBackgroundResource(R.drawable.xld);
                }

                View layout = viewHolder.getView(R.id.id_layout);
                if(item.isIscheck()){
                    layout.setBackgroundResource(R.drawable.mylayout1);
                    mTvname.setTextColor(Color.WHITE);
                }else {
                    layout.setBackgroundResource(R.drawable.mylayout);
                    mTvname.setTextColor(Color.BLACK);
                }
            }
        };
        mIdLv.setAdapter(commonAdapter);
        mIdLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mdata.size(); i++) {
                    if(position==i){
                        mdata.get(i).setIscheck(true);
                    }else {
                        mdata.get(i).setIscheck(false);
                    }
                }
                commonAdapter.notifyDataSetChanged();
                switch (mdata.get(position).getDeviceType()){
                    case 10004:
                        Click1();
                        break;
                    case 10005:
                        Click2();
                        break;
                    case 10003:
                        Click3();
                        break;
                }
            }
        });
    }

    public void Click1(){//体征
        mIdLayout1.setVisibility(View.VISIBLE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.GONE);
    }
    public void Click2(){//防褥疮
        mIdLayout1.setVisibility(View.GONE);
        mIdLayout2.setVisibility(View.VISIBLE);
        mIdLayout3.setVisibility(View.GONE);
    }
    public void Click3(){//养老
        mIdLayout1.setVisibility(View.GONE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.VISIBLE);
    }
}
