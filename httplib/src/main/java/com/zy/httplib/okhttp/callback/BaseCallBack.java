package com.zy.httplib.okhttp.callback;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zy on 2017/8/2.
 */

public abstract class BaseCallBack<T> {

    /**
     * 下载进度回调(UI回调)
     *
     * @param progress 进度
     */
    public void loadProgress(float progress, long total, int id) {
        Log.d("下载进度", "进度" + progress);
    }

    /**
     * 判断网络数据是否获取成功
     *
     * @param response 请求返回Response
     * @param id       id
     * @return
     */
    public boolean validateReponse(Response response, int id) {
        return response.isSuccessful();
    }

    public abstract T parseNetworkResponse(Response response, int id) throws Exception;

    /**
     * 缓存数据返回回调()
     *
     * @param response 缓存数据
     * @param id       id
     */
    protected abstract void onCacheResponse(T response, int id);

    /**
     * 数据返回回调
     *
     * @param response 数据
     * @param id       id
     */
    public abstract void onResponse(T response, int id);

    /**
     * 请求失败回调
     *
     * @param call OkHttp Call
     * @param e    异常
     * @param id   id
     */
    public abstract void onError(Call call, Exception e, int id);

    /**
     * 默认接口空实现
     */
    public static final BaseCallBack DEFAULT_CALLBACK = new BaseCallBack() {
        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception {
            return null;
        }

        @Override
        protected void onCacheResponse(Object response, int id) {

        }

        @Override
        public void onResponse(Object response, int id) {

        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }
    };
}
