package com.example.wananzhuo.ui.mine.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.Entity.LoginEntity;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.http.Injection;
import com.hjq.toast.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/10
 * Time: 14:09
 * Name: 作用类：
 */
public class LoginPresenter extends BasePresenter<BaseModel, LoginContract.IVew> implements LoginContract.IPresenter {
    private Context context;

    public LoginPresenter(LoginContract.IVew rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void getLogin(String username, String password) {
        addSubscribe(Injection.PostApp(context).SetLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeEntity<LoginEntity>>() {
                    @Override
                    public void accept(CodeEntity<LoginEntity> loginEntityCodeEntity) throws Exception {
                        if (loginEntityCodeEntity.getCode() != 0) {
                            ToastUtils.show(loginEntityCodeEntity.getMsg());
                            Log.e(TAG, loginEntityCodeEntity.getMsg());
                            return;
                        }
                        mRootView.setIntent(loginEntityCodeEntity.getData());


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, throwable.getMessage());

                    }
                }));
    }

    public void getRegister(String username, String password, String password_1) {
        addSubscribe(Injection.PostApp(context).SetRegister(username, password, password_1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeEntity<LoginEntity>>() {
                    @Override
                    public void accept(CodeEntity<LoginEntity> loginEntityCodeEntity) throws Exception {
                        if (loginEntityCodeEntity.getCode() != 0) {
                            ToastUtils.show(loginEntityCodeEntity.getMsg());
                            Log.e(TAG, loginEntityCodeEntity.getMsg());
                            return;
                        }
                        mRootView.setRegister(loginEntityCodeEntity.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }
}
