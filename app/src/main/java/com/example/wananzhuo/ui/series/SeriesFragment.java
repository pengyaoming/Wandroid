package com.example.wananzhuo.ui.series;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.SmoothMove;
import com.example.wananzhuo.Uilt.TopSmoothScroller;
import com.example.wananzhuo.base.BaseFragment;
import com.example.wananzhuo.ui.navigation.NavigationFragment;

import butterknife.BindView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:39
 * name:体系
 */
public class SeriesFragment extends BaseFragment<SeriesPresenter> implements SeriesContract.IView {
    @BindView(R.id.recy_tuo)
    RecyclerView recy_tuo;
    @BindView(R.id.RecyclerView)
    RecyclerView RecyclerView;
    private TopSmoothScroller mTopScroller;
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.tv_title)
    TextView tv_title;

    public static Fragment getInstance() {
        SeriesFragment fragment = new SeriesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("home", "我的");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_series, null, false);
    }

    @Override
    protected void initData() {
        mPresenter = new SeriesPresenter(this, getContext());
//        mTopScroller = new TopSmoothScroller(getContext());
        img_left.setVisibility(View.GONE);
        tv_title.setText("体系");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        linearLayoutManager.startSmoothScroll(mTopScroller);
        recy_tuo.setLayoutManager(linearLayoutManager);
        mPresenter.initAdapter();
        mPresenter.initList();

    }

    @Override
    public void setAdapter(SeriesTitleAdapter seriesTitleAdapter, SeriesMessageAdapter seriesMessageAdapter) {
        recy_tuo.setAdapter(seriesMessageAdapter);
        RecyclerView.setAdapter(seriesTitleAdapter);
    }

    @Override
    public void getItem(int position) {
//        mTopScroller.setTargetPosition(position);
        recy_tuo.smoothScrollToPosition(position);
    }


}
