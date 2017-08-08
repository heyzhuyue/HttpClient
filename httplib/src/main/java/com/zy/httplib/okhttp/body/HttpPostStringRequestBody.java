package com.zy.httplib.okhttp.body;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zy on 2017/8/3.
 */

public class HttpPostStringRequestBody extends HttpRequestBody {

    /**
     * POST请求常见编码方式 (存在放在请求Header的Content-Type中) 文章链接(http://www.jianshu.com/p/0fd3d0e2d415)
     * 1.MediaType.parse("multipart/form-data;charset=utf-8")  multipart/form-data  使用表单上传文件,多用于文件上传
     * 2.MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8")  application/x-www-form-urlencoded  提交的数据按照 key1=val1&key2=val2 的方式进行编码，key 和 val 都进行了 URL 转码
     * 3.MediaType.parse("text/xml;charset=utf-8")  它是一种使用 HTTP 作为传输协议，XML 作为编码方式的远程调用规范
     * 4.MediaType.parse("application/json;charset=utf-8") application/json  服务端消息主体是序列化后的 JSON 字符串
     */
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
    private String content;
    private MediaType mediaType;

    public HttpPostStringRequestBody(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id, String content, MediaType mediaType) {
        super(url, tag, params, headers, id);
        this.content = content;
        this.mediaType = mediaType;
        if (this.content == null) {
            throw new RuntimeException();
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }
    }

    @Override
    RequestBody buildRequestBody() {
        return RequestBody.create(mediaType, content);
    }

    @Override
    Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
