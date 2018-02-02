package com.acv.marvel.data.repository

import arrow.core.Either
import com.acv.marvel.data.MarvelClient
import com.acv.marvel.data.map
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.NotInternetDomainError
import com.acv.marvel.domain.SuperHero
import com.acv.marvel.domain.UnknownDomainError
import retrofit2.Response
import java.net.ConnectException

class NetworkMarvelDataSource(val apiClient: MarvelClient) : MarvelDataSource {
    override fun populate(superHeroes: List<SuperHero>) {}

    companion object {
        const val RESPONSE_OK = 200
    }

    override fun getAll(): Either<DomainError, List<SuperHero>> =
            try {
                mapResponse(apiClient.getMarvel().execute()) { it.superheroes.map { it.map() } }
            } catch (exception: Exception) {
                Either.left(mapException(exception))
            }

    private fun <T, R> mapResponse(
            response: Response<T>,
            mapper: (T) -> R
    ): Either<DomainError, R> =
            when (response.code()) {
                RESPONSE_OK -> Either.right(mapper(response.body()!!))
                else -> Either.left(UnknownDomainError())
            }

    private fun mapException(exception: Exception): DomainError =
            when {
                exception.cause is ConnectException -> NotInternetDomainError
                else -> UnknownDomainError()
            }

}