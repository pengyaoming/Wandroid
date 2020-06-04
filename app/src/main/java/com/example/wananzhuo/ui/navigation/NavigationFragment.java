package com.example.wananzhuo.ui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseFragment;
import com.example.wananzhuo.ui.mine.MineFragment;

import butterknife.BindView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:39
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.IView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.tv_title)
    TextView tv_title;


    public static Fragment getInstance() {
        NavigationFragment fragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("home", "导航");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_navigation, null, false);
    }

    @Override
    protected void initData() {
        mPresenter = new NavigationPresenter(this, getContext());
        img_left.setVisibility(View.GONE);
        tv_title.setText("导航");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.initAdapter();
        mPresenter.navigation();
    }

    @Override
    public void setAdapter(NavigationAdapter navigationAdapter) {
        recyclerView.setAdapter(navigationAdapter);
    }
}
