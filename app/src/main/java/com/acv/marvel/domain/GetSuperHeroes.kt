package com.acv.marvel.domain

import arrow.core.Either
import com.acv.marvel.data.repository.MarvelRepository

class GetSuperHeroes(val superHeroesRepository: MarvelRepository) {
    operator fun invoke(): Either<DomainError, List<SuperHero>> =
            superHeroesRepository.getAllSuperHeroes()
}