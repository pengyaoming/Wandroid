package com.example.wananzhuo.Entity;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 10:41
 */
public class CodeEntity<T> {
    /**
     * msg : 查询成功
     * code : 0
     * data : []
     */
    private String msg;
    private int code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

}
