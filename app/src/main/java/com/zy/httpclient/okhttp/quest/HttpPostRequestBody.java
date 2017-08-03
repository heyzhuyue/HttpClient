package com.zy.httpclient.okhttp.quest;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/3.
 */

public class HttpPostRequestBody extends HttpRequestBody {

    public HttpPostRequestBody(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    RequestBody buildRequestBody() {
        FormBody.Builder requestBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            requestBuilder.add(entry.getKey(), entry.getValue().toString());
        }
        return requestBuilder.build();
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
