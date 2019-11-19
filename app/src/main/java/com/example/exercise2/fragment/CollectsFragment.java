package com.example.exercise2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise2.BaseApp;
import com.example.exercise2.R;
import com.example.exercise2.adapter.RecyAdapter;
import com.example.exercise2.bean.RecentBean;
import com.example.xts.greendaodemo.db.RecentBeanDao;

import java.util.List;

public class CollectsFragment extends Fragment {
    private View view;
    private RecyclerView mCollectsRecy;
    private RecentBeanDao recentBeanDao;
    private RecyAdapter recyAdapter;
    private boolean mIsVisibleToUser;
    private boolean mViewCreated;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collectsfragment, null);
        initView(view);
        recentBeanDao = BaseApp.getInstance().getDaoSession().getRecentBeanDao();
        mViewCreated = true;
        initRecy();
        return view;
    }

    private void initData() {
        if (mViewCreated && mIsVisibleToUser) {
            final List<RecentBean> recentBeans = recentBeanDao.loadAll();
            recyAdapter = new RecyAdapter(getActivity(), recentBeans);
            mCollectsRecy.setAdapter(recyAdapter);

            recyAdapter.setOnClick(new RecyAdapter.OnClick() {
                @Override
                public void onItemClick(int postison) {
                    recentBeanDao.delete(recyAdapter.getRecent().get(postison));
                    recyAdapter.getRecent().remove(postison);
                    recyAdapter.notifyDataSetChanged();
                }

                @Override
                public void onItemLong(int postion) {

                }
            });
        }
    }

    private void initRecy() {
        mCollectsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCollectsRecy.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initData();
    }

    private void initView(View view) {
        mCollectsRecy = (RecyclerView) view.findViewById(R.id.collects_recy);
    }
}
