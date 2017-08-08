package com.zy.httplib.okhttp.callback;

import com.zy.httplib.okhttp.interfaces.IGenericsSerializator;

import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
 * Created by zy on 2017/8/8.
 */

public abstract class EntityCallback<T> extends BaseCallBack<T> {

    private IGenericsSerializator mIGenericsSerializator;

    public EntityCallback(IGenericsSerializator iGenericsSerializator) {
        this.mIGenericsSerializator = iGenericsSerializator;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String result = response.body().string();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            return (T) result;
        }
        T bean = mIGenericsSerializator.transform(result, entityClass);
        return bean;
    }
}
