package com.example.exercise2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise2.BaseApp;
import com.example.exercise2.R;
import com.example.exercise2.WebActivity;
import com.example.exercise2.adapter.RecyAdapter;
import com.example.exercise2.bean.MyBean;
import com.example.exercise2.bean.RecentBean;
import com.example.exercise2.present.MyPresenter;
import com.example.exercise2.view.MyView;
import com.example.xts.greendaodemo.db.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment implements MyView {
    private View view;
    private RecyclerView mRecy;
    private List<RecentBean> recent;
    private RecyAdapter recyAdapter;
    private MyPresenter myPresenter;
    private PopupWindow popupWindow;
    private RecentBeanDao recentBeanDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment, null);
        initView(view);
        myPresenter = new MyPresenter(this);
        recentBeanDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        initRecy();
        initData();
        return view;
    }

    private void initData() {
        myPresenter.getData();
    }

    private void initRecy() {
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecy.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recent = new ArrayList<>();
        recyAdapter = new RecyAdapter(getActivity(), recent);
        mRecy.setAdapter(recyAdapter);

        recyAdapter.setOnClick(new RecyAdapter.OnClick() {
            @Override
            public void onItemClick(int postison) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",recyAdapter.getRecent().get(postison).getUrl());
                startActivity(intent);
            }

            @Override
            public void onItemLong(final int postion) {
                View view = View.inflate(getActivity(), R.layout.pop, null);
                Button no = view.findViewById(R.id.no);
                Button ok = view.findViewById(R.id.ok);
                final RelativeLayout rela = view.findViewById(R.id.rela);
                popupWindow = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                if (!popupWindow.isShowing()){
                    popupWindow.showAtLocation(mRecy, Gravity.CENTER,0,0);
                }
                rela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recentBeanDao.insertOrReplace(recyAdapter.getRecent().get(postion));
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
    }

    @Override
    public void setData(MyBean bean) {
        recyAdapter.setRecent(bean.getRecent());
        recyAdapter.notifyDataSetChanged();
    }
}
