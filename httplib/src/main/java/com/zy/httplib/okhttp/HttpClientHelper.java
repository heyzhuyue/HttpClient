package com.zy.httplib.okhttp;

import com.zy.httplib.okhttp.callback.BaseCallBack;
import com.zy.httplib.okhttp.body.HttpRequestBody;
import com.zy.httplib.utils.HttpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpClientHelper {

    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private HttpRequestBody okHttpRequest;
    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

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

    private Call buildCall() {
        Request request = okHttpRequest.generateRequest();
        Call call;
        if (readTimeOut > 0 || writeTimeOut > 0 || connTimeOut > 0) {
            readTimeOut = readTimeOut > 0 ? readTimeOut : DEFAULT_MILLISECONDS;
            writeTimeOut = writeTimeOut > 0 ? writeTimeOut : DEFAULT_MILLISECONDS;
            connTimeOut = connTimeOut > 0 ? connTimeOut : DEFAULT_MILLISECONDS;

            OkHttpClient okHttpClient = HttpUtils.getInstance().getOkHttpClient().newBuilder()
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

    /**
     * 执行网络请求
     *
     * @param callback Callback回调
     */
    public void execute(BaseCallBack callback) {
        buildCall();
        if (callback == null) {
            callback = BaseCallBack.DEFAULT_CALLBACK;
        }
        final BaseCallBack finBaseCallBack = callback;
        final int id = okHttpRequest.getId();
        buildCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
                finBaseCallBack.onError(call, e, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        finBaseCallBack.onError(call, new IOException("请求取消"), id);
                        return;
                    }
                    if (!finBaseCallBack.validateReponse(response, id)) {
                        finBaseCallBack.onError(call, new IOException("request failed"), id);
                    }
                    Object object = finBaseCallBack.parseNetworkResponse(response, id);
                    if (object != null) {
                        finBaseCallBack.onResponse(object, id);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    finBaseCallBack.onError(call, e, id);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }
            }
        });
    }
}
