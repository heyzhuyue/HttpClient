package com.zy.httplib.okhttp.callback;

import okhttp3.Response;

/**
 * Created by zy on 2017/8/8.
 */

public abstract class StringCallBack extends BaseCallBack<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().string();
    }
}
