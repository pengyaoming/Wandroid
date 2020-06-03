package com.example.wananzhuo.ui.main;

import com.example.wananzhuo.base.BaseView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 9:33
 */
public class MainContract {
    public interface IView extends BaseView {

        void setPagerView(MainPagerAdapter pagerAdapter);

        void setTab(CommonNavigator commonNavigator);

        void setItem(int index);
    }

    public interface IPresenter {

    }
}
