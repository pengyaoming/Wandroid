package com.example.wananzhuo.ui.mine;

import com.example.wananzhuo.base.BaseView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:40
 */
public class MineContract {
    interface IView extends BaseView {

        void setAdapter(MineAdapter mAdapter);
    }

    interface IPresenter {

    }
}
