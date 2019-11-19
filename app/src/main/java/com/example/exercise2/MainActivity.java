package com.example.exercise2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.exercise2.adapter.MainPagerAdapter;
import com.example.exercise2.fragment.CollectsFragment;
import com.example.exercise2.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private TabLayout mTab;
    private ArrayList<Fragment> fragments;
    private MainPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPager();
        initTab();
    }

    private void initTab() {
        mTab.setupWithViewPager(mPager);
        mTab.getTabAt(0).setText("我的");
        mTab.getTabAt(1).setText("收藏");
    }

    private void initPager() {
        fragments = new ArrayList<>();
        fragments.add(new MyFragment());
        fragments.add(new CollectsFragment());
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mainPagerAdapter);
    }

    private void initView() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mTab = (TabLayout) findViewById(R.id.tab);
    }
}
