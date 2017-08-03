package com.zy.httpclient.okhttp.interfaces;

import com.zy.httpclient.okhttp.builder.RequestBuilder;

import java.util.Map;

/**
 * Created by zhy on 16/3/1.
 */
public interface HasParamsable {


    RequestBuilder params(Map<String, String> params);

    RequestBuilder addParams(String key, String val);
}
