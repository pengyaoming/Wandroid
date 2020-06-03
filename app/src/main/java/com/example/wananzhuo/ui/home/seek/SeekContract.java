package com.example.wananzhuo.ui.home.seek;

import com.example.wananzhuo.base.BaseView;
import com.example.wananzhuo.ui.home.HomeAdapter;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/25
 * Time: 13:50
 */
public class SeekContract {
    public interface IView extends BaseView {

        void setFlow(HotAdapter mHotAdapter);

        void setHomeAdapter(HomeAdapter mHomeAdapter);

        void setEdit(String name);
    }

    interface IPresenter {

    }
}
