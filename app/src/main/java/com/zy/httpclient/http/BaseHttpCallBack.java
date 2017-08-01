package com.zy.httpclient.http;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zy on 2017/8/1.
 */

public abstract class BaseHttpCallBack<T> {

    private Type mType;

    public BaseHttpCallBack() {
        mType = getGsonResponseType();
    }

    public abstract void onNext(T t);

    public abstract void onSubError(String message);

    public abstract void onError(String message);


    private Type getGsonResponseType() {
        Type type = getClass().getGenericSuperclass();    //反射获取带泛型的class
        if (type instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameter = (ParameterizedType) type;      //获取所有泛型
        return $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
    }

}
