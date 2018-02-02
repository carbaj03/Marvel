package com.acv.marvel.app.di.module

import com.acv.marvel.app.di.scope.ActivityScope
import com.acv.marvel.app.view.marvel.MarvelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MarvelActivityModule::class])
    @ActivityScope
    abstract fun contributeMarvelActivity(): MarvelActivity

}