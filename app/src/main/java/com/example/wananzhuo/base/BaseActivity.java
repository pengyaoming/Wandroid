package com.example.wananzhuo.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wananzhuo.Uilt.LoadingDialog;
import com.example.wananzhuo.ViewOpt;
import com.guosen.emm.viewoptapi.IViewCreator;
import com.hjq.toast.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/14
 * Time: 14:03
 */
public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView {
    private static final String TAG = BaseActivity.class.getSimpleName();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public abstract void initPresenter();

    protected P mPresenter;
    private Context mContext;
    private boolean isActive = false;
    private CompositeDisposable mCompositeDisposable;
    protected LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //修复安装点击完成后再home退出点击图标进入产生两个栈的BUG
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent
                    .ACTION_MAIN)) {
                finish();
                return;
            }
        }
        setContentView(getLayoutId());
        this.mContext = this;
        ButterKnife.bind(this);
        initPresenter();
        initView();
        initData();
        //注入Rxlifecyle生命周期
        //  mPresenter.injectLifecycleProvider(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = ViewOpt.createView(name, context, attrs);
        if (view != null) {
            return view;
        }
        return super.onCreateView(name, context, attrs);

    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log("onDestroy");
        if (mPresenter != null) {
            //  mPresenter.onDestroy();
        }
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }


    protected void log(String message) {
        Log.d(TAG, getClass().getSimpleName() + "--->" + message);
    }

    @Override
    public void showLoading(String message) {
        LoadingDialog.Builder builder = new LoadingDialog.Builder(getContext())
                .setMessage(message)
                .setCancelOutside(true)
                .setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showLoding(int strId) {
        LoadingDialog.Builder builder = new LoadingDialog.Builder(getContext())
                .setMessage(getString(strId))
                .setCancelOutside(true)
                .setCancelable(true);
        dialog = builder.create();
        dialog.show();
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
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart");
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        log("onStop");
        isActive = false;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * 判断是否有网络
     *
     * @param ev
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
//mNetworkInfo.isAvailable();
                return true;//有网
            }
        }
        return false;//没有网
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View view = getCurrentFocus();
//            if (isHideInput(view, ev)) {
//                HideSoftInput(view.getWindowToken());
//                view.clearFocus();
//            }
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
