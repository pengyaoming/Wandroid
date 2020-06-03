package com.example.wananzhuo.ui.series;

import com.example.wananzhuo.base.BaseView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:40
 */
public class SeriesContract {
    interface IView extends BaseView {

        void setAdapter(SeriesTitleAdapter seriesTitleAdapter, SeriesMessageAdapter seriesMessageAdapter);

        void getItem(int position);
    }

    interface IPresenter {

    }
}
