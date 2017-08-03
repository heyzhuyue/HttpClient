package com.zy.httpclient.http;

import com.zy.httpclient.constans.Constants;
import com.zy.httpclient.http.enums.HttpClientMethod;
import com.zy.httpclient.http.interfaces.HttpClientDefaultDeploy;
import com.zy.httpclient.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpExecuteHelper implements HttpClientDefaultDeploy {
    private static final MediaType FORM_CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private String apiMethod;
    private HttpClientMethod httpMethod = HttpClientMethod.POST;
    private int connectTimeOut;
    private int readTimeOut;
    private int writeTimeOut;
    private Map<String, String> headers;
    private Map<String, Object> parameters;

    public HttpExecuteHelper() {
        httpMethod = HttpClientMethod.POST;
        headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        parameters = new HashMap<>();
    }

    @Override
    public HttpExecuteHelper setRequestMethod(HttpClientMethod httpClientMethod) {
        this.httpMethod = httpClientMethod;
        return this;
    }

    @Override
    public HttpExecuteHelper setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
        return this;
    }

    @Override
    public HttpExecuteHelper setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    @Override
    public HttpExecuteHelper setWriteTimeOut(int writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    @Override
    public HttpExecuteHelper setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
        return this;
    }

    @Override
    public HttpExecuteHelper addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    @Override
    public HttpExecuteHelper addParameter(String name, String value) {
        if (value == null) {
            return this;
        }
        parameters.put(name, value);
        return this;
    }

    @Override
    public HttpExecuteHelper addParameter(Object param) {
        parameters.clear();
        Map<String, Object> params = HttpUtils.beanToMap(param);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public String getCharset() {
        return "UTF-8";
    }

    @Override
    public void execute(BaseHttpCallBack baseHttpCallBack) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        RequestBody requestBody = null;
        if (httpMethod == HttpClientMethod.GET) { //GET请求方式
            StringBuffer sb = new StringBuffer();
            sb.append("?");
            for (String key : parameters.keySet()) {
                sb.append(key + "=" + parameters.get(key) + "&");
            }
            apiMethod += sb.toString();
        } else if (httpMethod == HttpClientMethod.POST) { //POST请求方式
            StringBuffer sb = new StringBuffer();
            for (String key : parameters.keySet()) {
                sb.append(key + "=" + parameters.get(key) + "&");
            }
            requestBody = RequestBody.create(FORM_CONTENT_TYPE, sb.toString());
        } else {
            FormBody.Builder requestBuilder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                requestBuilder.add(entry.getKey(), entry.getValue().toString());
            }
            requestBody = requestBuilder.build();
        }

        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        builder.url(Constants.URL + apiMethod);
        final Request request;
        switch (httpMethod) {
            case GET:
                request = builder.build();
                break;
            case POST:
                request = builder.post(requestBody).build();
                break;
            case PUT:
                request = builder.put(requestBody).build();
                break;
            case PATCH:
                request = builder.patch(requestBody).build();
                break;
            case DELETE:
                request = builder.delete(requestBody).build();
                break;
            case HEAD:
                request = builder.head().build();
                break;
            default:
                request = builder.post(requestBody).build();
                break;
        }
        okHttpClient.newCall(request).enqueue(baseHttpCallBack);
    }
}
