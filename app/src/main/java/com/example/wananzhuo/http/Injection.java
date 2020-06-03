package com.example.wananzhuo.http;

import android.content.Context;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 10:39
 */
public class Injection {

    public static HttpLoadDataSourceImpl PostApp(Context context) {
        ApiService apiServer = RetrofitUilt.getInstance(context).create(ApiService.class);
        return HttpLoadDataSourceImpl.getInstance(apiServer);
    }
}
