package com.example.wananzhuo.base;

import android.content.Intent;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/14
 * Time: 14:03
 */
public interface BaseView {
    //显示加载
    void showLoading(String message);

    //显示加载
    void showLoding(int strId);

    //隐藏加载
    void hideLoading();

    //显示信息
    void showMessage(String message);

    //显示资源信息
    void showMessage(int messageId);

    //跳转Activity
    void luanchActivity(Intent intent);

    //杀死自己
    void KillMyself();

    //是否活动状态
    boolean isActive();

}
