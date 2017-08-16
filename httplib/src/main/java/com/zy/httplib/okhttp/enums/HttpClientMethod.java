package com.zy.httplib.okhttp.enums;

/**
 * Created by zy on 2017/8/16.
 */

public enum HttpClientMethod {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    HEAD("HEAD");

    private String value;

    HttpClientMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static boolean permitsRetry(HttpClientMethod method) {
        return method == GET;
    }

    public static boolean permitsCache(HttpClientMethod method) {
        return method == GET || method == POST;
    }

    public static boolean permitsRequestBody(HttpClientMethod method) {
        return method == POST
                || method == PUT
                || method == PATCH
                || method == DELETE;
    }
}
