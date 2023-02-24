package com.example.dwkyanglao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.dld.view.SegmentedControlItem;
import com.example.dwkyanglao.R;
import com.example.dwkyanglao.activity.model.DeviceZuModel;
import com.example.dwkyanglao.activity.model.DevicesModel;
import com.example.dwkyanglao.activity.model.SyYjModel;
import com.example.dwkyanglao.activity.model.WarncharModel;
import com.example.dwkyanglao.activity.model.YijnXxModel;
import com.example.dwkyanglao.event.RefreshYujin;
import com.example.dwkyanglao.event.Refreshshebei;
import com.example.dwkyanglao.manage.BaseFragment;
import com.example.dwkyanglao.manage.Constant;
import com.example.dwkyanglao.utils.Utils;
import com.example.dwkyanglao.utils.UtilsOKHttp;
import com.example.dwkyanglao.view.ProgressBarView;
import com.example.dwkyanglao.view.SegmentedControlView;
import com.google.gson.Gson;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;


/**
 * Created by Administrator on 2023-02-07.
 * 消息
 */

public class IndentFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private Spinner mIdSpinner;
    private ArrayAdapter adapter;
    private List<String> Sbdata=new ArrayList<>();//我绑定的其他账号
    private SegmentedControlView mIdScv;
    private ListView mIdListview;
    private List<YijnXxModel.DataBean> data=new ArrayList<>();
    private CommonAdapter<YijnXxModel.DataBean> commonAdapter;
    private View mIdLayout2,mIdLayout1;
    private List<DeviceZuModel.DataBean> mDeviceData=new ArrayList<>();
    private BGARefreshLayout mRefreshLayout;
    private int mSpinnerSelect=0;
    private RelativeLayout layoutc1,layoutc2,layoutc3,layoutc4,layoutc5;
    private View id_messw;
    private int measuredWidth;
    private TextView mIdTv1,mIdTv2,mIdTv3,mIdTv4,mIdTv5;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_indent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        layoutc1 = ((RelativeLayout) rootView.findViewById(R.id.id_layout_c1));
        layoutc2 = ((RelativeLayout) rootView.findViewById(R.id.id_layout_c2));
        layoutc3 = ((RelativeLayout) rootView.findViewById(R.id.id_layout_c3));
        layoutc4 = ((RelativeLayout) rootView.findViewById(R.id.id_layout_c4));
        layoutc5 = ((RelativeLayout) rootView.findViewById(R.id.id_layout_c5));
        layoutc1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                layoutc1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                measuredWidth = layoutc1.getMeasuredWidth();
                Log.e("pp", "onGlobalLayout: "+measuredWidth );
                refreshList();
            }
        });
        mIdTv1 = ((TextView) rootView.findViewById(R.id.id_tv1));
        mIdTv2 = ((TextView) rootView.findViewById(R.id.id_tv2));
        mIdTv3 = ((TextView) rootView.findViewById(R.id.id_tv3));
        mIdTv4 = ((TextView) rootView.findViewById(R.id.id_tv4));
        mIdTv5 = ((TextView) rootView.findViewById(R.id.id_tv5));
        mIdScv = ((SegmentedControlView) rootView.findViewById(R.id.scv));
        mIdListview = ((ListView) rootView.findViewById(R.id.id_listview));
        mIdSpinner = ((Spinner) rootView.findViewById(R.id.id_spinner));
        mIdLayout1 = ((View) rootView.findViewById(R.id.id_layout1));
        mIdLayout2 = ((View) rootView.findViewById(R.id.id_layout2));
        mRefreshLayout = (BGARefreshLayout) rootView.findViewById(R.id.rl_modulename_refresh);
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(),false);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initadapter();
        initYujin();
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
            for (int i = 0; i < deviceZuModel.getData().size(); i++) {
                Sbdata.add(deviceZuModel.getData().get(i).getName());
            }
            adapter.notifyDataSetChanged();
        }else {
            Sbdata.clear();
            Sbdata.add("我的健康");
            adapter.notifyDataSetChanged();
        }
    }

    private void initYujin() {
        UtilsOKHttp.getInstance().get(Constant.URL_Warnlist, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                YijnXxModel yijnXxModel = new Gson().fromJson(result, YijnXxModel.class);
                if(yijnXxModel!=null){
                    data.clear();
                    data.addAll(yijnXxModel.getData());
                    commonAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFail(String failResult) {

            }
        });
    }
    private void initadapter() {
        commonAdapter = new CommonAdapter<YijnXxModel.DataBean>(getActivity(), R.layout.xiaoxilayout, data) {
            @Override
            protected void convert(ViewHolder viewHolder, YijnXxModel.DataBean item, int position) {
                String name="";
                if(item.getDeviceType()==10004){
                    name="体征监测垫";
                }else if(item.getDeviceType()==10003){
                    name="养老电动床";
                }else if(item.getDeviceType()==10005){
                    name="防褥疮床垫";
                }
                viewHolder.setText(R.id.id_tv1,name);
                viewHolder.setText(R.id.id_tv2,"(用户"+item.getUsername()+")");
                viewHolder.setText(R.id.id_tv3,item.getContent());
                viewHolder.setText(R.id.id_tv4, Utils.getDateTimeFromMillisecond1(Utils.switchCreateTime(item.getTime())));

            }
        };
        mIdListview.setAdapter(commonAdapter);
    }

    private void initdata() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.textlayout, Sbdata);
        adapter.setDropDownViewResource(R.layout.textlayout1);
        mIdSpinner.setAdapter(adapter);
        mIdSpinner.setSelection(0);
        mIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerSelect=position;
                refreshList();
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
                initYujin();
                refreshList();
                if(position==0){
                    mIdLayout2.setVisibility(View.GONE);
                    mIdLayout1.setVisibility(View.VISIBLE);
                }else {
                    mIdLayout2.setVisibility(View.VISIBLE);
                    mIdLayout1.setVisibility(View.GONE);
                }
            }
        });

    }

    private void refreshList() {
        if(mDeviceData.size()!=0) {
            layoutc1.removeAllViews();
            layoutc2.removeAllViews();
            layoutc3.removeAllViews();
            layoutc4.removeAllViews();
            layoutc5.removeAllViews();
            UtilsOKHttp.getInstance().get(Constant.URL_Warnchartist + mDeviceData.get(mSpinnerSelect).getId(), new UtilsOKHttp.OKCallback() {
                @Override
                public void onSuccess(String result) {
                    WarncharModel warncharModel = new Gson().fromJson(result, WarncharModel.class);
                    if (warncharModel != null && warncharModel.getData() != null) {
                        String dateTimeFromMillisecond1 = Utils.getDateTimeFromMillisecond1(System.currentTimeMillis());
                        String dateTimeFromMillisecond2 = Utils.getDateTimeFromMillisecond1(System.currentTimeMillis()-6*60*60*1000);
                        String dateTimeFromMillisecond3 = Utils.getDateTimeFromMillisecond1(System.currentTimeMillis()-12*60*60*1000);
                        String dateTimeFromMillisecond4 = Utils.getDateTimeFromMillisecond1(System.currentTimeMillis()-18*60*60*1000);
                        String dateTimeFromMillisecond5 = Utils.getDateTimeFromMillisecond1(System.currentTimeMillis()-24*60*60*1000);

                        long oldtime = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
                        refreshChar(warncharModel.getData().get_$1(),R.drawable.chard1,oldtime,layoutc1);
                        refreshChar(warncharModel.getData().get_$2(),R.drawable.chard2,oldtime,layoutc2);
                        refreshChar(warncharModel.getData().get_$3(),R.drawable.chard3,oldtime,layoutc3);
                        refreshChar(warncharModel.getData().get_$4(),R.drawable.chard4,oldtime,layoutc4);
                        refreshChar(warncharModel.getData().get_$5(),R.drawable.chard5,oldtime,layoutc5);
                        mIdTv1.setText(dateTimeFromMillisecond1);
                        mIdTv2.setText(dateTimeFromMillisecond2);
                        mIdTv3.setText(dateTimeFromMillisecond3);
                        mIdTv4.setText(dateTimeFromMillisecond4);
                        mIdTv5.setText(dateTimeFromMillisecond5);

                    }
                }

                @Override
                public void onFail(String failResult) {

                }
            });
        }
    }

    private void refreshChar(List<String> list, int chard1, long oldtime,RelativeLayout layoutc1) {
        for (int i = 0; i < list.size(); i++) {
            long time1 = Utils.switchCreateTime1(list.get(i));
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(chard1);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    20, 20);
            imageView.setLayoutParams(lp);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            int left = (int) ((time1 - oldtime) * measuredWidth / (24 * 60 * 60 * 1000))-20;
            lp.setMargins(left<0?0:left,0,0,0);
            layoutc1.addView(imageView);
            Log.e("pp", "refreshChar: "+ measuredWidth);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        UtilsOKHttp.getInstance().get(Constant.URL_Warnlist, new UtilsOKHttp.OKCallback() {
            @Override
            public void onSuccess(String result) {
                YijnXxModel yijnXxModel = new Gson().fromJson(result, YijnXxModel.class);
                if(yijnXxModel!=null){
                    data.clear();
                    data.addAll(yijnXxModel.getData());
                    commonAdapter.notifyDataSetChanged();
                    mRefreshLayout.endRefreshing();
                }
            }
            @Override
            public void onFail(String failResult) {

            }
        });
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true) //在ui线程执行
    public void onEvent(RefreshYujin event) {
        if(event!=null) {
            initYujin();
            refreshList();
        }
    }
}
