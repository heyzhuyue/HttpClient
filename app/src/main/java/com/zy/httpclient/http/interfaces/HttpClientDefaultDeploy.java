package com.zy.httpclient.http.interfaces;

import com.zy.httpclient.http.BaseHttpCallBack;
import com.zy.httpclient.http.HttpExecuteHelper;
import com.zy.httpclient.http.enums.HttpClientMethod;

/**
 * Created by zy on 2017/8/2.
 */

public interface HttpClientDefaultDeploy {

    /**
     * 设置请求方法
     */
    HttpExecuteHelper setRequestMethod(HttpClientMethod httpClientMethod);

    /**
     * 连接超时时间
     *
     * @param connectTimeOut 单位：毫秒
     */
    HttpExecuteHelper setConnectTimeOut(int connectTimeOut);

    /**
     * 设置从主机读取数据超时
     *
     * @param readTimeOut 单位：毫秒
     */
    HttpExecuteHelper setReadTimeOut(int readTimeOut);

    /**
     * 设置读取数据时间
     *
     * @param writeTimeOut 单位:毫秒
     * @return
     */

    HttpExecuteHelper setWriteTimeOut(int writeTimeOut);


    /**
     * 设置网络请求接口方法
     *
     * @param apiMethod 接口方法
     * @return
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
}
