package com.example.wananzhuo.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wananzhuo.Entity.DataBean;
import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseFragment;
import com.example.wananzhuo.ui.home.seek.SeekActivity;
import com.example.wananzhuo.ui.web.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.transformer.AlphaPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:39
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.img_right)
    ImageView img_right;
    @BindView(R.id.relative)
    RelativeLayout relative;
    Banner banner;
    ImageAdapter imageAdapter;
    List<DataBean> list = new ArrayList<>();

    public static Fragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("home", "首页");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null, false);
    }

    @OnClick({R.id.img_right})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_right:
//                int offsetX = SizeUtils.dp2px(20) - view.getWidth() / 2;
//                int offsetY = (relative.getHeight() - view.getHeight()) / 2;
//                mPopup.showAtAnchorView(view, YGravity.BELOW, XGravity.ALIGN_RIGHT, offsetX, offsetY);
                Intent intent = new Intent(getContext(), SeekActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initData() {
        if (mPresenter == null) {
            mPresenter = new HomePresenter(this, getContext());
        }
        img_left.setVisibility(View.GONE);
        tv_title.setText(getString(R.string.home_title));
        img_right.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mPresenter.initAdapter();
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK, Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE);
        initHadler();
        mPresenter.initHomeList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.mAdapter.getLoadMoreModule().setEnableLoadMore(false);
                        mPresenter.getNewList();//请求数据
                        swipeRefreshLayout.setRefreshing(false);//关闭刷新
                    }
                }, 500);
            }
        });
        //下拉刷新
        mPresenter.initIsListHome();
//        initPopup();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (recyclerView.getAdapter() == null) {
            Log.e(TAG, "dispatchLayout");

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recyclerView.getAdapter() == null) {
            Log.e(TAG, "dispatchLayout");
        }
    }

    private void initHadler() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_banner, (ViewGroup) recyclerView.getParent(), false);
        banner = view.findViewById(R.id.banner);
        imageAdapter = new ImageAdapter(list, getContext());
        banner.setAdapter(imageAdapter);
        banner.setBannerGalleryEffect(18, 10);
        mPresenter.mAdapter.addHeaderView(view);

    }

    @Override
    public void setAdapter(HomeAdapter mAdapter) {
        recyclerView.setAdapter(mAdapter);
        if (mAdapter == null) {
            mAdapter = new HomeAdapter();
            recyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void setBanner(List<DataBean> data) {
        list.clear();
        list.addAll(data);
        imageAdapter.setDatas(list);
        imageAdapter.notifyDataSetChanged();
        //banner点击事件
        banner.setOnBannerListener((data1, position) -> {
            Log.d("TAGH", position + "");
            Intent intent = new Intent(HomeFragment.this.getContext(), WebViewActivity.class);
            intent.putExtra("url", imageAdapter.getData(position).getUrl());
            intent.putExtra("title", imageAdapter.getData(position).getTitle());
            HomeFragment.this.startActivity(intent);
        });
    }
}
