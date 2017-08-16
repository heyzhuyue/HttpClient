package com.zy.httplib.utils;

/**
 * Created by zy on 2017/8/16.
 */

public class LoggingSwitch {

    /**
     * 初始化日志开关
     *
     * @param isOpen 是否开始日志
     */
    public static void initLogging(boolean isOpen) {
        HttpUtils.setIsOpenLogging(isOpen);
    }

    /**
     * 设置读取超时时间
     *
     * @param readTimeOut 读取超时时间
     */
    public static void initReadTimeOut(long readTimeOut) {
        HttpUtils.setReadTimeOut(readTimeOut);
    }

    /**
     * 设置读取超时时间
     *
     * @param writeTimeOut 读取超时时间
     */
    public static void initWriteTimeOut(long writeTimeOut) {
        HttpUtils.setWriteTimeOut(writeTimeOut);
    }

    /**
     * 设置连接超时时间
     *
     * @param connTimeOut 连接超时时间
     */
    public static void initConnTimeOut(long connTimeOut) {
        HttpUtils.setConnTimeOut(connTimeOut);
    }
}
