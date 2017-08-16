package com.zy.httplib.okhttp.builder;

import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.body.HttpOtherRequestBody;
import com.zy.httplib.okhttp.enums.HttpClientMethod;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadRequestBuilder extends GetRequestBuilder {
    @Override
    public HttpClientHelper build() {
        return new HttpOtherRequestBody(null, null, HttpClientMethod.HEAD, url, tag, params, headers, id).build();
    }
}
