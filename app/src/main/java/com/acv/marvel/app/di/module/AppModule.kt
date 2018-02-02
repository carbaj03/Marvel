package com.acv.marvel.app.di.module

import android.app.Application
import android.content.Context
import com.acv.marvel.app.common.RealTimeProvider
import com.acv.marvel.app.common.TimeProvider
import com.acv.marvel.data.MarvelClient
import com.acv.marvel.data.repository.MemoryMarvelDataSource
import com.acv.marvel.data.repository.NetworkMarvelDataSource
import com.acv.marvel.data.repository.MarvelDataSource
import com.acv.marvel.data.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application


    @Provides
    @Singleton
    fun provideRandomUserRepository(
            @Named("Network") network: MarvelDataSource,
            @Named("Local") localStorage: MarvelDataSource
    ): MarvelRepository =
            MarvelRepository(listOf(localStorage,network))

    @Provides
    @Singleton
    @Named("Local")
    fun provideLocalStorage(timeProvider: TimeProvider): MarvelDataSource =
            MemoryMarvelDataSource(timeProvider)

    @Provides
    @Singleton
    @Named("Network")
    fun provideRandomUserGateway(apiClient: MarvelClient): MarvelDataSource =
            NetworkMarvelDataSource(apiClient)

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): MarvelClient =
            retrofit.create<MarvelClient>(MarvelClient::class.java)

    @Provides
    @Singleton
    fun provideTimeProvider(): TimeProvider = RealTimeProvider()


}