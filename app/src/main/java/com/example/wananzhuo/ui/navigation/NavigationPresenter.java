package com.example.wananzhuo.ui.navigation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.http.Injection;
import com.example.wananzhuo.ui.mine.MineContract;
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
public class NavigationPresenter extends BasePresenter<BaseModel, NavigationContract.IView> implements NavigationContract.IPresenter {
    Context context;
    NavigationAdapter navigationAdapter;
    List<NavigationEntity> list = new ArrayList<>();

    public NavigationPresenter(NavigationContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void initAdapter() {
        navigationAdapter = new NavigationAdapter(context);
        mRootView.setAdapter(navigationAdapter);
        navigationAdapter.setOnItemIcklist((link, title) -> {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("url", link);
            intent.putExtra("title", title);
            mRootView.luanchActivity(intent);
        });
    }

    public void navigation() {
        addSubscribe(Injection.PostApp(context)
                .getNavigation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeEntity<List<NavigationEntity>>>() {
                    @Override
                    public void accept(CodeEntity<List<NavigationEntity>> listCodeEntity) throws Exception {
                        if (listCodeEntity.getCode() != 0) {
                            ToastUtils.show(listCodeEntity.getMsg());
                            Log.e(TAG, listCodeEntity.getMsg());
                            return;
                        }
                        list.clear();
                        navigationAdapter.setList(list);
                        List<NavigationEntity> list1 = new ArrayList<>();
                        list1.addAll(listCodeEntity.getData());
                        navigationAdapter.addData(list1);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }
}
