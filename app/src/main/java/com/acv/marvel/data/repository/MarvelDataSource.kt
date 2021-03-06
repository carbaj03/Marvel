package com.acv.marvel.data.repository

import arrow.core.Either
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.SuperHero

interface MarvelDataSource {
    fun getAll(): Either<DomainError, List<SuperHero>>
    fun isUpdated(): Boolean = true
    fun populate(superHeroes: List<SuperHero>)
    fun contains(key: String): Boolean = true
}