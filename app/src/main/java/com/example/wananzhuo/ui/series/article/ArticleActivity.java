package com.example.wananzhuo.ui.series.article;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.OnClickUtils;
import com.example.wananzhuo.base.BaseActivity;
import com.example.wananzhuo.ui.home.HomeAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/2
 * Time: 9:50
 * Name: 作用类：体系下的文章
 */
public class ArticleActivity extends BaseActivity<ArticlePresenter> implements ArticleContract.IView {
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.tv_title)
    TextView textView;
    String title;
    int id;

    @OnClick(R.id.img_left)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                if (OnClickUtils.isFastClick()) {
                    finish();
                }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getIntExtra("id", 0);
        textView.setText(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.initAdapter();
        mPresenter.setList(id);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.homeAdapter.getLoadMoreModule().setEnableLoadMore(false);
                        mPresenter.initList();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });
        mPresenter.Loading();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new ArticlePresenter(this, this);

    }

    @Override
    public void setAdapter(HomeAdapter homeAdapter) {
        recyclerView.setAdapter(homeAdapter);
    }
}
