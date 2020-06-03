package com.example.wananzhuo.ui.wx;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wananzhuo.Entity.WxTitleEntity;
import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseFragment;
import com.example.wananzhuo.ui.wx.wxarticle.WxNewsFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:39
 */
public class WxFragment extends BaseFragment<WxPresenter> implements WxContract.IView {
    @BindView(R.id.magicIndicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public static Fragment getInstance() {
        WxFragment fragment = new WxFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Wx", "公众号");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_wx, null, false);
    }

    @Override
    protected void initData() {
        mPresenter = new WxPresenter(this, getContext());
        mPresenter.initNavigator(getContext());
        mPresenter.initTab();
        mPresenter.initAdapter(getChildFragmentManager());
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
    public void setAdapter(WxPagerAdapter wxPagerAdapter) {
        viewPager.setAdapter(wxPagerAdapter);
    }
}
