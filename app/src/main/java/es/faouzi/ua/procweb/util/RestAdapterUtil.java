package es.faouzi.ua.procweb.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
public class RestAdapterUtil {
    private static final String TAG = "RestAdapterUtil";
    public static <T> T createXmlAdapterFor(final Class<T> api, final String endpoint) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(new OkHttpClient()) // Use OkHttp3 client
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava adapter
                .addConverterFactory(SimpleXmlConverterFactory.create()) // Simple XML converter
                .build();
        return retrofit.create(api);
    }
}
