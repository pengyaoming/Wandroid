package com.example.wananzhuo.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.ui.home.HomeFragment;
import com.example.wananzhuo.ui.mine.MineFragment;
import com.example.wananzhuo.ui.navigation.NavigationFragment;
import com.example.wananzhuo.ui.series.SeriesFragment;
import com.example.wananzhuo.ui.wx.WxFragment;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 9:33
 */
public class MainPresenter extends BasePresenter<BaseModel, MainContract.IView> implements MainContract.IPresenter {
    public MainPagerAdapter pagerAdapter;
    private MainNavigatorAdapter navigatorAdapter;
    List<Fragment> fragments = new ArrayList<>();

    public MainPresenter(MainContract.IView rootView) {
        super(rootView);
    }

    public void initAdapter(FragmentManager supportFragmentManager) {
        fragments.add(HomeFragment.getInstance());
        fragments.add(WxFragment.getInstance());
        fragments.add(SeriesFragment.getInstance());
        fragments.add(NavigationFragment.getInstance());
        fragments.add(MineFragment.getInstance());
        pagerAdapter = new MainPagerAdapter(supportFragmentManager, fragments);
        mRootView.setPagerView(pagerAdapter);
    }

    public void initTab(MainActivity mainActivity) {
        CommonNavigator commonNavigator = new CommonNavigator(mainActivity);
        commonNavigator.setAdjustMode(true);
        navigatorAdapter = new MainNavigatorAdapter();
        commonNavigator.setAdapter(navigatorAdapter);
        mRootView.setTab(commonNavigator);
        navigatorAdapter.setOnItemClickListener(index -> mRootView.setItem(index));
    }
}
