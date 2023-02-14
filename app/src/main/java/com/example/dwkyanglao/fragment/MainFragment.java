package com.example.dwkyanglao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import androidx.annotation.Nullable;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.Main2Activity;
import com.example.dwkyanglao.activity.activity2.XzsbActivity;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.view.HorizontalListView;
import com.example.dwkyanglao.view.MyCircleProgress;
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
    private List<String> mdata=new ArrayList<>();
    private CommonAdapter<String> commonAdapter;
    private View mIdLayout1,mIdLayout2,mIdLayout3;
    private MyCircleProgress mIdMcp;
    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//我绑定的其他账号
    @Override
    public int getContentViewId() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mIdLv = ((HorizontalListView) rootView.findViewById(R.id.id_listview));
        mIdLayout1 = rootView.findViewById(R.id.id_layout1);
        mIdLayout2 = rootView.findViewById(R.id.id_layout2);
        mIdLayout3 = rootView.findViewById(R.id.id_layout3);
        mIdMcp = ((MyCircleProgress) rootView.findViewById(R.id.id_mcp));
        mIdSpinner = ((Spinner) rootView.findViewById(R.id.id_spinner));
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
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
        mIdMcp.SetCurrent(80);
    }

    private void initdata() {
        Sbdata.add("我的设备");
        Sbdata.add("设备A");
        Sbdata.add("设备B");
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.textlayout, Sbdata);
        adapter.setDropDownViewResource(R.layout.textlayout);
        mIdSpinner.setAdapter(adapter);
        mIdSpinner.setSelection(0);
        mIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mdata.add("1");
        mdata.add("2");
        mdata.add("3");
        commonAdapter = new CommonAdapter<String>(getActivity(), R.layout.sylvlayout, mdata) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.id_tv1,item);
            }
        };
        mIdLv.setAdapter(commonAdapter);
        mIdLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Click1();
                        break;
                    case 1:
                        Click2();
                        break;
                    case 2:
                        Click3();
                        break;
                }
            }
        });
    }

    public void Click1(){
        mIdLayout1.setVisibility(View.VISIBLE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.GONE);
    }
    public void Click2(){
        mIdLayout1.setVisibility(View.GONE);
        mIdLayout2.setVisibility(View.VISIBLE);
        mIdLayout3.setVisibility(View.GONE);
    }
    public void Click3(){
        mIdLayout1.setVisibility(View.GONE);
        mIdLayout2.setVisibility(View.GONE);
        mIdLayout3.setVisibility(View.VISIBLE);
    }
}
