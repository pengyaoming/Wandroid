package com.example.wananzhuo.ui.wx.wxarticle;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseFragment;
import com.example.wananzhuo.ui.home.HomeAdapter;

import butterknife.BindView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/26
 * Time: 11:09
 */
public class WxNewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.IView {
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    int id;

    public static Fragment getInstance(int id) {
        WxNewsFragment fragment = new WxNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_wx_news, null, false);
    }

    @Override
    protected void initData() {
        mPresenter = new NewsPresenter(this, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.initNews();
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        Log.d("TAG", id + "");

        swipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            mPresenter.homeAdapter.getLoadMoreModule().setEnableLoadMore(false);
            mPresenter.initList();
            //关闭刷新
            swipeRefreshLayout.setRefreshing(false);
        }, 500));
        //上拉加载
        mPresenter.setSwipe();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setNews(id);
    }

    @Override
    public void setAdapter(HomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);
    }
}
