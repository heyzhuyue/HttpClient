package com.zy.httplib.okhttp.body;

import android.text.TextUtils;

import com.zy.httplib.okhttp.enums.HttpClientMethod;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

/**
 * Created by zy on 2017/8/16.
 */

public class HttpOtherRequestBody extends HttpRequestBody {

    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");
    private RequestBody requestBody;
    private String content;
    private HttpClientMethod method;
    private Map<String, String> params;

    public HttpOtherRequestBody(RequestBody requestBody, String content, HttpClientMethod method, String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, headers, id);
        this.requestBody = requestBody;
        this.content = content;
        this.method = method;
        this.params = params;
    }

    @Override
    protected RequestBody buildRequestBody() {
        if (requestBody == null && TextUtils.isEmpty(content) && HttpMethod.requiresRequestBody(method.toString())) {
            throw new NullPointerException("requestBody and content can not be null in method:" + method);
        }
        if (requestBody == null && !TextUtils.isEmpty(content)) {
            requestBody = RequestBody.create(MEDIA_TYPE_PLAIN, content);
        }
        return requestBody;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        if (method.equals(HttpClientMethod.PUT)) {
            builder.put(requestBody);
        } else if (method.equals(HttpClientMethod.DELETE)) {
            if (requestBody == null)
                builder.delete();
            else
                builder.delete(requestBody);
        } else if (method.equals(HttpClientMethod.HEAD)) {
            builder.head();
        } else if (method.equals(HttpClientMethod.PATCH)) {
            builder.patch(requestBody);
        }
        return builder.build();
    }
}
