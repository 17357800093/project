package com.example.dwkyanglao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import androidx.annotation.Nullable;

import com.dld.view.SegmentedControlItem;
import com.example.dwkyanglao.R;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.view.ProgressBarView;
import com.example.dwkyanglao.view.SegmentedControlView;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017-02-24.
 * 消息
 */

public class IndentFragment extends BaseFragment {

    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//我绑定的其他账号
    private SegmentedControlView mIdScv;
    private ListView mIdListview;
    private List<String> data=new ArrayList<>();
    private CommonAdapter<String> commonAdapter;
    private View mIdLayout2;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_indent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mIdScv = ((SegmentedControlView) rootView.findViewById(R.id.scv));
        mIdListview = ((ListView) rootView.findViewById(R.id.id_listview));
        mIdSpinner = ((Spinner) rootView.findViewById(R.id.id_spinner));
        mIdLayout2 = ((View) rootView.findViewById(R.id.id_layout2));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initadapter();
        initdata();
    }

    private void initadapter() {
        commonAdapter = new CommonAdapter<String>(getActivity(), R.layout.xiaoxilayout, data) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {

            }
        };
        mIdListview.setAdapter(commonAdapter);
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

        List<SegmentedControlItem> items = new ArrayList<>();
        items.add(new SegmentedControlItem("列表"));
        items.add(new SegmentedControlItem("图表"));
        mIdScv.addItems(items);
        mIdScv.setTextColor(Color.parseColor("#000000"));
        mIdScv.setSelectedItemTextColor(Color.parseColor("#000000"));
        mIdScv.setOnSegItemClickListener(new SegmentedControlView.OnSegItemClickListener() {
            @Override
            public void onItemClick(SegmentedControlItem item, int position) {
                if(position==0){
                    mIdLayout2.setVisibility(View.GONE);
                    mIdListview.setVisibility(View.VISIBLE);
                }else {
                    mIdLayout2.setVisibility(View.VISIBLE);
                    mIdListview.setVisibility(View.GONE);
                }
            }
        });
        for (int i = 0; i < 3; i++) {
            data.add("1");
        }
    }
}
