package com.example.dwkyanglao.activity;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.dwkyanglao.R;
import com.example.dwkyanglao.event.RefreshShouye;
import com.example.dwkyanglao.event.RefreshYujin;
import com.example.dwkyanglao.fragment.IndentFragment;
import com.example.dwkyanglao.fragment.LiveListFragment;
import com.example.dwkyanglao.fragment.MainFragment;
import com.example.dwkyanglao.fragment.MyFragment;
import com.example.dwkyanglao.manage.BaseActivity;
import com.example.dwkyanglao.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BaseActivity  implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup mIdGroup;
    private FrameLayout mIdFLayoutContainer;
    private RadioButton mIdRbtn1;
    private RadioButton mIdRbtn2;
    private RadioButton mIdRbtn3;
    private RadioButton mIdRbtn4;


    private boolean mDoubleBackExitPressedOnce;
    private MainFragment mMainFragment;
    private MyFragment mMyFragment;
    private IndentFragment mIndentFragment;
    private LiveListFragment mLiveListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mIdGroup= (RadioGroup)findViewById(R.id.id_group);
        mIdRbtn1= (RadioButton)findViewById(R.id.id_rbtn_1);
        mIdRbtn2= (RadioButton)findViewById(R.id.id_rbtn_2);
        mIdRbtn3= (RadioButton)findViewById(R.id.id_rbtn_3);
        mIdRbtn4= (RadioButton)findViewById(R.id.id_rbtn_4);
        mIdFLayoutContainer= (FrameLayout)findViewById(R.id.id_fLayout_container);
        mIdGroup.setOnCheckedChangeListener(this);
        mIdRbtn1.setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("pp", "onResume: " );
        int num = getIntent().getIntExtra("num", 0);
        if(num!=0){
            switch (num){
                case 1:
                    mIdRbtn1.setChecked(true);
                    break;
                case 2:
                    mIdRbtn2.setChecked(true);
                    break;
                case 3:
                    mIdRbtn3.setChecked(true);
                    break;
                case 4:
                    mIdRbtn4.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case R.id.id_rbtn_1:
                if (mMainFragment == null) {
                    mMainFragment = new MainFragment();
                    transaction.add(R.id.id_fLayout_container, mMainFragment);
                } else {
                    transaction.show(mMainFragment);
                }
                break;
            case R.id.id_rbtn_2:
                if (mLiveListFragment == null) {
                    mLiveListFragment = new LiveListFragment();
                    transaction.add(R.id.id_fLayout_container, mLiveListFragment);
                } else {
                    transaction.show(mLiveListFragment);
                }
                break;
            case R.id.id_rbtn_3:
                if (mIndentFragment == null) {
                    mIndentFragment = new IndentFragment();
                    transaction.add(R.id.id_fLayout_container, mIndentFragment);
                } else {
                    transaction.show(mIndentFragment);
                }
                EventBus.getDefault().post(new RefreshYujin(1));
                break;
            case R.id.id_rbtn_4:
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                    transaction.add(R.id.id_fLayout_container, mMyFragment);
                } else {
                    transaction.show(mMyFragment);
                }
                break;
        }
        transaction.commit();
    }
    public void hideAllFragment(FragmentTransaction transaction) {
        if (mMainFragment != null) {
            transaction.hide(mMainFragment);
        }
        if (mLiveListFragment != null) {
            transaction.hide(mLiveListFragment);
        }
        if (mIndentFragment != null) {
            transaction.hide(mIndentFragment);
        }
        if (mMyFragment != null) {
            transaction.hide(mMyFragment);
        }
    }

    @Override
    public void onBackPressed() {
        // 双击返回键关闭程序
        // 如果两秒重置时间内再次点击返回,则退出程序
        if (mDoubleBackExitPressedOnce) {
            exit();
            EventBus.getDefault().unregister(this);
            return;
        }
        mDoubleBackExitPressedOnce = true;
        showToast("再按一次返回键关闭程序");
        Utils.delay2Do(2000, new Runnable() {
            @Override
            public void run() {
                // 延迟两秒后重置标志位为false
                mDoubleBackExitPressedOnce = false;
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true) //在ui线程执行
    public void onEvent(RefreshShouye event) {
        if(event!=null){
            switch (event.isChange()){
                case 1:
                    mIdRbtn1.setChecked(true);
                    break;
                case 2:
                    mIdRbtn2.setChecked(true);
                    break;
                case 3:
                    mIdRbtn3.setChecked(true);
                    break;
                case 4:
                    mIdRbtn4.setChecked(true);
                    break;
            }
        }
    }

}
