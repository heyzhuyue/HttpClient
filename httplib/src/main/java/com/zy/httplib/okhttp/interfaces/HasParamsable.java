package com.zy.httplib.okhttp.interfaces;

import com.zy.httplib.okhttp.builder.RequestBuilder;

import java.util.Map;

/**
 * Created by zhy on 16/3/1.
 */
public interface HasParamsable {

    /**
     * 设置参数
     *
     * @param params 请求参数
     * @return RequestBuilder
     */
    RequestBuilder params(Map<String, String> params);

    /**
     * 添加参数
     *
     * @param key 参数key
     * @param val 参数值
     * @return RequestBuilder
     */
    RequestBuilder addParams(String key, String val);
}
