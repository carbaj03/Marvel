package com.acv.marvel.domain

import arrow.core.Either
import arrow.effects.IO
import arrow.effects.monadSuspend
import com.acv.marvel.data.repository.SuperHeroRepository

class GetSuperHeroes(private val superHeroesRepository: SuperHeroRepository) {
    operator fun invoke(): Either<DomainError, List<SuperHero>> =
            superHeroesRepository.getAllSuperHeroes()
}