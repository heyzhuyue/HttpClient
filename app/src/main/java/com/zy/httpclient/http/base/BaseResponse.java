package com.zy.httpclient.http.base;

/**
 * Created by zy on 2017/8/1.
 */

public class BaseResponse {

    private int code;

    private String msg;

    private Object newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getNewslist() {
        return newslist;
    }

    public void setNewslist(Object newslist) {
        this.newslist = newslist;
    }
}
