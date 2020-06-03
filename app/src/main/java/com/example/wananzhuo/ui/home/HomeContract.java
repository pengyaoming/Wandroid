package com.example.wananzhuo.ui.home;

import com.example.wananzhuo.Entity.DataBean;
import com.example.wananzhuo.base.BaseView;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:40
 */
public class HomeContract {
    interface IView extends BaseView {
        void setAdapter(HomeAdapter mAdapter);

        void setBanner(List<DataBean> data);
    }

    interface IPresenter {
    }
}
