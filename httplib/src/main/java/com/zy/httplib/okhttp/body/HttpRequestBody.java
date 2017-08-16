package com.zy.httplib.okhttp.body;

import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.callback.BaseCallBack;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class HttpRequestBody {

    protected String url;
    protected Object tag;
    protected int id;
    protected Map<String, String> headers;
    protected Request.Builder builder = new Request.Builder();

    protected HttpRequestBody(String url, Object tag, Map<String, String> headers, int id) {
        this.url = url;
        this.tag = tag;
        this.headers = headers;
        this.id = id;
        if (url == null) {
            throw new RuntimeException();
        }
        initRequestBuilder();
    }

    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initRequestBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    /**
     * 添加Header
     */
    private void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    /**
     * 创建Request
     *
     * @return
     */
    public Request generateRequest() {
        RequestBody requestBody = buildRequestBody();
        Request request = buildRequest(requestBody);
        return request;
    }

    protected RequestBody wrapRequestBody(RequestBody requestBody, final BaseCallBack callback) {
        return requestBody;
    }

    public HttpClientHelper build() {
        return new HttpClientHelper(this);
    }

    public int getId() {
        return id;
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(RequestBody requestBody);
}
