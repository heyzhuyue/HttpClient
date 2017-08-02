package com.zy.httpclient.http;

import android.text.TextUtils;

import com.zy.httpclient.http.enums.HttpClientMethod;

/**
 * Created by zhuyue on 2017/8/1
 */

public class HttpClientManager {

    private int mConnectionTime;

    private int mReadTimeOut;

    private int mWriteTimeOut;

    private HttpClientMethod mHttpClientMethod = HttpClientMethod.POST;

    private String mApiMethod;

    public HttpClientManager(Builder builder) {
        this.mConnectionTime = builder.mConnectionTimeOut;
        this.mReadTimeOut = builder.mReadTimeOut;
        this.mHttpClientMethod = builder.mHttpClientMethod;
        this.mApiMethod = builder.mApiMethod;
        this.mWriteTimeOut = builder.mWriteTimeOut;
    }

    public static class Builder {

        private int mConnectionTimeOut = 400;

        private int mReadTimeOut = 400;

        private int mWriteTimeOut = 400;

        private HttpClientMethod mHttpClientMethod = HttpClientMethod.POST;

        private String mApiMethod;

        public Builder setConnectionTimeOut(int connectionTimeOut) {
            this.mConnectionTimeOut = connectionTimeOut;
            return this;
        }

        public void setReadTimeOut(int readTimeOut) {
            this.mReadTimeOut = readTimeOut;
        }

        public void setWriteTimeOut(int writeTimeOut) {
            this.mWriteTimeOut = writeTimeOut;
        }

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
        HttpExecuteHelper httpExecuteHelper = new HttpExecuteHelper();
        httpExecuteHelper.setApiMethod(mApiMethod);
        httpExecuteHelper.setConnectTimeOut(mConnectionTime);
        httpExecuteHelper.setReadTimeOut(mReadTimeOut);
        httpExecuteHelper.setRequestMethod(mHttpClientMethod);
        httpExecuteHelper.setWriteTimeOut(mWriteTimeOut);
        httpExecuteHelper.addParameter(param);
        httpExecuteHelper.execute(baseHttpCallBack);
    }

}
