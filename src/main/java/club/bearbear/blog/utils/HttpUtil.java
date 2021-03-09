package club.bearbear.blog.utils;

import okhttp3.*;

import java.io.IOException;

/**
 * ClassName HttpUtils
 *
 * @author Dylan
 * @description Http util
 * @createDate 2020-03-04 17:31
 */
public class HttpUtil {

    public static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    /**
     * 发送get请求
     *
     * @param url 路径
     * @return 字符串
     * @throws IOException
     */
    public static String getRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 发送post请求
     *
     * @param url  路径
     * @param json json字符串
     * @return 返回string
     * @throws IOException
     */
    public static String postRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json,MEDIA_TYPE);
        Request request = new Request.Builder().url(url).post(body).build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
