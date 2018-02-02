package com.acv.marvel.data.repository

import arrow.core.Either
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.SuperHero


class MarvelRepository(val datasources: List<MarvelDataSource>) {
    fun getAllSuperHeroes(): Either<DomainError, List<SuperHero>> {
        val all = datasources
                .first { it.isUpdated() }
                .getAll()

        if (all.isRight())
            all.get().let { list: List<SuperHero> -> datasources.forEach { it.populate(list) } }
        return all
    }
}