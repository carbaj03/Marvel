package com.acv.marvel.data.repository

import arrow.core.Either
import com.acv.marvel.app.common.TimeProvider
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.SuperHero
import java.util.concurrent.TimeUnit

class MemoryMarvelDataSource(val timeProvider: TimeProvider) : MarvelDataSource {
    companion object {
        private val TIME_UPDATE = TimeUnit.SECONDS.toMillis(1)
    }

    private val cache = LinkedHashMap<String, SuperHero>()
    private var lastUpdate = 0L

    override fun isUpdated(): Boolean = timeProvider.time - lastUpdate < TIME_UPDATE

    override fun contains(key: String): Boolean = cache.contains(key)

    override fun getAll(): Either<DomainError, List<SuperHero>> =
            Either.right(ArrayList(cache.values))

    override fun populate(superHeroes: List<SuperHero>) {
        lastUpdate = timeProvider.time
        cache.putAll(superHeroes.map { it.name to it })
    }

}