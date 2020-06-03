package com.example.wananzhuo.ui.wx;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/26
 * Time: 9:45
 */
public class WxPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> list = new ArrayList<>();

    public WxPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    public void setData(List<Fragment> list) {
        this.list = list;
        //viewpager加入数据一定要not一下，不然直接挂掉
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return WxPagerAdapter.POSITION_NONE;
    }
}
