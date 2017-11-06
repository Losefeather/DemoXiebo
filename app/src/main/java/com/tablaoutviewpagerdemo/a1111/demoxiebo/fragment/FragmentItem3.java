package com.tablaoutviewpagerdemo.a1111.demoxiebo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.tablaoutviewpagerdemo.a1111.demoxiebo.FeagmentActivity;
import com.tablaoutviewpagerdemo.a1111.demoxiebo.FragmentFactory;
import com.tablaoutviewpagerdemo.a1111.demoxiebo.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import superlibrary.adapter.BaseViewHolder;
import superlibrary.adapter.SuperBaseAdapter;
import superlibrary.recycleview.ProgressStyle;
import superlibrary.recycleview.SuperRecyclerView;


import static com.tablaoutviewpagerdemo.a1111.demoxiebo.FeagmentActivity.setButton;

/**
 * Created by a1111 on 17/9/30.
 */

public class FragmentItem3 extends BaseRxFragment {
    public static String TAG = "FragmentItem3";
    @Bind(R.id.sp_item3)
    Spinner spItem3;
    @Bind(R.id.rcv_item3)
    SuperRecyclerView rcvItem3;
    private View view;
    private SuperRecyclerViewCAdapter srva;
    private List<Item> powerList = new ArrayList<Item>();
    private String[] ss;
    private boolean isUp = true;
    private ArrayAdapter<String> spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FeagmentActivity.Num = 0;
        setButton();
        ButterKnife.bind(this.getActivity());
        ss = new String[]{getString(R.string.pinpvpiancha), getString(R.string.dianyapiancha), getString(R.string.dianyabupinghengdu), getString(R.string.shanbian), getString(R.string.dianyaxiebojibianlv),
                getString(R.string.dianyaxiebohanyoulv), getString(R.string.dianliuxiebohanyoulv)};
        view = inflater.inflate(R.layout.fragment_item3, container, false);
        rcvItem3 = view.findViewById(R.id.rcv_item3);
        spItem3 = view.findViewById(R.id.sp_item3);
        initData(isUp);
        String[] list = getContext().getResources().getStringArray(R.array.spingarr);
        //适配器
        spinnerAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, list);
        //设置样式
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器;
        spItem3.setAdapter(spinnerAdapter);
        spItem3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    if (!isUp) {
                        isUp = true;
                        reFreash(isUp);
                    }
                } else {
                    if (isUp) {
                        isUp = false;
                        reFreash(isUp);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        srva = new SuperRecyclerViewCAdapter(this.getContext(), powerList, this.getFragmentManager());
        rcvItem3.setLayoutManager(layoutManager);
        rcvItem3.setRefreshEnabled(true);//可以定制是否开启下拉刷新
        rcvItem3.setLoadMoreEnabled(false);//可以定制是否开启加载更多
        rcvItem3.setLoadingListener(new SuperRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        reFreash(isUp);
                        super.handleMessage(msg);
                    }
                };
                handler.sendEmptyMessage(123);
            }

            @Override
            public void onLoadMore() {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        rcvItem3.completeLoadMore();
                        super.handleMessage(msg);
                    }
                };
            }
        });//下拉刷新，上拉加载的监听
        rcvItem3.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);//下拉刷新的样式
        rcvItem3.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);//上拉加载的样式
        rcvItem3.setArrowImageView(R.mipmap.ic_pulltorefresh_arrow);//设置下拉箭头
        rcvItem3.setAdapter(srva);
        return view;
    }

    public void initData(boolean isUp) {
        powerList.clear();
        Random random = new Random(100);
        for (int i = 0; i < ss.length; i++) {
            Item item = new Item();
            item.setItem1(ss[i]);
            item.setItem2(random.nextInt(100));
            powerList.add(item);
        }
        CollectionsList(powerList, isUp);
    }

    private void CollectionsList(List<Item> powerList, boolean isUp) {
        if (isUp) {
            Collections.sort(powerList, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    Item item1 = (Item) o1;
                    Item item2 = (Item) o2;
                    if (item1.getItem2() > item2.getItem2()) {
                        return 1;
                    } else if (item1.getItem2() == item2.getItem2()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        } else {
            Collections.sort(powerList, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    Item item1 = (Item) o1;
                    Item item2 = (Item) o2;
                    if (item1.getItem2() < item2.getItem2()) {
                        return 1;
                    } else if (item1.getItem2() == item2.getItem2()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this.getActivity());
        super.onDestroyView();
    }

    private void reFreash(boolean isUp) {
        powerList.clear();
        Random random = new Random(99);
        for (int i = 0; i < ss.length; i++) {
            Item item = new Item();
            item.setItem1(ss[i]);
            item.setItem2(random.nextInt(100));
            powerList.add(item);
        }
        CollectionsList(powerList, isUp);
        srva.notifyDataSetChanged();
        rcvItem3.completeRefresh();
    }
}

class Item {
    private String item1;
    private int item2;

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }
}

class SuperRecyclerViewCAdapter extends SuperBaseAdapter<Item> {
    public FragmentManager fragmentManager;

    public SuperRecyclerViewCAdapter(Context context) {
        super(context);
    }

    public SuperRecyclerViewCAdapter(Context context, List<Item> data) {

        super(context, data);
    }

    public SuperRecyclerViewCAdapter(Context context, List<Item> data, FragmentManager fragmentManager) {
        super(context, data);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void convert(final BaseViewHolder holder, Item item, int position) {
        holder.setText(R.id.tv_recycler_item3_name, item.getItem1());
        holder.setText(R.id.tv_recycler_item3_rate, item.getItem2() + "%");
        if (item.getItem2() < 100 && item.getItem2() >= 95) {
            holder.setBackgroundColor(R.id.tv_recycler_item3_rate, Color.parseColor("#66CD00"));
        } else if (item.getItem2() < 95 && item.getItem2() >= 90) {
            holder.setBackgroundColor(R.id.tv_recycler_item3_rate, Color.parseColor("#7D26CD"));
        } else if (item.getItem2() < 90 && item.getItem2() >= 80) {
            holder.setBackgroundColor(R.id.tv_recycler_item3_rate, Color.parseColor("#FF8C00"));
        } else {
            holder.setBackgroundColor(R.id.tv_recycler_item3_rate, Color.parseColor("#FF0000"));
        }

        holder.getView(R.id.rl_recyc_item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentFactory.getFragmentInstance(fragmentManager, FragmentItem3Info.TAG);
            }
        });

    }

    @Override
    protected int getItemViewLayoutId(int position, Item item) {
        return R.layout.recycler_item3;
    }
}
