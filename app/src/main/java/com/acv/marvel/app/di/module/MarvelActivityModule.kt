package com.acv.marvel.app.di.module

import com.acv.marvel.app.di.scope.ActivityScope
import com.acv.marvel.app.view.marvel.MarvelActivity
import com.acv.marvel.data.repository.MarvelRepository
import com.acv.marvel.domain.GetSuperHeroes
import com.acv.marvel.presentation.MarvelPresenter
import dagger.Module
import dagger.Provides

@Module
class MarvelActivityModule {

    @Provides
    @ActivityScope
    fun provideAccountView(marvelActivity: MarvelActivity): MarvelPresenter.View = marvelActivity

    @Provides
    @ActivityScope
    fun provideMarvelPresenter(
            view: MarvelPresenter.View,
            getRandomUsers: GetSuperHeroes
    ): MarvelPresenter =
            MarvelPresenter(view, getRandomUsers)

    @Provides
    @ActivityScope
    fun provideGetRandomUsers(repository: MarvelRepository): GetSuperHeroes =
            GetSuperHeroes(repository)
}