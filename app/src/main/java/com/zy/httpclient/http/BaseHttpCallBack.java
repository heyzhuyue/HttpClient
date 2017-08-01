package com.zy.httpclient.http;

/**
 * Created by zy on 2017/8/1.
 */

public abstract class BaseHttpCallBack<T> {

    public abstract void onNext(T t);

    public abstract void onSubError(String message);

    public abstract void onError(String message);

}
