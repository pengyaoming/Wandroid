package com.example.wananzhuo.ui.mine.login;

import com.example.wananzhuo.Entity.LoginEntity;
import com.example.wananzhuo.base.BaseView;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/10
 * Time: 14:09
 * Name: 作用类：
 */
public class LoginContract {
    interface IVew extends BaseView {

        void setIntent(LoginEntity data);

        void setRegister(LoginEntity data);
    }

    interface IPresenter {

    }
}
