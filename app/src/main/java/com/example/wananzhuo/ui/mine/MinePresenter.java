package com.example.wananzhuo.ui.mine;

import android.content.Context;

import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.ui.home.HomeContract;

/**
 * Created by Android Studio.
 * User: pengyam
 * Date: 2020/5/15
 * Time: 13:40
 */
public class MinePresenter extends BasePresenter<BaseModel, MineContract.IView> implements MineContract.IPresenter {
    Context context;

    public MinePresenter(MineContract.IView rootView, Context context) {
        super(rootView);
    }
}
