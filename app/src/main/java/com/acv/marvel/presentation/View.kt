package com.acv.marvel.presentation

interface View {
        fun hideLoading()
        fun showSuperHeroes(superHeroes: List<SuperHeroView>)
        fun showLoading()
        fun showEmptyCase()
        fun showSelect(superHero: SuperHeroView)
        fun showError(error: String)
    }