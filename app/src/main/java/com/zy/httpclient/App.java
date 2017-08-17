package com.zy.httpclient;

import android.app.Application;

/**
 * Created by zy on 2017/8/17.
 */

public class App extends Application {

    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static App getInstance() {
        return INSTANCE;
    }
}
