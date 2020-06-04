package com.example.wananzhuo.ui.navigation;

import com.example.wananzhuo.base.BaseView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:40
 */
public class NavigationContract {
    interface IView extends BaseView {

        void setAdapter(NavigationAdapter navigationAdapter);
    }

    interface IPresenter {

    }
}
