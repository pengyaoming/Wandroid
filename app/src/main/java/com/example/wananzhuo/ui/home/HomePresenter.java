package com.example.wananzhuo.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.Entity.DataBean;
import com.example.wananzhuo.Entity.HomeEntity;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.http.Injection;
import com.example.wananzhuo.ui.web.WebViewActivity;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: pengyam
 * Date: 2020/5/15
 * Time: 13:40
 */
public class HomePresenter extends BasePresenter<BaseModel, HomeContract.IView> implements HomeContract.IPresenter {
    public HomeAdapter mAdapter;
    private int size = 0;
    private List<HomeEntity> list = new ArrayList<>();
    private Context context;
    public int page = 0;

    public HomePresenter(HomeContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void initAdapter() {
        mAdapter = new HomeAdapter();
        mRootView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Log.d("TAGH", position + "");
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", mAdapter.getItem(position).getLink());
            intent.putExtra("title", mAdapter.getItem(position).getTitle());
            mRootView.luanchActivity(intent);
        });
    }

    /**
     * 获取首页数据
     */
    public void initHomeList() {
        if (size <= 0) {
            list.clear();
            initBanner();
            initTopList();
        }

        addSubscribe(Injection.PostApp(context).getHomeList(size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(homeListEntityCodeEntity -> {
                    if (homeListEntityCodeEntity.getCode() != 0) {
                        ToastUtils.show(homeListEntityCodeEntity.getMsg());
                        return;
                    }
                    mAdapter.addData(homeListEntityCodeEntity.getData().getDatas());
                    page = page + homeListEntityCodeEntity.getData().getDatas().size();
                }, throwable -> Log.d("WAZ", throwable.getMessage())));
    }

    public void getNewList() {
        List<HomeEntity> list = new ArrayList<>();
        size = 0;
        mAdapter.setList(list);
        initHomeList();
    }

    public void initIsListHome() {
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            mAdapter.getLoadMoreModule().setEnableLoadMore(true);
            int HOME_SIZE = 0;
            if (page != 0) {
                HOME_SIZE = page + 1;
            }
            if (page > HOME_SIZE) {
                mAdapter.getLoadMoreModule().loadMoreEnd();//数据不足20条，说明已经到尾部，显示没有更多数据
            } else {
                size++;
                initHomeList();
                mAdapter.getLoadMoreModule().loadMoreComplete();
            }
        });
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    //获取banner图片
    public void initBanner() {
        addSubscribe(Injection.PostApp(context).getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCodeEntity -> {
                    if (listCodeEntity.getCode() != 0) {
                        ToastUtils.show(listCodeEntity.getMsg());
                        return;
                    }
                    mRootView.setBanner(listCodeEntity.getData());
                }, throwable -> {
                    Log.d("TAGH", throwable.getMessage());
                }));
    }

    //获取置顶文章
    public void initTopList() {
        addSubscribe(Injection.PostApp(context).getTopList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCodeEntity -> {
                    if (listCodeEntity.getCode() != 0) {
                        ToastUtils.show(listCodeEntity.getMsg());
                        return;
                    }
                    mAdapter.addData(listCodeEntity.getData());
                }, throwable -> Log.d("TAGH", throwable.getMessage())));

    }
}
