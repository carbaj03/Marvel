package com.acv.marvel.app.di.module

import com.acv.marvel.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    @Named("EndPoint")
    fun provideApiUrl(): String = BuildConfig.API_URL_MARVEL

    @Provides
    @Singleton
    fun provideRetrofit(
            @Named("EndPoint") endPoint: String,
            okHttpClient: OkHttpClient
    ): Retrofit =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(endPoint)
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().apply { addInterceptor(loggingInterceptor) }.build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
}