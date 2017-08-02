package com.zy.httpclient.okhttp.quest;

import com.zy.httpclient.okhttp.callback.BaseCallBack;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class HttpQuestBody {

    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected int id;
    protected Request.Builder builder = new Request.Builder();

    protected HttpQuestBody(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        this.url = url;
        this.tag = tag;
        this.params = params;
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

    private void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }


    private RequestBody wrapRequestBody(RequestBody requestBody, final BaseCallBack callback) {
        return requestBody;
    }

    abstract RequestBody buildRequestBody();

    abstract Request buildRequest(RequestBody requestBody);

    public Request generateRequest(BaseCallBack callback) {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }
}
