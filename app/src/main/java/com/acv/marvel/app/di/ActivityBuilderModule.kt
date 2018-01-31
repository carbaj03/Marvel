package com.acv.marvel.app.di

import com.acv.marvel.app.MarvelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MarvelActivityModule::class])
    abstract fun contributeMarvelActivity(): MarvelActivity

}