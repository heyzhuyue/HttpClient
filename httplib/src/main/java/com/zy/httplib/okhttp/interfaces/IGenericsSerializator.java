package com.zy.httplib.okhttp.interfaces;

/**
 * Created by zy on 2017/8/8.
 */

public interface IGenericsSerializator {

    <T> T transform(String response, Class<T> classOfT);
}
