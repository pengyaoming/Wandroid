package com.example.wananzhuo.Entity;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/19
 * Time: 15:35
 */
public class DataBean {

    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://www.wanandroid.com/blogimgs/3c83027f-f155-45f3-b200-bebb01864c44.jpeg
     * isVisible : 1
     * order : 0
     * title : 历时半年，整理了这份379页的《最全Android面试题及答案解析》
     * type : 0
     * url : https://mp.weixin.qq.com/s/XFiAQK5zTve0QvpNFVN1Pw
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
