package com.acv.marvel.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.acv.marvel.R
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.GetSuperHeroes
import com.acv.marvel.domain.SuperHero

class MarvelViewModel(val repo: GetSuperHeroes) : ViewModel() {
    private val superheroes: MutableLiveData<List<SuperHeroView>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    init {
        repo().fold(
                {
                    when (it) {
                        is DomainError -> error.value = "Domain"
                    }
                },
                {
                    superheroes.value = it.map { it.map() }
                }
        )
    }

    fun getSuperheroes(): LiveData<List<SuperHeroView>> = superheroes
    fun getError(): LiveData<String> = error
}

data class SuperHeroView(
        val name: String,
        val photo: String,
        val realName: String,
        val height: String,
        val power: String,
        val abilities: String,
        val groups: String
) : ItemVisitable {
    override fun type() = R.layout.item_superhero
}

fun SuperHero.map() = SuperHeroView(name, photo, realName, height, power, abilities, groups)

interface ItemVisitable {
    fun type(): Int
}

val fake = listOf(SuperHero(
        "Spiderman",
        "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg",
        "Peter Benjamin Parker",
        "1.77m",
        "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.",
        "Peter is an accomplished scientist, inventor and photographer.",
        "Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws"

))