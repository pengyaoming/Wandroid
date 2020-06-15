package com.example.wananzhuo.Entity;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/15
 * Time: 13:47
 * Name: 作用类：
 */
public class IntegralEntity {

    /**
     * coinCount : 851
     * level : 9
     * rank : 860
     * userId : 29060
     * username : 1**223
     */

    private int coinCount;
    private int level;
    private String rank;
    private int userId;
    private String username;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
