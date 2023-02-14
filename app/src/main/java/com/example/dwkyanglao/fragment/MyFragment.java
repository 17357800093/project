package com.example.dwkyanglao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import androidx.annotation.Nullable;

import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.activity4.GrzlActivity;
import com.example.dwkyanglao.activity.activity4.GuanyuActivity;
import com.example.dwkyanglao.activity.activity4.SettingActivity;
import com.example.dwkyanglao.activity.activity4.SjhzcActivity;
import com.example.dwkyanglao.activity.activity4.ZhuceActivity;
import com.example.dwkyanglao.manage.BaseFragment;


/**
 * Created by Administrator on 2017-02-24.
 * 我的
 */

public class MyFragment extends BaseFragment {


    @Override
    public int getContentViewId() {
        return R.layout.fragment_my;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
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
                startActivity(new Intent(getActivity(), SjhzcActivity.class));
                getActivity().finish();
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
