package com.acv.marvel.data.repository

import arrow.core.Either
import arrow.syntax.foldable.foldMap
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.SuperHero


class SuperHeroRepository(private val datasources: List<SuperHeroDataSource>) {
    fun getAllSuperHeroes(): Either<DomainError, List<SuperHero>> =
            datasources
                    .first { it.isUpdated() }
                    .getAll()
                    .apply { foldMap { list -> datasources.forEach { it.populate(list) } } }

    fun getByName(key: String): Either<DomainError, SuperHero> =
            datasources
                    .first { it.isUpdated() && it.contains(key) }
                    .get(key)
}