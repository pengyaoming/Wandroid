package com.example.wananzhuo.ui.wx.wxarticle;

import com.example.wananzhuo.base.BaseView;
import com.example.wananzhuo.ui.home.HomeAdapter;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/26
 * Time: 14:36
 */
public class NewsContract {
    interface IView extends BaseView {

        void setAdapter(HomeAdapter homeAdapter);
    }

    interface IPresenter {

    }
}
