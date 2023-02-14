package com.example.dwkyanglao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import androidx.annotation.Nullable;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.activity2.ShebeiActivity;
import com.example.dwkyanglao.activity.activity2.XzsbActivity;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.utils.Utils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017-02-24.
 * 设备
 */

public class LiveListFragment extends BaseFragment {


    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//我绑定的其他账号
    private List<String> mdata=new ArrayList<>();//我绑定的其他账号
    private GridView mIdGv;
    private CommonAdapter<String> commonAdapter;

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
        commonAdapter = new CommonAdapter<String>(getActivity(), R.layout.sblblayout, mdata) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                Log.e("pp", "convert: "+position );
            }
        };
        mIdGv.setAdapter(commonAdapter);
        mIdGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ShebeiActivity.class));
            }
        });
    }
}
