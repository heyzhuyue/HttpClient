package com.zy.httplib.okhttp.body;

import com.zy.httplib.okhttp.builder.PostFormRequestBuilder;
import com.zy.httplib.okhttp.callback.BaseCallBack;

import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/3.
 * POST请求FORM(支持文件与其他参数一同上传)
 */

public class HttpPostFormRequestBody extends HttpRequestBody {

    private List<PostFormRequestBuilder.FileInput> files;

    public HttpPostFormRequestBody(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id, List<PostFormRequestBuilder.FileInput> files) {
        super(url, tag, params, headers, id);
        this.files = files;
    }

    @Override
    RequestBody buildRequestBody() {
        if (files == null || files.isEmpty()) { //不存在文件
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            return builder.build();
        } else { //存在文件
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            addParams(builder);
            for (int i = 0; i < files.size(); i++) {
                PostFormRequestBuilder.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final BaseCallBack callback) {
        if (callback == null) return requestBody;
        return new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {
                callback.loadProgress(bytesWritten * 1.0f / contentLength, contentLength, id);
            }
        });
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    /**
     * 编码文件名称
     *
     * @param fileName 文件名字
     * @return
     */
    private String guessMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 添加参数(带文件)
     *
     * @param builder MultipartBody.Builder
     */
    private void addParams(MultipartBody.Builder builder) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        }
    }

    /**
     * 添加其他参数(不带文件)
     *
     * @param builder FormBody.Builder
     */
    private void addParams(FormBody.Builder builder) {
        if (params != null) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }

}
