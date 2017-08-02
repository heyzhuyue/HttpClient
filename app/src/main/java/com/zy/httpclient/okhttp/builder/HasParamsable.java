package com.zy.httpclient.okhttp.builder;

import java.util.Map;

/**
 * Created by zhy on 16/3/1.
 */
public interface HasParamsable {


    RequestBuilder params(Map<String, String> params);

    RequestBuilder addParams(String key, String val);
}
