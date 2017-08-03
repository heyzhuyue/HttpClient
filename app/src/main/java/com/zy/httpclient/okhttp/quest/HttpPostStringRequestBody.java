package com.zy.httpclient.okhttp.quest;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/3.
 */

public class HttpPostStringRequestBody extends HttpRequestBody {

    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");
    private String content;
    private MediaType mediaType;

    public HttpPostStringRequestBody(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id, String content, MediaType mediaType) {
        super(url, tag, params, headers, id);
        this.content = content;
        this.mediaType = mediaType;
        if (this.content == null) {
            throw new RuntimeException();
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }
    }

    @Override
    RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, content);
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
