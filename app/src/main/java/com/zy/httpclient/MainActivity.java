package com.zy.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zy.httpclient.bean.WeiChatParam;
import com.zy.httpclient.constans.Constants;
import com.zy.httpclient.utils.AppUtils;
import com.zy.httplib.okhttp.callback.StringCallBack;
import com.zy.httplib.utils.HttpUtils;

import java.util.Map;

import okhttp3.Call;

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
//        HttpClientManager clientManager = new HttpClientManager.Builder().setHttpClientMethond(HttpClientMethod.GET).setApiMethod("wxnew").build();
//        clientManager.execute(weiChatParam, new BaseHttpCallBack<List<WXItemBean>>() {
//
//            @Override
//            public void onNext(List<WXItemBean> response) {
//                Toast.makeText(MainActivity.this, response.get(0).getUrl(), Toast.LENGTH_SHORT).
//                        show();
//            }
//
//            @Override
//            public void onSubError(String message) {
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(String message) {
//                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//            }
//        });
        Map<String, String> map = AppUtils.beanToMap(weiChatParam);
        HttpUtils.getInstance().get().setUrl(Constants.URL + "wxnew").params(map).build().execute(new StringCallBack() {
            @Override
            protected void onCacheResponse(String response, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("数据", response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });

    }
}
