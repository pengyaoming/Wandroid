package com.example.wananzhuo.Entity;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 15:05
 */
public class BannerEntity
{


    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://wanandroid.com/blogimgs/7afbd760-8b30-4c7b-8353-25b38fc65908.jpeg
     * isVisible : 1
     * order : 0
     * title : “你能做个淘宝、微信吗？”
     * type : 0
     * url : https://mp.weixin.qq.com/s/ledHfZJo64nfXjqsbzT0Dg
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
