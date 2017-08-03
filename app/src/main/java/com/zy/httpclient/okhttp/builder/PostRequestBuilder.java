package com.zy.httpclient.okhttp.builder;

import com.zy.httpclient.okhttp.HttpClientHelper;
import com.zy.httpclient.okhttp.interfaces.HasParamsable;
import com.zy.httpclient.okhttp.quest.HttpPostRequestBody;

import java.util.Map;

/**
 * Created by zy on 2017/8/2.
 */

public class PostRequestBuilder extends RequestBuilder<PostRequestBuilder> implements HasParamsable {

    @Override
    public HttpClientHelper build() {
        return new HttpPostRequestBody(url, tag, params, headers, id).build();
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (params == null) {
            return this;
        }
        params.put(key, val);
        return this;
    }
}
