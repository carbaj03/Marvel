package com.acv.marvel.presentation

import arrow.core.Either
import com.acv.marvel.domain.GetSuperHeroes
import com.acv.marvel.domain.NotInternetDomainError
import com.acv.marvel.domain.SuperHero
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class MarvelPresenterTest {

    lateinit var presenter: MarvelPresenter

    @Mock
    lateinit var view: MarvelPresenter.View

    @Mock
    lateinit var getSuperHeroes: GetSuperHeroes

    @Before
    fun setUp() {
        presenter = MarvelPresenter(view, getSuperHeroes)
    }

    @Test
    fun `should show loading when call usecase`() {
        whenever(getSuperHeroes()).thenReturn(Either.right(SUPERHEROES))

        presenter.loadMarvels()

        verify(view, times(1)).showLoading()
    }

    @Test
    fun `should load superheroes when call update`() {
        whenever(getSuperHeroes()).thenReturn(any())

        presenter.loadMarvels()

        verify(view, times(1)).showSuperHeroes(any())
    }

    @Test
    fun `should show empty message when list is empty`() {
        whenever(getSuperHeroes()).thenReturn(Either.right(listOf()))

        presenter.loadMarvels()

        verify(view, times(1)).showEmptyCase()
    }


    @Test
    fun `should show error`() {
        whenever(getSuperHeroes()).thenReturn(Either.left(NotInternetDomainError))

        presenter.loadMarvels()

        verify(view, times(1)).showError(any())
    }


    @Test
    fun `should show item clicked`() {
        val superhero = SUPERHERO

        presenter.onSuperHeroClicked(superhero)

        verify(view, times(1)).showSelect(superhero)
    }

    val SUPERHERO =
            SuperHeroView(
                    "Spiderman",
                    "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg",
                    "Peter Benjamin Parker",
                    "1.77m",
                    "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.",
                    "Peter is an accomplished scientist, inventor and photographer.",
                    "Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws"
            )


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