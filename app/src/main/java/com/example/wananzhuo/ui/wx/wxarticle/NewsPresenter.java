package com.example.wananzhuo.ui.wx.wxarticle;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.Entity.HomeEntity;
import com.example.wananzhuo.Entity.HomeListEntity;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.http.Injection;
import com.example.wananzhuo.ui.home.HomeAdapter;
import com.example.wananzhuo.ui.web.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/26
 * Time: 14:37
 */
public class NewsPresenter extends BasePresenter<BaseModel, NewsContract.IView> implements NewsContract.IPresenter {
    Context context;
    HomeAdapter homeAdapter;
    int size = 0;//加载次数
    int page = 0;//加载条数
    int id;
    List<HomeEntity> list = new ArrayList<>();

    public NewsPresenter(NewsContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void initNews() {
        homeAdapter = new HomeAdapter();
        mRootView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", homeAdapter.getItem(position).getLink());
            intent.putExtra("title", homeAdapter.getItem(position).getTitle());
            mRootView.luanchActivity(intent);
        });
    }


    public void setNews(int id) {
        this.id = id;
        initList();
    }

    public void setSwipe() {
        List<HomeEntity> list = new ArrayList<>();
        size = 0;
        //刷新adapter
        homeAdapter.setList(list);
        initListWx();
    }

    private void initListWx() {
        homeAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                homeAdapter.getLoadMoreModule().setEnableLoadMore(true);
                int HOME_SIZE = 0;
                if (page != 0) {
                    HOME_SIZE = page + 1;
                }
                if (page > HOME_SIZE) {
                    homeAdapter.getLoadMoreModule().loadMoreEnd();//数据不足20条
                } else {
                    size++;
                    initList();
                    homeAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }
        });
        homeAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    public void initList() {
        if (size <= 0) {
            list.clear();
        }
        addSubscribe(Injection.PostApp(context).getWxList(id, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(homeListEntityCodeEntity -> {
                    if (homeListEntityCodeEntity.getCode() != 0) {
                        Log.e("TAG", homeListEntityCodeEntity.getMsg());
                    }
                    list.addAll(homeListEntityCodeEntity.getData().getDatas());
                    homeAdapter.addData(list);
                    page = page + list.size();
                }, throwable -> Log.e("TAG", throwable.getMessage())));
    }
}
