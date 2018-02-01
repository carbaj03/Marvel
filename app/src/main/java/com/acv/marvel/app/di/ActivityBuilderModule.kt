package com.acv.marvel.app.di

import com.acv.marvel.app.view.MarvelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MarvelActivityModule::class, FragmentBuilderModule::class])
    abstract fun contributeMarvelActivity(): MarvelActivity

}