package com.example.wananzhuo.ui.series;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.http.Injection;
import com.example.wananzhuo.ui.navigation.NavigationContract;
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
public class SeriesPresenter extends BasePresenter<BaseModel, SeriesContract.IView> implements SeriesContract.IPresenter {
    Context context;
    SeriesTitleAdapter seriesTitleAdapter;//进度条
    SeriesMessageAdapter seriesMessageAdapter;//recy

    public SeriesPresenter(SeriesContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }


    public void initAdapter() {
        seriesTitleAdapter = new SeriesTitleAdapter();

        seriesMessageAdapter = new SeriesMessageAdapter(context);
        mRootView.setAdapter(seriesTitleAdapter, seriesMessageAdapter);
        seriesTitleAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                mRootView.getItem(position);
            }
        });
    }

    public void initList() {
        addSubscribe(Injection
                .PostApp(context).getSeries().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCodeEntity -> {
                    if (listCodeEntity.getCode() != 0) {
                        ToastUtils.show(listCodeEntity.getMsg());
                        Log.e(TAG, listCodeEntity.getMsg());
                        return;
                    }
                    List<SeriesEntity> list = new ArrayList<>();
                    list.addAll(listCodeEntity.getData());
                    seriesTitleAdapter.addData(list);
                    seriesMessageAdapter.addData(list);
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());
                    }
                }));
    }
}
