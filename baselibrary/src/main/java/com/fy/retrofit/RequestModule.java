package com.fy.retrofit;

import android.text.TextUtils;

import com.fy.utils.ConstantUtils;
import com.fy.utils.L;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 提供依赖对象的实例
 * Created by fangs on 2017/5/15.
 */
@Module
public class RequestModule {

    @Singleton
    @Provides
    protected ApiService getService(RxJava2CallAdapterFactory callAdapterFactory, GsonConverterFactory
            gsonConverterFactory, OkHttpClient client) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(Api.BASE_URL)
                .client(client)
                .build()
                .create(ApiService.class);
    }

    @Singleton
    @Provides
    protected RxJava2CallAdapterFactory getCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    protected GsonConverterFactory getGsonConvertFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    protected OkHttpClient getClient(HttpLoggingInterceptor interceptor, Interceptor header) {
        return new OkHttpClient.Builder()
                .connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .writeTimeout(15000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(header)
                .build();
    }

    @Singleton
    @Provides
    protected HttpLoggingInterceptor getResponseIntercept() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                L.i("拦截请求与相应", message);

//                FileUtils.fileToInputContent("log", "日志.txt", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    protected Interceptor getHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = null;

                //获取request
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json;charse=UTF-8")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "application/json")
                        .addHeader("Cookie", "add cookies here")
                        .build();

                //获取request的创建者builder
                Request.Builder builder = request.newBuilder();

                //从request中获取headers，通过给定的键url_name
                List<String> headerValues = request.headers("url_name");
                if (headerValues != null && headerValues.size() > 0) {
                    //如果有这个header，先将配置的header删除，因为 此header仅用作app和okhttp之间使用
                    builder.removeHeader("url_name");

                    //匹配获得新的BaseUrl
                    String headerValue = headerValues.get(0);
                    HttpUrl newBaseUrl = null;
                    if ("user".equals(headerValue) && !TextUtils.isEmpty(ConstantUtils.custom_Url)) {
                        newBaseUrl = HttpUrl.parse(ConstantUtils.custom_Url);

                        //从request中获取原有的HttpUrl实例oldHttpUrl
                        HttpUrl oldHttpUrl = request.url();
                        //重建新的HttpUrl，修改需要修改的url部分
                        HttpUrl newFullUrl = oldHttpUrl
                                .newBuilder()
                                .scheme(newBaseUrl.scheme())
                                .host(newBaseUrl.host())
                                .port(newBaseUrl.port())
                                .build();

                        //重建这个request，通过builder.url(newFullUrl).build()；
                        //然后返回一个response至此结束修改
                        response = chain.proceed(builder.url(newFullUrl).build());
                    }
                }

                if (null == response) {
                    Request.Builder requestBuilder = request.newBuilder();

                    Request newRequest = requestBuilder.build();
                    response = chain.proceed(newRequest);
                }

                return response;
            }
        };
    }
}
