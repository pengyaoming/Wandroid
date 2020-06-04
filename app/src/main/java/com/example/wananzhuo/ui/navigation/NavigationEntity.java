package com.example.wananzhuo.ui.navigation;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/4
 * Time: 9:37
 * Name: 作用类：
 */
public class NavigationEntity {

    /**
     * articles : [{"apkLink":"","audit":1,"author":"小编","canEdit":false,"chapterId":272,"chapterName":"常用网站","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":13307,"link":"https://developer.android.google.cn/reference/kotlin/android/widget/TextView?hl=en","niceDate":"2020-05-08 16:19","niceShareDate":"2020-05-08 16:19","origin":"","prefix":"","projectLink":"","publishTime":1588925969000,"selfVisible":0,"shareDate":1588925969000,"shareUser":"","superChapterId":0,"superChapterName":"","tags":[],"title":"Android Sdk 查看","type":0,"userId":-1,"visible":0,"zan":0}]
     * cid : 272
     * name : 常用网站
     */

    private int cid;
    private String name;
    private List<ArticlesBean> articles;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }


}
