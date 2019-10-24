package com.acv.marvel.app.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import arrow.core.Either
import com.acv.marvel.R
import com.acv.marvel.app.recyclerview.RecyclerViewInteraction
import com.acv.marvel.app.recyclerview.RecyclerViewItemsCountMatcher.recyclerViewHasItemCount
import com.acv.marvel.app.view.marvel.MarvelActivity
import com.acv.marvel.data.repository.MarvelRepository
import com.acv.marvel.domain.DomainError
import com.acv.marvel.domain.SuperHero
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
@LargeTest
class MarvelActivityTest {
    private val ANY_NUMBER_OF_SUPER_HEROES = 10

    @get:Rule
    var activityRule = ActivityTestRule(MarvelActivity::class.java, false, false)

    @Mock
    lateinit var repository: MarvelRepository

    @Test
    fun `should show empty case if there are not superhero`() {
        givenThereAreNoSuperHeroes()

        startActivity()

        onView(withText("empty")).check(matches(isDisplayed()))
    }

    @Test
    fun `should show name when exist superheroes`() {
        val superHeroes = givenThereAreSomeSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES)

        startActivity()

        RecyclerViewInteraction.onRecyclerView<SuperHero>(withId(R.id.rvMarvel))
                .withItems(superHeroes.get())
                .check { superHero, view, e ->
                    matches(hasDescendant(withText(superHero.name))).check(view, e)
                }
    }


    @Test
    fun `should not show empty case when exist superheroes`() {
        givenThereAreSomeSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES)

        startActivity()

        onView(withId(R.id.tvEmptyCase)).check(matches(not<View>(isDisplayed())))
    }


    @Test
    fun `should show exact num of superheroes`() {
        givenThereAreSomeSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES)

        startActivity()

        onView(withId(R.id.rvMarvel)).check(
                matches(recyclerViewHasItemCount(ANY_NUMBER_OF_SUPER_HEROES)))
    }

    private fun givenThereAreSomeSuperHeroes(numberOfSuperHeroes: Int): Either<DomainError, List<SuperHero>> {
        val superHeroes = mutableListOf<SuperHero>()
        for (i in 0 until numberOfSuperHeroes) {

            val superHero = SuperHero("Spiderman$i",
                    "https://i.annihil.us/u/prod/marvel/i/mg/9/30/538cd33e15ab7/standard_xlarge.jpg",
                    "Peter Benjamin Parker$i",
                    "1.77m",
                    "Peter can cling to most surfaces, has superhuman strength (able to lift 10 tons optimally) and is roughly 15 times more agile than a regular human.",
                    "Peter is an accomplished scientist, inventor and photographer.",
                    "Avengers, formerly the Secret Defenders, \\\"New Fantastic Four\\\", the Outlaws")
            superHeroes.add(superHero)
        }
        val superhero = Either.right(superHeroes)
        whenever(repository.getAllSuperHeroes()).thenReturn(Either.right(superHeroes))
        return superhero
    }

    private fun givenThereAreNoSuperHeroes() =
            whenever(repository.getAllSuperHeroes()).thenReturn(Either.right(emptyList()))

    private fun startActivity(): MarvelActivity =
            activityRule.launchActivity(null)

}