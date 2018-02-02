package com.acv.marvel.data.repository

import arrow.core.Either
import com.acv.marvel.domain.SuperHero
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Suppress("IllegalIdentifier")
class MarvelRepositoryTest {

    @Mock
    private lateinit var dataSource1: MarvelDataSource
    @Mock
    private lateinit var dataSource2: MarvelDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return get all data from the first datasource if info is updated`() {
        givenDataSourceWithData(dataSource1)
        val repository = MarvelRepository(listOf(dataSource1, dataSource2))

        repository.getAllSuperHeroes()

        verify(dataSource1, times(1)).getAll()
        verify(dataSource2, never()).getAll()
    }

    @Test
    fun `should return get all data from the second datasource if first is not updated`() {
        givenDataSourceWithOldData(dataSource1)
        givenDataSourceWithData(dataSource2)
        val repository = MarvelRepository(listOf(dataSource1, dataSource2))

        repository.getAllSuperHeroes()

        verify(dataSource1, never()).getAll()
        verify(dataSource2, times(1)).getAll()
    }

    @Test
    fun `should call populate with new data for each datasource after obtain getAll`() {
        givenDataSourceWithOldData(dataSource1)
        val superheroes = givenDataSourceWithData(dataSource2)
        val repository = MarvelRepository(listOf(dataSource1, dataSource2))

        repository.getAllSuperHeroes()

        verify(dataSource1, times(1)).populate(superheroes)
        verify(dataSource2, times(1)).populate(superheroes)
    }

    private fun givenDataSourceWithOldData(dataSource: MarvelDataSource) {
        whenever(dataSource.isUpdated()).thenReturn(false)
    }

    private fun givenDataSourceWithData(dataSource: MarvelDataSource): List<SuperHero> {
        val superheroes = listOf(ANY_SUPERHERO)
        whenever(dataSource.isUpdated()).thenReturn(true)
        whenever(dataSource.getAll()).thenReturn(Either.right(superheroes))
        whenever(dataSource.contains(any())).thenReturn(true)
        return superheroes
    }

    val ANY_SUPERHERO = SuperHero(
            "Spiderman",
            "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg",
            "Peter Benjamin Parker",
            "1.77m",
            "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.",
            "Peter is an accomplished scientist, inventor and photographer.",
            "Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws"
    )

    val ANY_NAME = "anyName"

}