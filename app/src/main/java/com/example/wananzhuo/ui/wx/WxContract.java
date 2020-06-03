package com.example.wananzhuo.ui.wx;

import com.example.wananzhuo.base.BaseView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:40
 */
public class WxContract {
    interface IView extends BaseView {

        void setTab(CommonNavigator commonNavigator);

        void setItem(int index);

        void setAdapter(WxPagerAdapter wxPagerAdapter);
    }

    interface IPresenter {

    }
}
