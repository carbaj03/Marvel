package com.acv.marvel.app.di

import com.acv.marvel.app.view.MarvelActivity
import dagger.Module
import dagger.Provides

@Module
class MarvelActivityModule {
    @Provides
    fun provideAccountView(marvelActivity: MarvelActivity): MarvelActivity = marvelActivity


}