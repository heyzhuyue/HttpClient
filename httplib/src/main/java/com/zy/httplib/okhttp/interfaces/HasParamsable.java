package com.zy.httplib.okhttp.interfaces;

import com.zy.httplib.okhttp.builder.RequestBuilder;

/**
 * Created by zhy on 16/3/1.
 */
public interface HasParamsable {

    /**
     * 添加参数
     *
     * @param key 参数key
     * @param val 参数值
     * @return RequestBuilder
     */
    RequestBuilder addParams(String key, String val);
}
