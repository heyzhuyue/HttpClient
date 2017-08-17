package com.zy.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zy.httpclient.bean.WXItemBean;
import com.zy.httpclient.bean.WeiChatParam;
import com.zy.httpclient.constans.API;
import com.zy.httpclient.constans.Constant;
import com.zy.httpclient.http.BaseHttpCallBack;
import com.zy.httpclient.http.HttpClientManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTest(View view) {
        WeiChatParam weiChatParam = new WeiChatParam();
        weiChatParam.setKey(Constant.KEY_API);
        weiChatParam.setNum(20);
        weiChatParam.setPage(1);
        HttpClientManager.Builder builder = new HttpClientManager.Builder();
        builder.setApiMethod(API.WEICHAT_LIST_METHOD).build()
                .execute(weiChatParam, new BaseHttpCallBack<List<WXItemBean>>() {
                    @Override
                    protected void onNext(List<WXItemBean> list) {
                        Toast.makeText(MainActivity.this, list.get(0).getDescription(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onSubError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
