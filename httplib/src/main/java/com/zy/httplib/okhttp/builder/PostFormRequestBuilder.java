package com.zy.httplib.okhttp.builder;

import com.zy.httplib.okhttp.HttpClientHelper;
import com.zy.httplib.okhttp.interfaces.HasParamsable;
import com.zy.httplib.okhttp.body.HttpPostFormRequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zy on 2017/8/2.
 */

public class PostFormRequestBuilder extends RequestBuilder<PostFormRequestBuilder> implements HasParamsable {

    private List<FileInput> files = new ArrayList<>();

    @Override
    public PostFormRequestBuilder setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (params == null) {
            return this;
        }
        params.put(key, val);
        return this;
    }

    @Override
    public HttpClientHelper build() {
        return new HttpPostFormRequestBody(url, tag, params, headers, id, files).build();
    }

    /**
     * 设置上传文件
     * @param key key
     * @param files 文件
     * @return
     */
    public PostFormRequestBuilder files(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    /**
     * 添加文件
     * @param name key
     * @param filename 文件名
     * @param file 文件
     * @return
     */
    public PostFormRequestBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }
}
