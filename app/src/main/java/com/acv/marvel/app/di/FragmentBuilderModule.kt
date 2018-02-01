package com.acv.marvel.app.di

import com.acv.marvel.app.view.MarvelsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = [MarvelFragmentModule::class])
    abstract fun contributeMarvelFragment(): MarvelsFragment


}
