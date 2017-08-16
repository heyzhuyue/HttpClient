package com.zy.httplib.utils;

import com.zy.httplib.okhttp.builder.GetRequestBuilder;
import com.zy.httplib.okhttp.builder.HeadRequestBuilder;
import com.zy.httplib.okhttp.builder.OtherRequestBuilder;
import com.zy.httplib.okhttp.builder.PostBuilder;
import com.zy.httplib.okhttp.builder.PostFormRequestBuilder;
import com.zy.httplib.okhttp.builder.PostStringRequestBuilder;
import com.zy.httplib.okhttp.enums.HttpClientMethod;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpUtils {

    private OkHttpClient mOkHttpClient;
    private static HttpUtils mInstance;
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    public static long readTimeOut;
    public static long writeTimeOut;
    public static long connTimeOut;
    private static boolean isOpenLogging = true;

    public static void setIsOpenLogging(boolean isOpenLogging) {
        HttpUtils.isOpenLogging = isOpenLogging;
    }

    public static void setReadTimeOut(long readTimeOut) {
        HttpUtils.readTimeOut = readTimeOut;
    }

    public static void setWriteTimeOut(long writeTimeOut) {
        HttpUtils.writeTimeOut = writeTimeOut;
    }

    public static void setConnTimeOut(long connTimeOut) {
        HttpUtils.connTimeOut = connTimeOut;
    }

    private HttpUtils(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public static HttpUtils getInstance() {
        readTimeOut = readTimeOut > 0 ? readTimeOut : DEFAULT_MILLISECONDS;
        writeTimeOut = writeTimeOut > 0 ? writeTimeOut : DEFAULT_MILLISECONDS;
        connTimeOut = connTimeOut > 0 ? connTimeOut : DEFAULT_MILLISECONDS;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                .connectTimeout(connTimeOut, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor);
        if (isOpenLogging) {
            builder.addInterceptor(loggingInterceptor);
        }
        OkHttpClient okHttpClient = builder.build();
        return initClient(okHttpClient);
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
     * GET请求方法
     *
     * @return
     */
    public GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    /**
     * HEAD请求
     *
     * @return
     */
    public static HeadRequestBuilder head() {
        return new HeadRequestBuilder();
    }

    /**
     * PUT请求
     *
     * @return
     */
    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(HttpClientMethod.PUT);
    }

    /**
     * DELETE请求
     *
     * @return
     */
    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(HttpClientMethod.DELETE);
    }

    /**
     * PATCH请求
     *
     * @return
     */
    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(HttpClientMethod.PATCH);
    }

    /**
     * POS请求
     *
     * @return PostFormRequestBuilder
     */
    public PostBuilder post() {
        return new PostBuilder();
    }

    /**
     * POS请求(String RequestBody)
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
