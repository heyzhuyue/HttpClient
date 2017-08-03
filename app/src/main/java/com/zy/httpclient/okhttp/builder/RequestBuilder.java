package com.zy.httpclient.okhttp.builder;

import com.zy.httpclient.okhttp.HttpClientHelper;

import java.util.Map;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class RequestBuilder<T extends RequestBuilder> {

    /**
     * 请求Url地址
     */
    protected String url;
    /**
     * Tgg
     */
    protected Object tag;
    /**
     * 请求Header
     */
    protected Map<String, String> headers;
    /**
     * 请求参数
     */
    protected Map<String, String> params;
    /**
     * id
     */
    protected int id;

    public T setUrl(String url) {
        this.url = url;
        return (T) this;
    }

    public T setTag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return (T) this;
    }

    public T setParams(Map<String, String> params) {
        this.params = params;
        return (T) this;
    }

    public T setId(int id) {
        this.id = id;
        return (T) this;
    }

    public abstract HttpClientHelper build();
}
