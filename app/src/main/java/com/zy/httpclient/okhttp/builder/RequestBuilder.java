package com.zy.httpclient.okhttp.builder;

import com.zy.httpclient.http.HttpExecuteHelper;

import java.util.Map;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class RequestBuilder<T extends RequestBuilder> {

    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
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

    public abstract HttpExecuteHelper build();
}
