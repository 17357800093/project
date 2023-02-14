package com.example.dwkyanglao.manage;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


/**
 * Created by Administrator on 2019/3/12.
 */

public abstract class BaseFragment extends Fragment {
    public View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        return mRootView;
    }

    /**
     * 设置Fragment布局
     *
     * @return
     */
    public abstract int getContentViewId();

}
