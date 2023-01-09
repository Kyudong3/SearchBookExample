package com.kyudong3.searchbookexample.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kyudong3.searchbookexample.utils.provider.ApiProvider
import com.kyudong3.searchbookexample.utils.provider.impl.ApiProviderImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
object NetworkModule {

    private const val BASE_URL = "https://dapi.kakao.com"
    private const val TIME_OUT = 20_000L

    @Provides
    @Singleton
    internal fun provideGson(): Gson =
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .addInterceptor(interceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    @Singleton
    @Provides
    internal fun provideLoggingInterceptor() =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    internal fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    internal fun provideApiProvider(
        retrofit: Retrofit
    ): ApiProvider = ApiProviderImpl(retrofit)
}
