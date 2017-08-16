package com.zy.httpclient.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zy.httpclient.constans.API;
import com.zy.httpclient.http.base.BaseResponse;
import com.zy.httpclient.http.interfaces.HttpClientDefaultDeploy;
import com.zy.httpclient.utils.AppUtils;
import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.callback.StringCallBack;
import com.zy.httplib.okhttp.enums.HttpClientMethod;
import com.zy.httplib.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpExecuteHelper implements HttpClientDefaultDeploy {
    private String apiMethod;
    private HttpClientMethod httpMethod = HttpClientMethod.POST;
    private Map<String, String> headers;
    private Map<String, String> params;

    public HttpExecuteHelper(HttpClientMethod httpClientMethod) {
        httpMethod = httpClientMethod;
        headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        params = new HashMap<>();
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
        params.put(name, value);
        return this;
    }

    @Override
    public HttpExecuteHelper addParameter(Object param) {
        params.clear();
        Map<String, String> params = AppUtils.beanToMap(param);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            this.params.put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public String getCharset() {
        return "UTF-8";
    }

    @Override
    public void execute(final BaseHttpCallBack baseHttpCallBack) {
        if (TextUtils.isEmpty(apiMethod)) {
            baseHttpCallBack.onError("请求接口为空");
        }
        HttpClientHelper httpClientHelper = null;
        switch (httpMethod) {
            case GET:
                httpClientHelper = HttpUtils.getInstance().get().setUrl(API.BASE_URL + apiMethod)
                        .setParams(params)
                        .setHeaders(headers)
                        .build();
                break;
            case POST:
                httpClientHelper = HttpUtils.getInstance().post().setUrl(API.BASE_URL + apiMethod)
                        .setParams(params)
                        .setHeaders(headers)
                        .build();
                break;
            default:
                break;
        }
        if (httpClientHelper != null) {
            httpClientHelper
                    .execute(new StringCallBack() {
                        @Override
                        protected void onCacheResponse(String response, int id) { //缓存数据
                            try {
                                final BaseResponse baseResponse = new Gson().fromJson(response, BaseResponse.class);
                                if (baseResponse.getCode() == 200) {
                                    String res = new Gson().toJson(baseResponse.getNewslist());
                                    final Object t = new Gson().fromJson(res, baseHttpCallBack.mType);
                                    baseHttpCallBack.sendNext(t);
                                } else {
                                    baseHttpCallBack.sendSubError(baseResponse.getMsg());
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                baseHttpCallBack.sendError("数据解析错误");
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) { //网络请求数据
                            try {
                                final BaseResponse baseResponse = new Gson().fromJson(response, BaseResponse.class);
                                if (baseResponse.getCode() == 200) {
                                    String res = new Gson().toJson(baseResponse.getNewslist());
                                    final Object t = new Gson().fromJson(res, baseHttpCallBack.mType);
                                    baseHttpCallBack.sendNext(t);
                                } else {
                                    baseHttpCallBack.sendSubError(baseResponse.getMsg());
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                baseHttpCallBack.sendError("数据解析错误");
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) { //网络请求错误
                            baseHttpCallBack.sendError(e.getMessage());
                        }
                    });
        }

    }
}
