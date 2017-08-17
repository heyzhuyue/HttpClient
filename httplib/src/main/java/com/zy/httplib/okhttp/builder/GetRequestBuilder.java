package com.zy.httplib.okhttp.builder;

import android.net.Uri;

import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.interfaces.HasParamsable;
import com.zy.httplib.okhttp.body.HttpGetRequestBody;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zy on 2017/8/2.
 */

public class GetRequestBuilder extends RequestBuilder<GetRequestBuilder> implements HasParamsable {

    @Override
    public HttpClientHelper build() {
        if (params != null) {
            url = appendParamsForUrl(url, params);
        }
        return new HttpGetRequestBody(url, tag, headers, id, isCache).build();
    }

    /**
     * Get请求拼接参数到请求地址
     *
     * @param url    请求地址
     * @param params 参数
     * @return
     */
    protected String appendParamsForUrl(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }

    @Override
    public GetRequestBuilder setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public void setCache(boolean cache) {
        this.isCache = cache;
    }
}
