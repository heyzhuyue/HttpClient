package com.zy.httpclient.okhttp.quest;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/2.
 */

public class GetQuestBody extends HttpQuestBody {

    private GetQuestBody(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        super(url, tag, params, headers, id);
    }

    @Override
    RequestBody buildRequestBody() {
        return null;
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }
}
