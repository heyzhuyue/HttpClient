package com.zy.httplib.okhttp;

import com.zy.httplib.okhttp.body.HttpRequestBody;
import com.zy.httplib.okhttp.callback.BaseCallBack;
import com.zy.httplib.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zy on 2017/8/2.
 */

public class HttpClientHelper {

    private HttpRequestBody okHttpRequest;

    public HttpClientHelper(HttpRequestBody okHttpRequest) {
        this.okHttpRequest = okHttpRequest;
    }

    private Call buildCall() {
        Request request = okHttpRequest.generateRequest();
        OkHttpClient okHttpClient = HttpUtils.getInstance().getOkHttpClient();
        return okHttpClient.newCall(request);
    }

    /**
     * 执行网络请求
     *
     * @param callback Callback回调
     */
    public void execute(BaseCallBack callback) {
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
