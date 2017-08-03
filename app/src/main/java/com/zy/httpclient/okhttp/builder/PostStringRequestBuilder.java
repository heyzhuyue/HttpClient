package com.zy.httpclient.okhttp.builder;

import com.zy.httpclient.okhttp.HttpClientHelper;
import com.zy.httpclient.okhttp.quest.HttpPostStringRequestBody;

import okhttp3.MediaType;

/**
 * Created by zy on 2017/8/3.
 */

public class PostStringRequestBuilder extends RequestBuilder<PostStringRequestBuilder> {

    private String content;
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
        return new HttpPostStringRequestBody(url, tag, params, headers, id, content, mediaType).build();
    }
}
