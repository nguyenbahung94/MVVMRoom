package com.example.hungnb.demomvvm.di.module;

import android.app.Application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private final String TAG = NetworkModule.class.getSimpleName();
    private static final String BASE_URL = "https://dpguat.azure-api.net/";
    private static final String NAME_HEADER = "Ocp-Apim-Subscription-Key";
    private static final String VALUE_HEADER = "8421a295f02e44eaa395c739731164d2";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final int CONNECT_TIMEOUT = 30;

    @Singleton
    @Provides
    public Retrofit proRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public OkHttpClient proOkHttpClient(Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(proInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache);
        return builder.build();
    }

    @Singleton
    @Provides
    public Cache proCache(Application application) {
        return new Cache(application.getCacheDir(), CACHE_SIZE);
    }

    private Interceptor proInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response.newBuilder()
                        .build();
            }
        };
    }
}
