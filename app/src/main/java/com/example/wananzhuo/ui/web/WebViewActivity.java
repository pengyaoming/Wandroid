package com.example.wananzhuo.ui.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.NetworkUtils;
import com.example.wananzhuo.Uilt.OnClickUtils;
import com.example.wananzhuo.base.BaseActivity;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/20
 * Time: 9:24
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.linear)
    LinearLayout linearLayout;
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    WebView mWebView;
    private WebSettings mWebSetting;
    @BindView(R.id.top_view)
    View top_view;
    @OnClick({R.id.img_left})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                if (OnClickUtils.isFastClick()) {
                    if (mWebView == null) {
                        finish();
                    } else {
                        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                        mWebView.clearHistory();
                        ((ViewGroup) mWebView.getParent()).removeView(mWebView);
                        mWebView.destroy();
                        mWebView = null;
                        finish();
                    }
                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        //获取Url
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        //把WebView动态加载到LinearLayout中
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(params);
        linearLayout.addView(mWebView);
        mWebView.loadUrl(url);
        mWebSetting = mWebView.getSettings();
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebSetting.setUseWideViewPort(true);//将图片调整到适合webview 大小
        mWebSetting.setLoadWithOverviewMode(true);//缩放至屏幕大小
        mWebSetting.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过js打开窗口
        mWebSetting.setLoadsImagesAutomatically(true);//支持自动加载图片
        mWebSetting.setDefaultTextEncodingName("utf-8");//设置编码格式
//        复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        ImmersionBar.with(this)
                .titleBar(top_view) //指定标题栏view
                .statusBarColor(R.color.main_color)
                .init();
        //设置WebChromeClient类
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d("TAG", title);
                if (title.length() > 10) {
                    tv_title.setText(title);
                } else {
                    tv_title.setText(title + "");
                }

            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showLoading("请稍后");
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                hideLoading();
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d("TAGH112", errorCode + "");
                Log.d("TAGH112", failingUrl + "");
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().startsWith("http:") || request.getUrl().toString().startsWith("https:")) {
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("TAG", "ERROR1 : " + e.toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (url.startsWith("bilibili:")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
                mWebView.loadUrl(url);
                return false;
            }
        });

    }


    @Override
    public void initData() {
        //webview加载不出图片，因为图片使用的是http，自从Android5.0之后，webview默认不能http和https混合使用，需要设置就行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (NetworkUtils.iConnected(this)) {
            //网络已连接
            mWebSetting.setCacheMode(mWebSetting.LOAD_DEFAULT);//根据chche-control决定是否从网络上获取数据
        } else {
            mWebSetting.setCacheMode(mWebSetting.LOAD_CACHE_ELSE_NETWORK);//美网，从本地获取，及离线加载
        }
        mWebSetting.setDomStorageEnabled(true); //开启 DOM storage API功能
        mWebSetting.setDatabaseEnabled(true); //开启database storage API功能
        mWebSetting.setAppCacheEnabled(true);//开启application Cache功能
        String cache = getFilesDir().getAbsolutePath() + "webActivity";
        mWebSetting.setAppCachePath(cache);
    }

    @Override
    public void initPresenter() {
    }


    //点击返回上一页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断WebView内多层wenView嵌套情况下，点击返回直接退出webView情况，监听问手机系统返回监听时间
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
            //支持JavaScript
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWebView != null) {
            mWebView.pauseTimers();
            //禁止支持javaScript
            mWebSetting.setJavaScriptEnabled(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWebView != null) {
            mWebView.resumeTimers();

        }
    }

    @Override
    protected void onDestroy() {
        //安全退出webView，先给webView 一个空白页面，再关闭webView页面，最后删除webView
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        ImmersionBar.with(this).destroy();
        super.onDestroy();
    }
}
