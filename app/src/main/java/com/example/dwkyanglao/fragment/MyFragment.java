package com.example.dwkyanglao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.activity4.GrzlActivity;
import com.example.dwkyanglao.activity.activity4.GuanyuActivity;
import com.example.dwkyanglao.activity.activity4.SettingActivity;
import com.example.dwkyanglao.activity.activity4.SjhzcActivity;
import com.example.dwkyanglao.activity.activity4.XgMcActivity;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.SharedPreferencesUtils;


/**
 * Created by Administrator on 2017-02-24.
 * 我的
 */

public class MyFragment extends BaseFragment{


    private TextView mIdTvName;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_my;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        mIdTvName = ((TextView) rootView.findViewById(R.id.id_tv1));
        rootView.findViewById(R.id.id_layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GrzlActivity.class));
            }
        });
        rootView.findViewById(R.id.id_layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
        rootView.findViewById(R.id.id_layout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GuanyuActivity.class));
            }
        });
        rootView.findViewById(R.id.id_bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.clearAll(getActivity());
                startActivity(new Intent(getActivity(), SjhzcActivity.class));
                getActivity().finish();
            }
        });
        rootView.findViewById(R.id.id_img_xiugai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), XgMcActivity.class));
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Constant.myself.getData().getDisplayName()==null){
            mIdTvName.setText(Constant.myself.getData().getUsername());
        }else {
            mIdTvName.setText(Constant.myself.getData().getDisplayName());
        }

    }

    private void initdata() {
    }

}
