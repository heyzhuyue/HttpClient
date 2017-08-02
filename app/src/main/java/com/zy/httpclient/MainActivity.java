package com.zy.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zy.httpclient.bean.WXItemBean;
import com.zy.httpclient.bean.WeiChatParam;
import com.zy.httpclient.constans.Constants;
import com.zy.httpclient.http.BaseHttpCallBack;
import com.zy.httpclient.http.HttpClientManager;
import com.zy.httpclient.http.enums.HttpClientMethod;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTest(View view) {
        WeiChatParam weiChatParam = new WeiChatParam();
        weiChatParam.setKey(Constants.KEY_API);
        weiChatParam.setNum(20);
        weiChatParam.setPage(1);
        HttpClientManager clientManager = new HttpClientManager.Builder().setHttpClientMethond(HttpClientMethod.GET).setApiMethod("wxnew").build();
        clientManager.execute(weiChatParam, new BaseHttpCallBack<List<WXItemBean>>() {

            @Override
            public void onNext(List<WXItemBean> response) {
                Toast.makeText(MainActivity.this, response.get(0).getUrl(), Toast.LENGTH_SHORT).
                        show();
            }

            @Override
            public void onSubError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
