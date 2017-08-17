package com.zy.httplib.okhttp.body;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpGetRequestBody extends HttpRequestBody {

    private boolean isCache = true;

    /**
     * 请求地址
     *
     * @param url
     * @param tag
     * @param headers
     * @param id
     */
    public HttpGetRequestBody(String url, Object tag, Map<String, String> headers, int id, boolean isCache) {
        super(url, tag, headers, id);
        this.isCache = isCache;
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
