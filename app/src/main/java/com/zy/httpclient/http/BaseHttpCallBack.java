package com.zy.httpclient.http;

import android.os.Handler;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class BaseHttpCallBack<T> {

    public Type mType;
    private Handler mHandler;

    protected BaseHttpCallBack() {
        mType = getGsonResponseType();
        mHandler = new Handler();
    }

    private Type getGsonResponseType() {
        Type type = getClass().getGenericSuperclass();    //反射获取带泛型的class
        if (type instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameter = (ParameterizedType) type;      //获取所有泛型
        return $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
    }

    public void sendNext(final T t) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onNext(t);
            }
        });
    }

    public void sendSubError(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSubError(message);
            }
        });
    }

    public void sendError(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onError(message);
            }
        });
    }

    protected abstract void onNext(T t);

    protected abstract void onSubError(String message);

    protected abstract void onError(String message);


}
