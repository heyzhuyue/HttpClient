package com.zy.httpclient.weight;

import com.google.gson.Gson;
import com.zy.httplib.okhttp.interfaces.IGenericsSerializator;

/**
 * Created by JimGong on 2016/6/23.
 */
public class JsonGenericsSerializator implements IGenericsSerializator {
    private Gson mGson = new Gson();

    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}
