package com.urwoo.tools;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Slf4j
public class OkHttpClientTools {

    private static Logger logger = LoggerFactory.getLogger(OkHttpClientTools.class);

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = null;

    static {
        client = new OkHttpClient();
    }

    private static class LazyHolder {
        private static final OkHttpClientTools INSTANCE = new OkHttpClientTools();
    }

    private OkHttpClientTools() {
    }

    public static final OkHttpClientTools getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String get(String url, Map<String, Object> param) throws IOException {
        return get(url, null, param);
    }

    public String get(String url, Headers headers, Map<String, Object> param) throws IOException {
        StringBuffer urlBuffer = new StringBuffer(url);
        urlBuffer.append(buildUrl(param));

        Request.Builder builder = new Request.Builder().url(urlBuffer.toString()).get();
        if (ObjectTools.nonNull(headers)){
            builder.headers(headers);
        }
        Request request = builder.build();
        Instant start = Instant.now();
        Response response = client.newCall(request).execute();
        Instant  end = Instant .now();
        logger.info("url={}, param={}, qt={}", url, param,
                Duration.between(start, end).toMillis());
        if (ObjectTools.nonNull(response) &&
                ObjectTools.nonNull(response.body()))
            return response.body().string();
        return null;
    }

    public String post(String url, Map<String, Object> param) throws IOException {
        return post(url, null, param);
    }

    public String post(String url, Headers headers, Map<String, Object> param) throws IOException {

        Request.Builder builder = new Request.Builder()
                .url(url).post(buildForm(param));
        if (ObjectTools.nonNull(headers)){
            builder.headers(headers);
        }
        Request request = builder.build();
        Instant start = Instant.now();
        Response response = client.newCall(request).execute();
        Instant  end = Instant .now();
        logger.info("url={}, param={}, qt={}", url, param,
                Duration.between(start, end).toMillis());
        if (ObjectTools.nonNull(response) &&
                ObjectTools.nonNull(response.body()))
            return response.body().string();
        return null;
    }

    public String postJson(String url, String json) throws IOException{
        return postJson(url, null, json);
    }

    public String postJson(String url, Headers headers, String json) throws IOException{

        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder().url(url).post(body);
        if (ObjectTools.nonNull(headers)){
            builder.headers(headers);
        }
        Request request = builder.build();
        Instant  start = Instant .now();
        Response response = client.newCall(request).execute();
        Instant  end = Instant .now();
        logger.info("url={}, bodyJson={}, qt={}", url, json,
                Duration.between(start, end).toMillis());
        if (ObjectTools.nonNull(response) &&
                ObjectTools.nonNull(response.body()))
            return response.body().string();
        return null;
    }

    /**
     * 创建formBody
     * @param param
     * @return
     */
    private FormBody buildForm(Map<String, Object> param) {
        FormBody.Builder builder = new FormBody.Builder();
        if (MapTools.nonNull(param)) {
            Set<Map.Entry<String, Object>> entrySet = param.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object o = entry.getValue();
                if (StringTools.nonNullAndEmpty(key)
                        && ObjectTools.nonNull(o)) {
                    builder.add(key, (String) o);
                }else {
                    continue;
                }
            }
        }
        return builder.build();
    }

    /**
     * 生成get URL
     * @param param 请求参数
     * @return
     */
    private String buildUrl(Map<String, Object> param) {

        StringBuffer stringBuffer = new StringBuffer();
        if (MapTools.nonNull(param)) {
            stringBuffer.append("?");
            Set<Map.Entry<String, Object>> entrySet = param.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object o = entry.getValue();
                if (StringTools.nonNullAndEmpty(key)
                        && ObjectTools.nonNull(o)) {
                    if (o instanceof Collection){
                        Collection collection = (Collection) o;
                        StringBuilder listStr = new StringBuilder();
                        collection.forEach(c->listStr.append(c).append(","));
                        if (listStr.length() > 0){
                            listStr.deleteCharAt(listStr.length()-1);
                        }
                        stringBuffer.append(key).append("=").append(listStr);
                    } else {
                        stringBuffer.append(key).append("=").append(o).append("&");
                    }
                } else {
                    continue;
                }
            }
        }
        return stringBuffer.toString();
    }
}
