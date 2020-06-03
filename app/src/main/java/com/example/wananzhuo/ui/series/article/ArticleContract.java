package com.example.wananzhuo.ui.series.article;

import com.example.wananzhuo.base.BaseView;
import com.example.wananzhuo.ui.home.HomeAdapter;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/2
 * Time: 9:51
 * Name: 作用类：
 */
public class ArticleContract {
    interface IView extends BaseView {
        void setAdapter(HomeAdapter homeAdapter);
    }

    interface IPresenter {

    }
}
