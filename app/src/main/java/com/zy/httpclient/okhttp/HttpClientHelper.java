package com.zy.httpclient.okhttp;

import com.zy.httpclient.okhttp.callback.BaseCallBack;
import com.zy.httpclient.okhttp.quest.HttpRequestBody;
import com.zy.httpclient.utils.HttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpClientHelper {

    private HttpRequestBody okHttpRequest;
    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;
    private Request request;
    private Call call;
    private OkHttpClient okHttpClient;

    public HttpClientHelper(HttpRequestBody okHttpRequest) {
        this.okHttpRequest = okHttpRequest;
    }

    public HttpClientHelper readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public HttpClientHelper writeTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public HttpClientHelper connTimeOut(long connTimeOut) {
        this.connTimeOut = connTimeOut;
        return this;
    }

    public Call buildCall(BaseCallBack callback) {
        request = okHttpRequest.generateRequest();

        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : HttpUtils.DEFAULT_MILLISECONDS;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : HttpUtils.DEFAULT_MILLISECONDS;
            connTimeOut = connTimeOut > 0 ? connTimeOut : HttpUtils.DEFAULT_MILLISECONDS;

            okHttpClient = HttpUtils.getInstance().getOkHttpClient().newBuilder()
                    .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                    .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                    .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS)
                    .build();

            call = okHttpClient.newCall(request);
        } else {
            call = HttpUtils.getInstance().getOkHttpClient().newCall(request);
        }
        return call;
    }


}
