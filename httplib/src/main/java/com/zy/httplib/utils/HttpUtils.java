package com.zy.httplib.utils;

import com.zy.httplib.okhttp.builder.GetRequestBuilder;
import com.zy.httplib.okhttp.builder.PostFormRequestBuilder;
import com.zy.httplib.okhttp.builder.PostStringRequestBuilder;

import okhttp3.OkHttpClient;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpUtils {

    private OkHttpClient mOkHttpClient;
    private static HttpUtils mInstance;

    private HttpUtils(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public static HttpUtils getInstance() {
        return initClient(new OkHttpClient());
    }

    private static HttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * get请求方法
     *
     * @return
     */
    public GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    /**
     * POS请求
     *
     * @return PostFormRequestBuilder
     */
    public PostStringRequestBuilder postString() {
        return new PostStringRequestBuilder();
    }

    /**
     * POS请求(form-data编码形式)
     *
     * @return PostFormRequestBuilder
     */
    public PostFormRequestBuilder postForm() {
        return new PostFormRequestBuilder();
    }
}
