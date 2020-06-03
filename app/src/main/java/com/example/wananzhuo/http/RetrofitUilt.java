package com.example.wananzhuo.http;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.example.wananzhuo.base.BasePresenter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 9:41
 */
public class RetrofitUilt {
    private Context context;
    private static RetrofitUilt instance;
    private Retrofit retrofit;

    private String Url;
    private OkHttpClient okHttpClient;


    public static RetrofitUilt getInstance(Context context) {
        if (instance == null) {
            synchronized (RetrofitUilt.class) {
                if (instance == null) {
                    instance = new RetrofitUilt(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private RetrofitUilt(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new SaveCookiesInterceptor())
                .retryOnConnectionFailure(true)
                .cookieJar(new CookieManger(context));
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("cookie", setCookie())
//                                .addHeader("Connecti
//                                on", "close")
//                                .build();
//                        return chain.proceed(request);
//                    }
//                });
        builder.addInterceptor(loggingInterceptor);
        okHttpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public String setCookie() {
        return SPUtils.getInstance("cookie").getString("cookie");
    }

    public <T> T create(Class<T> cls) {
        if (retrofit == null) {
            throw new NullPointerException(getClass().getSimpleName());
        }
        return retrofit.create(cls);
    }

}
