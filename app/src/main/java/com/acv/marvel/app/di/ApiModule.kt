package com.acv.marvel.app.di

import com.acv.marvel.BuildConfig
import com.acv.marvel.data.MarvelClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiUrl(): String = BuildConfig.API_URL_MARVEL

    @Provides
    @Singleton
    fun provideCmcClient(retrofit: Retrofit): MarvelClient =
            retrofit.create(MarvelClient::class.java)

    @Provides
    @Singleton
    fun provideRetrofitCmc(endPoint: String, okHttpClient: OkHttpClient): Retrofit =
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