package com.zy.httplib.okhttp.body;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/16.
 */

public class HttpPostRequestBody extends HttpRequestBody {

    public HttpPostRequestBody(String url, Object tag, Map<String, String> headers, int id) {
        super(url, tag, headers, id);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }
}
