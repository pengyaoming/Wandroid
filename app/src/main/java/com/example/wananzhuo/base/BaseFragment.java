package com.example.wananzhuo.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.wananzhuo.Uilt.LoadingDialog;
import com.hjq.toast.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/14
 * Time: 14:26
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseView {

    protected BaseActivity mActivity;
    protected View mRootView;
    protected P mPresenter;
    protected final String TAG = this.getClass().getSimpleName();
    private String mPageName;
    private boolean isActive;
    protected LoadingDialog dialog;
    protected CompositeDisposable mCompositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mPageName = getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView();
        Log.d(TAG, "onCreateView");
        ButterKnife.bind(this, mRootView);// 绑定到butterknife
        return mRootView;
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        mActivity = (BaseActivity) getActivity();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    protected abstract View initView();

    protected abstract void initData();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();// 释放资源
    }

    @Override
    public void showLoading(String message) {
        LoadingDialog.Builder builder = new LoadingDialog.Builder(getContext())
                .setMessage(message)
                .setCancelOutside(false)
                .setCancelable(false);
        dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showLoding(int strId) {

    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.show(message);
    }

    @Override
    public void showMessage(int messageId) {
        ToastUtils.show(messageId);
    }

    @Override
    public void luanchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void KillMyself() {
        mActivity.finish();
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        isActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        isActive = false;
    }
}
