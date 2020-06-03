package com.example.wananzhuo.ui.wx;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.wananzhuo.Entity.WxTitleEntity;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.http.Injection;
import com.example.wananzhuo.ui.wx.wxarticle.WxNewsFragment;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: pengyam
 * Date: 2020/5/15
 * Time: 13:40
 */
public class WxPresenter extends BasePresenter<BaseModel, WxContract.IView> implements WxContract.IPresenter {
    WxTitleNavigatorAdapter navigatorAdapter;
    Context context;
    Fragment fragment;
    WxPagerAdapter wxPagerAdapter;

    public WxPresenter(WxContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void initNavigator(Context context) {
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdjustMode(false);
        navigatorAdapter = new WxTitleNavigatorAdapter();
        commonNavigator.setAdapter(navigatorAdapter);
        mRootView.setTab(commonNavigator);

        navigatorAdapter.setmOnItemClickListener(new WxTitleNavigatorAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int index) {
                mRootView.setItem(index);
            }
        });
    }

    public void initTab() {
        addSubscribe(Injection.PostApp(context).getWxarticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listCodeEntity -> {
                    if (listCodeEntity.getCode() != 0) {
                        Log.e("TAGH", listCodeEntity.getMsg());
                        return;
                    }
                    List<WxTitleEntity> list = new ArrayList<>();
                    list.addAll(listCodeEntity.getData());
                    navigatorAdapter.setData(list);
                    initPager();
                }, throwable -> {
                    Log.e("TAGH1", throwable.getMessage());
                    return;
                }));
    }

    public void initPager() {
        List<WxTitleEntity> list = navigatorAdapter.getAll();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            fragment = WxNewsFragment.getInstance(list.get(i).getId());
            fragments.add(fragment);
        }
        wxPagerAdapter.setData(fragments);
    }

    public void initAdapter(FragmentManager childFragmentManager) {
        wxPagerAdapter = new WxPagerAdapter(childFragmentManager);
        mRootView.setAdapter(wxPagerAdapter);
    }
}
