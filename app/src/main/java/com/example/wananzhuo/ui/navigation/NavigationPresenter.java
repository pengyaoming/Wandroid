package com.example.wananzhuo.ui.navigation;

import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.ui.mine.MineContract;

/**
 * Created by Android Studio.
 * User: pengyam
 * Date: 2020/5/15
 * Time: 13:40
 */
public class NavigationPresenter extends BasePresenter<BaseModel, NavigationContract.IView> implements NavigationContract.IPresenter {
    public NavigationPresenter(NavigationContract.IView rootView) {
        super(rootView);
    }
}
