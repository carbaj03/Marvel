package com.acv.marvel.app.view.marvel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.acv.marvel.R
import com.acv.marvel.app.common.color
import com.acv.marvel.app.common.gone
import com.acv.marvel.app.common.show
import com.acv.marvel.app.view.SuperHeroAdapter
import com.acv.marvel.app.view.common.DividerDecorationK
import com.acv.marvel.presentation.SuperHeroView
import com.acv.marvel.presentation.MarvelPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MarvelActivity : AppCompatActivity(), MarvelPresenter.View {

    private val superHeroAdapter
            by lazy { SuperHeroAdapter(holder = ::MarvelViewHolder) { itemClick(it) } }

    @Inject
    lateinit var presenter: MarvelPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        configToolbar(getString(R.string.title_marvel))
        setupRecyclerView()
        presenter.loadMarvels()
    }

    private fun setupRecyclerView() =
            with(rvMarvel) {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerDecorationK(context.color(R.color.colorPrimary), 1f))
                adapter = superHeroAdapter
            }

    private fun configToolbar(newTitle: String) =
            with(this) {
                setSupportActionBar(toolbar)
                supportActionBar!!.apply {
                    setDisplayShowTitleEnabled(true)
                    setDisplayHomeAsUpEnabled(true)
                    title = newTitle
                }
            }

    fun itemClick(superHero: SuperHeroView) =
            presenter.onSuperHeroClicked(superHero)

    override fun showSelect(superHero: SuperHeroView) =
            Snackbar.make(rvMarvel, superHero.name, Toast.LENGTH_SHORT).show()

    override fun showSuperHeroes(superHeroes: List<SuperHeroView>) =
            superHeroAdapter.add(superHeroes)

    override fun hideLoading() = progressBar.gone()

    override fun showLoading() = progressBar.show()

    override fun showEmptyCase() = tvEmptyCase.show()

    override fun showError(error: String) =
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
}
