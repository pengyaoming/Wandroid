package com.example.wananzhuo.base;

import androidx.fragment.app.FragmentManager;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/14
 * Time: 14:03
 */
public abstract class BasePresenter<M, V extends BaseView> implements IPresenter {

    protected final String TAG;

    protected M mModel;

    protected V mRootView;
    //弱引用持有
    private WeakReference<LifecycleProvider> lifecycle;

    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
        TAG = this.getClass().getSimpleName();
        mCompositeDisposable = new CompositeDisposable();
    }

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
        TAG = this.getClass().getSimpleName();
    }

    public BasePresenter() {
        onStart();
        TAG = this.getClass().getSimpleName();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        this.mModel = null;
        this.mRootView = null;
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    public void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = new WeakReference<>(lifecycle);
    }

    public LifecycleProvider getLifecycleProvider() {
        return lifecycle.get();
    }


}
