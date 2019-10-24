package com.acv.marvel.presentation

import android.util.Log
import arrow.core.Either
import co.metalab.asyncawait.async
import com.acv.marvel.app.common.weak
import com.acv.marvel.domain.GetSuperHeroes
import com.acv.marvel.domain.SuperHero

class MarvelPresenter(
        view: View,
        private val getSuperHeroes: GetSuperHeroes
) {
    private val view: View? by weak(view)

    fun loadMarvels() {
        view?.showLoading()
        refreshMarvel()
    }

    private fun refreshMarvel() = async {
        val result = await { getSuperHeroes() }
        view?.hideLoading()
        when (result) {
            is Either.Right -> await { showSuperHeroes(result.b) }
            is Either.Left -> view?.showError(result.a.error)
        }
    }.onError {
        Log.e("error", it.message)
        view?.showError("Error")
    }

    private fun showSuperHeroes(result: List<SuperHero>) =
            when {
                result.isEmpty() -> view?.showEmptyCase()
                else -> view?.showSuperHeroes(result.map { it.map() })
            }

    fun onSuperHeroClicked(superHero: SuperHeroView) = view?.showSelect(superHero)

  
}