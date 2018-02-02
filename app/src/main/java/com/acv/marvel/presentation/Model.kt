package com.acv.marvel.presentation

import com.acv.marvel.R
import com.acv.marvel.domain.SuperHero


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