package com.example.wananzhuo.ui.series.article;

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
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/2
 * Time: 9:52
 * Name: 作用类：
 */
public class ArticlePresenter extends BasePresenter<BaseModel, ArticleContract.IView> implements ArticleContract.IPresenter {
    Context context;
    HomeAdapter homeAdapter;
    int size = 0;
    int page = 0;
    int id;
    List<HomeEntity> list = new ArrayList<>();

    public ArticlePresenter(ArticleContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void initAdapter() {
        homeAdapter = new HomeAdapter();
        homeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("title", homeAdapter.getItem(position).getTitle());
                intent.putExtra("url", homeAdapter.getItem(position).getLink());
                mRootView.luanchActivity(intent);
            }
        });
        mRootView.setAdapter(homeAdapter);

    }

    public void initList() {
        List<HomeEntity> list = new ArrayList<>();
        list.clear();
        homeAdapter.setList(list);
        page = 0;
        initNext();
    }


    public void setList(int id) {
        this.id = id;
        initNext();
    }

    private void initNext() {
        if (page == 0) {
            list.clear();
        }
        addSubscribe(Injection.PostApp(context)
                .getList(page, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(homeListEntityCodeEntity -> {
                    if (homeListEntityCodeEntity.getCode() != 0) {
                        Log.e(TAG, homeListEntityCodeEntity.getMsg());
                        ToastUtils.show(homeListEntityCodeEntity.getMsg());
                        return;
                    }
                    list.addAll(homeListEntityCodeEntity.getData().getDatas());
                    homeAdapter.addData(list);
                    size = size + homeListEntityCodeEntity.getData().getDatas().size();
                }, throwable -> Log.e(TAG, throwable.getMessage())));
    }

    public void Loading() {
        homeAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                homeAdapter.getLoadMoreModule().setEnableLoadMore(true);
                int HOME_SIZE = 0;
                if (size != 0) {
                    HOME_SIZE = size + 1;
                }
                if (size > HOME_SIZE) {
                    homeAdapter.getLoadMoreModule().loadMoreEnd();
                } else {
                    page++;
                    initNext();
                    homeAdapter.getLoadMoreModule().loadMoreComplete();
                }
            }
        });
        homeAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }
}
