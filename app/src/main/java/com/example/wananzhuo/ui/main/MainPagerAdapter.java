package com.example.wananzhuo.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 11:39
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public MainPagerAdapter( FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        this.fragments = fragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
