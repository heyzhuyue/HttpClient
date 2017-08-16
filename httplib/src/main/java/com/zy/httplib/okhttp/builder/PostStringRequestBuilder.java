package com.zy.httplib.okhttp.builder;

import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.body.HttpPostStringRequestBody;

import okhttp3.MediaType;

/**
 * Created by zy on 2017/8/3.
 */

public class PostStringRequestBuilder extends RequestBuilder<PostStringRequestBuilder> {

    /**
     * 内容
     */
    private String content;
    /**
     * POST请求编码格式
     */
    private MediaType mediaType;

    public PostStringRequestBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public PostStringRequestBuilder setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public HttpClientHelper build() {
        return new HttpPostStringRequestBody(url, tag, headers, id, content, mediaType).build();
    }
}
