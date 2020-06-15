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

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 10:36
 */
public interface HttpDataSource {
    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getHomeList(int size);

    Observable<CodeEntity<List<DataBean>>> getBanner();

    /**
     * 置顶文章
     *
     * @return
     */
    Observable<CodeEntity<List<HomeEntity>>> getTopList();

    Observable<CodeEntity<List<HotEntity>>> getHotKey();


    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> geSeek(int size, String name);

    Observable<CodeEntity<List<WxTitleEntity>>> getWxarticle();

    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getWxList(int id, int size);

    Observable<CodeEntity<List<SeriesEntity>>> getSeries();

    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getList(int page, int id);

    Observable<CodeEntity<List<NavigationEntity>>> getNavigation();

    Observable<CodeEntity<LoginEntity>> SetLogin(String username, String password);

    Observable<CodeEntity<LoginEntity>> SetRegister(String username, String password, String repassword);

}
