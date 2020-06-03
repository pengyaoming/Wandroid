package com.example.wananzhuo.ui.home.seek;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.Entity.HomeEntity;
import com.example.wananzhuo.Entity.HomeListEntity;
import com.example.wananzhuo.Entity.HotEntity;
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
 * Date: 2020/5/25
 * Time: 13:51
 */
public class SeekPresenter extends BasePresenter<BaseModel, SeekContract.IView> implements SeekContract.IPresenter {
    Context context;
    HotAdapter mHotAdapter;
    HomeAdapter mHomeAdapter;
    int page = 0;
    int size = 0;
    String name;
    List<HomeEntity> list = new ArrayList<>();

    public SeekPresenter(SeekContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }


    //获取搜索热词
    public void initFlow() {
        mHotAdapter = new HotAdapter();
        mHotAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String name = mHotAdapter.getItem(position).getName();
                mRootView.setEdit(name);

            }
        });
        mRootView.setFlow(mHotAdapter);
    }

    public void getHotKey() {
        addSubscribe(Injection.PostApp(context)
                .getHotKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCodeEntity -> {
                    if (listCodeEntity.getCode() != 0) {
                        Log.e("TAGH", listCodeEntity.getMsg());
                        return;
                    }
                    List<HotEntity> hotEntities = new ArrayList<>();
                    hotEntities.addAll(listCodeEntity.getData());
                    mHotAdapter.addData(hotEntities);
                }, throwable -> Log.e("TAGH", throwable.getMessage())));
    }

    public void initSeek() {
        mHomeAdapter = new HomeAdapter();
        mRootView.setHomeAdapter(mHomeAdapter);
        mHomeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("title", mHomeAdapter.getItem(position).getTitle());
            intent.putExtra("url", mHomeAdapter.getItem(position).getLink());
            mRootView.luanchActivity(intent);
        });

        mHomeAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> {
            mHomeAdapter.getLoadMoreModule().setEnableLoadMore(true);
            int HOME_SIZE = 0;
            if (page != 0) {
                HOME_SIZE = page + 1;

            }
            if (page > HOME_SIZE) {
                mHomeAdapter.getLoadMoreModule().loadMoreEnd();
            } else {
                size++;
                getSeek(name);
                //不加这段代码，只能加载一次，不会关闭加载，导致第二次加载出现无法刷新
                mHomeAdapter.getLoadMoreModule().loadMoreComplete();
            }
        });
        //不加这段代码，就会无限制刷新
        mHomeAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);

    }

    public void getSeek(String title) {
        if (name != null && !name.equals(title)) {
            List<HomeEntity> list = new ArrayList<>();
            mHomeAdapter.setList(list);
        }
        name = title;
        addSubscribe(Injection.PostApp(context).geSeek(size, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(homeListEntityCodeEntity -> {
                    if (homeListEntityCodeEntity.getCode() != 0) {
                        Log.e("TAGH", homeListEntityCodeEntity.getMsg());
                        return;
                    }
                    mHomeAdapter.addData(homeListEntityCodeEntity.getData().getDatas());
                    page = page + homeListEntityCodeEntity.getData().getDatas().size();
                }, throwable -> Log.e("TAGH", throwable.getMessage())));
    }
}
