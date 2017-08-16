package com.zy.httplib.okhttp.builder;

import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.body.HttpOtherRequestBody;
import com.zy.httplib.okhttp.enums.HttpClientMethod;

import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/16.
 */

public class OtherRequestBuilder extends RequestBuilder<OtherRequestBuilder> {

    private RequestBody requestBody;
    private String content;
    private HttpClientMethod method;

    public OtherRequestBuilder(HttpClientMethod method) {
        this.method = method;
    }

    @Override
    public HttpClientHelper build() {
        return new HttpOtherRequestBody(requestBody, content, method, url, tag, params, headers, id).build();
    }

    public OtherRequestBuilder requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public OtherRequestBuilder setContent(String content) {
        this.content = content;
        return this;
    }
}
