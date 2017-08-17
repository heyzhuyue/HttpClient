package com.zy.httpclient.http.interfaces;

import com.zy.httpclient.http.BaseHttpCallBack;
import com.zy.httpclient.http.HttpExecuteHelper;

/**
 * Created by zy on 2017/8/2.
 */

public interface HttpClientDefaultDeploy {

    /**
     * 设置请求方法
     */
    HttpExecuteHelper setApiMethod(String apiMethod);


    /**
     * 添加头信息
     *
     * @param name  头名称
     * @param value 头值
     * @return this
     */
    HttpExecuteHelper addHeader(String name, String value);

    /**
     * 添加参数
     *
     * @param name  参数名称
     * @param value 参数值
     * @return this
     */
    HttpExecuteHelper addParameter(String name, String value);

    /**
     * 添加参数
     *
     * @param param 参数实体(转换为HashMap数据)
     * @return
     */
    HttpExecuteHelper addParameter(Object param);

    /**
     * 获取数据编码集
     *
     * @return 编码集
     */
    String getCharset();

    /**
     * 执行post请求
     *
     * @return 返回数据
     */
    void execute(BaseHttpCallBack baseHttpCallBack);

    /**
     * 是否缓存
     *
     * @return
     */
    HttpClientDefaultDeploy setCache(boolean isCache);

}
