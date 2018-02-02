package com.acv.marvel.domain

import arrow.core.Either
import arrow.core.getOrElse
import com.acv.marvel.data.repository.MarvelRepository
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GetSuperHeroesTest {

    private lateinit var useCase: GetSuperHeroes

    @Mock
    private lateinit var repository: MarvelRepository

    @Before
    fun setUp() {
        useCase = GetSuperHeroes(repository)
    }

    @Test
    fun `should return get all data from the repository`() {
        whenever(repository.getAllSuperHeroes()).thenReturn(Either.right(SUPERHEROES))

        useCase()

        verify(repository, times(1)).getAllSuperHeroes()
    }

    @Test
    fun `should return superheroes`() {
        whenever(repository.getAllSuperHeroes()).thenReturn(Either.right(SUPERHEROES))

        val superheroes = useCase()

        Assert.assertEquals(superheroes.getOrElse { UnknownDomainError() }, SUPERHEROES)
    }


    val SUPERHEROES = listOf(
            SuperHero(
                    "Spiderman",
                    "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg",
                    "Peter Benjamin Parker",
                    "1.77m",
                    "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.",
                    "Peter is an accomplished scientist, inventor and photographer.",
                    "Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws"

            ),
            SuperHero(
                    "Spiderman",
                    "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg",
                    "Peter Benjamin Parker",
                    "1.77m",
                    "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.",
                    "Peter is an accomplished scientist, inventor and photographer.",
                    "Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws"
            ))
}