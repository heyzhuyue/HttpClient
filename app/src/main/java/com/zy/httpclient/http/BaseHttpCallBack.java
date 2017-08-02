package com.zy.httpclient.http;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.$Gson$Types;
import com.zy.httpclient.http.base.BaseResponse;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class BaseHttpCallBack<T> implements Callback {

    private Type mType;
    private Handler mHandler;

    protected BaseHttpCallBack() {
        mType = getGsonResponseType();
        mHandler = new Handler();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        sendError(e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String result = response.body().string();
        try {
            final BaseResponse baseResponse = new Gson().fromJson(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String res = new Gson().toJson(baseResponse.getNewslist());
                final T t = new Gson().fromJson(res, mType);
                sendNext(t);
            } else {
                sendSubError(baseResponse.getMsg());
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            sendError("数据解析错误");
        }
    }

    private Type getGsonResponseType() {
        Type type = getClass().getGenericSuperclass();    //反射获取带泛型的class
        if (type instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameter = (ParameterizedType) type;      //获取所有泛型
        return $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
    }

    private void sendNext(final T t) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onNext(t);
            }
        });
    }

    private void sendSubError(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSubError(message);
            }
        });
    }

    private void sendError(final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onError(message);
            }
        });
    }

    public abstract void onNext(T t);

    public abstract void onSubError(String message);

    public abstract void onError(String message);


}
