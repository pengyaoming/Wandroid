package com.example.wananzhuo.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IView {
    @BindView(R.id.magicIndicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.top_view)
    View top_view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter.initAdapter(getSupportFragmentManager());
        mPresenter.initTab(this);
    }

    @Override
    public void initData() {
        ImmersionBar.with(this)
                .titleBar(top_view) //指定标题栏view
                .statusBarColor(R.color.main_color)
                .init();
    }

    @Override
    public void initPresenter() {
        mPresenter = new MainPresenter(this);
    }


    @Override
    public void setPagerView(MainPagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void setTab(CommonNavigator commonNavigator) {
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void setItem(int index) {
        viewPager.setCurrentItem(index);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}