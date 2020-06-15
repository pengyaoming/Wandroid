package com.example.wananzhuo.http;

import com.example.wananzhuo.Entity.CodeEntity;
import com.example.wananzhuo.Entity.DataBean;
import com.example.wananzhuo.Entity.HomeEntity;
import com.example.wananzhuo.Entity.HomeListEntity;
import com.example.wananzhuo.Entity.HotEntity;
import com.example.wananzhuo.Entity.LoginEntity;
import com.example.wananzhuo.Entity.WxListEntity;
import com.example.wananzhuo.Entity.WxTitleEntity;
import com.example.wananzhuo.ui.navigation.NavigationEntity;
import com.example.wananzhuo.ui.series.SeriesEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 10:37
 */
public class HttpLoadDataSourceImpl implements HttpDataSource {
    private ApiService apiServer;
    private volatile static HttpLoadDataSourceImpl INSTANCE = null;

    public static HttpLoadDataSourceImpl getInstance(ApiService apiServer) {
        if (INSTANCE == null) {
            synchronized (HttpLoadDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpLoadDataSourceImpl(apiServer);
                }
            }
        }
        return INSTANCE;
    }

    private HttpLoadDataSourceImpl(ApiService apiServer) {
        this.apiServer = apiServer;
    }

    @Override
    public Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getHomeList(int size) {
        return apiServer.getHomeList(size);
    }

    @Override
    public Observable<CodeEntity<List<DataBean>>> getBanner() {
        return apiServer.getBanner();
    }

    @Override
    public Observable<CodeEntity<List<HomeEntity>>> getTopList() {
        return apiServer.getTopList();
    }

    @Override
    public Observable<CodeEntity<List<HotEntity>>> getHotKey() {
        return apiServer.getHotKey();
    }

    @Override
    public Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> geSeek(int size, String name) {
        return apiServer.getSeek(size, name);
    }

    @Override
    public Observable<CodeEntity<List<WxTitleEntity>>> getWxarticle() {
        return apiServer.getWxarticle();
    }

    @Override
    public Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getWxList(int id, int size) {
        return apiServer.getWxList(id, size);
    }

    @Override
    public Observable<CodeEntity<List<SeriesEntity>>> getSeries() {
        return apiServer.getSeries();
    }

    @Override
    public Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getList(int page, int id) {
        return apiServer.getList(page, id);
    }

    @Override
    public Observable<CodeEntity<List<NavigationEntity>>> getNavigation() {
        return apiServer.getNavigation();
    }

    @Override
    public Observable<CodeEntity<LoginEntity>> SetLogin(String username, String password) {
        return apiServer.SetLogin(username, password);
    }

    @Override
    public Observable<CodeEntity<LoginEntity>> SetRegister(String username, String password, String repassword) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("repassword", repassword);
        return apiServer.SetRegister(map);
    }


}
