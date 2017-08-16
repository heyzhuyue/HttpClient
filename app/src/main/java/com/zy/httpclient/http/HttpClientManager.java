package com.zy.httpclient.http;

import android.text.TextUtils;

import com.zy.httplib.okhttp.enums.HttpClientMethod;

/**
 * Created by zhuyue on 2017/8/1
 */

public class HttpClientManager {

    /**
     * 请求方式
     */
    private HttpClientMethod mHttpClientMethod = HttpClientMethod.POST;

    /**
     * 请求方法
     */
    private String mApiMethod;

    public HttpClientManager(Builder builder) {
        this.mHttpClientMethod = builder.mHttpClientMethod;
        this.mApiMethod = builder.mApiMethod;
    }

    public static class Builder {


        private HttpClientMethod mHttpClientMethod = HttpClientMethod.POST;

        private String mApiMethod;

        public Builder setHttpClientMethond(HttpClientMethod httpClientMethod) {
            this.mHttpClientMethod = httpClientMethod;
            return this;
        }

        public Builder setApiMethod(String adiMethod) {
            this.mApiMethod = adiMethod;
            return this;
        }

        public HttpClientManager build() {
            return new HttpClientManager(this);
        }

    }

    /**
     * 执行网络请求
     *
     * @param param            参数
     * @param baseHttpCallBack 请求回调
     */
    public void execute(Object param, BaseHttpCallBack baseHttpCallBack) {
        if (baseHttpCallBack == null) {
            return;
        }
        if (param == null) {
            baseHttpCallBack.onError("请求参数为空");
            return;
        }
        if (TextUtils.isEmpty(mApiMethod)) {
            baseHttpCallBack.onError("接口地址为空");
            return;
        }
        HttpExecuteHelper httpExecuteHelper = new HttpExecuteHelper(mHttpClientMethod);
        httpExecuteHelper.setApiMethod(mApiMethod);
        httpExecuteHelper.addParameter(param);
        httpExecuteHelper.execute(baseHttpCallBack);
    }

}
