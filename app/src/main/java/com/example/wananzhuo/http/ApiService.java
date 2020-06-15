package com.example.wananzhuo.http;

import android.util.Log;

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
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 10:30
 */
public interface ApiService {


    @GET("article/list/{size}/json")
    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getHomeList(@Path("size") int size);

    @GET("banner/json")
    Observable<CodeEntity<List<DataBean>>> getBanner();

    /**
     * 置顶文章
     *
     * @return
     */
    @GET("article/top/json")
    Observable<CodeEntity<List<HomeEntity>>> getTopList();

    /**
     *
     */
    @GET("/hotkey/json")
    Observable<CodeEntity<List<HotEntity>>> getHotKey();

    /**
     * 搜索接口
     */
    @POST("article/query/{size}/json")
    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getSeek(@Path("size") int size, @Query("k") String name);

    //公众号
    @GET("wxarticle/chapters/json")
    Observable<CodeEntity<List<WxTitleEntity>>> getWxarticle();

    //公众号历史文章
    @GET("/wxarticle/list/{id}/{size}/json")
    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getWxList(@Path("id") int id, @Path("size") int size);

    @GET("/tree/json")
    Observable<CodeEntity<List<SeriesEntity>>> getSeries();

    @GET("/article/list/{page}/json")
    Observable<CodeEntity<HomeListEntity<List<HomeEntity>>>> getList(@Path("page") int page, @Query("cid") int id);

    @GET("/navi/json")
    Observable<CodeEntity<List<NavigationEntity>>> getNavigation();

    @POST("/user/login")
    Observable<CodeEntity<LoginEntity>> SetLogin(@Field("username") String username, @Field("password") String password);

    @POST("/user/register")
    Observable<CodeEntity<LoginEntity>> SetRegister(@FieldMap Map<String ,String> map);


//    /**
//     * 公众号历史记录
//     */
//    @GET("wxarticle/list/{id}/{page}/json")
//    Observable<HistoryEntity> getHistCall(@Path("id") String id, @Path("page") String page);
//
//    /**
//     * 玩安卓开放接口-公众号接口
//     *
//     * @return
//     */
//    @GET("/wxarticle/chapters/json")
//    Observable<CodeEntity<List<SwEntity>>> getChapters();
//
//    /**
//     * 公众号
//     * @param id
//     * @param page
//     * @return
//     */
//    @GET("wxarticle/list/{id}/{page}/json")
//    Observable<CodeEntity<NewsWEntity<List<NewsEntity>>>> getArticle(@Path("id") String id, @Path("page") String page);
//
//    /**
//     * 注册接口
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("/user/register")
//    Observable<RegisterEntity> getRegister(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
//
//    /**
//     * 登录接口
//     * @param username
//     * @param password
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("/user/login")
//    Observable<CodeEntity<LoginEntity>> getLogin(@Field("username") String username, @Field("password") String password);
//
//    /**
//     * 轮播图
//     * @return
//     */
//    @GET("/banner/json")
//    Observable<CodeEntity<List<BannerEntity>>> getBanner();
//
//    /**
//     * 首页数据
//     * @param page
//     * @return
//     */
//    @GET("/article/list/{page}/json")
//    Observable<CodeEntity<CssEntity<List<ConsultEntity>>>> getConsult(@Path("page") String page);
//
//    /**
//     * 收藏文章
//     *
//     * @param id
//     * @return
//     */
//    @POST("/lg/collect/{id}/json")
//    Observable<CodeEntity> getCollect(@Path("id") String id);
//
//    /**
//     * 搜索
//     *
//     * @param page
//     * @param K
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("/article/query/{page}/json")
//    Observable<CodeEntity<SearchEntity<List<SearchListEntity>>>> getSearch(@Path("page") String page, @Field("k") String K);
//
//
//    @GET("/tree/json")
//    Observable<CodeEntity<List<SystemEntity>>> getSystem();
//
//    /**
//     * 置顶数据
//     *
//     * @return
//     */
//    @GET("/article/top/json")
//    Observable<CodeEntity<List<ConsultEntity>>> getTop();
//
//    /**
//     * 体系列表文章
//     *
//     * @param page
//     * @param cid
//     * @return
//     */
//    @GET("/article/list/{page}/json")
//    Observable<CodeEntity<SearchEntity<List<NewsEntity>>>> getArtcle(@Path("page") String page, @Query("cid") String cid);
//
//    /**
//     * 收藏列表
//     *
//     * @param page
//     * @return
//     */
//    @GET("/lg/collect/list/{page}/json")
//    Observable<CodeEntity<SearchEntity<List<CollectionEntity>>>> getCollection(@Path("page") String page);
//
//    /**
//     * 收藏
//     *
//     * @param id
//     * @return
//     */
//    @POST("/lg/collect/{id}/json")
//    Observable<CodeEntity> getCilckCollection(@Path("id") String id);
//
//    /**
//     * 取消收藏
//     *
//     * @param id
//     * @return
//     */
//    @POST("/lg/uncollect_originId/{id}/json")
//    Observable<BannerEntity> getIsCollection(@Path("id") String id);
//

}
