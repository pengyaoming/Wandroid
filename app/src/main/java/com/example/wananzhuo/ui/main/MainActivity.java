package com.example.wananzhuo.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.getPhotoFromPhotoAlbum;
import com.example.wananzhuo.base.BaseActivity;
import com.example.wananzhuo.ui.mine.MineFragment;
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
    int index = 0;

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
        initState(0);

    }

    private void initState(int position) {
        ImmersionBar.with(MainActivity.this)
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
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pagerAdapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path;
        if (requestCode == 2 && resultCode == RESULT_OK) {
            path = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
        }

    }
}
