package com.zy.httpclient.okhttp.builder;

import com.zy.httpclient.http.HttpExecuteHelper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zy on 2017/8/2.
 */

public class GetquestBuilder extends RequestBuilder<GetquestBuilder> implements HasParamsable {

    @Override
    public HttpExecuteHelper build() {
        return null;
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

}
