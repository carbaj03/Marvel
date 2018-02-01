package com.acv.marvel.app.di

import com.acv.marvel.app.view.MarvelActivity
import com.acv.marvel.app.view.MarvelsFragment
import dagger.Module
import dagger.Provides

@Module
class MarvelFragmentModule {
    @Provides
    fun provideAccountView(marvelActivity: MarvelsFragment): MarvelsFragment = marvelActivity
}