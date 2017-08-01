package com.zy.httpclient.http;

import com.zy.httpclient.http.base.BasequestParam;
import com.zy.httpclient.http.entity.HttpClientMethond;

/**
 * Created by zhuyue on 2017/8/1
 */

public class HttpClientManager {

    private int mConnectionTime;

    private HttpClientMethond mHttpClientMethond = HttpClientMethond.GET;

    private String mApiMethond;

    public HttpClientManager(Builder builder) {
        this.mConnectionTime = builder.mConnectionTime;
        this.mHttpClientMethond = builder.mHttpClientMethond;
        this.mApiMethond = builder.mApiMethond;
    }

    static class Builder {

        private int mConnectionTime;

        private HttpClientMethond mHttpClientMethond = HttpClientMethond.GET;

        private String mApiMethond;

        public Builder setConnectionTime(int connectionTime) {
            this.mConnectionTime = connectionTime;
            return this;
        }

        public Builder setHttpClientMethond(HttpClientMethond httpClientMethond) {
            this.mHttpClientMethond = httpClientMethond;
            return this;
        }

        public Builder setApiMethond(String apiMethond) {
            this.mApiMethond = apiMethond;
            return this;
        }

        public HttpClientManager build() {
            return new HttpClientManager(this);
        }

    }

    public void execute(BasequestParam basequestParam, BaseHttpCallBack baseHttpCallBack) {

    }

}
