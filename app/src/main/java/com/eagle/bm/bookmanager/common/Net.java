package com.eagle.bm.bookmanager.common;



import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Net {

    private static final Logger logger = Logger.getLogger(Net.class.getName());
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    public String get(String url) throws IOException{

        Request request = new Request.Builder()
                .addHeader("Content-Type", "text/html;charset=UTF-8")
                .addHeader("platform", "1")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public static String post(String url, String json) throws IOException {
        logger.info("[POST][" + url + "]" + json);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "text/html;charset=UTF-8")
                .addHeader("platform", "1")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
