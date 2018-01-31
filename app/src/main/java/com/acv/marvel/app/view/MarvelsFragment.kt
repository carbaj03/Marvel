package com.acv.marvel.app.view

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.acv.marvel.R
import com.acv.marvel.app.viewModelProviders
import com.acv.marvel.presentation.MarvelViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class MarvelsFragment : BaseFragment() {
    private val superHeroAdapter
            by lazy { SuperHeroAdapter(holder = ::SuperHeroViewHolder) { goToDetail(it) } }

    private val model
            by lazy { viewModelProviders<MarvelViewModel>() }

    override fun getLayout() =
            R.layout.fragment_list

    override fun onCreate() {
        configToolbar(getString(R.string.title_marvel))
    }

    private fun configToolbar(newTitle: String) =
            with(activity as AppCompatActivity) {
                setSupportActionBar(toolbar)
                supportActionBar!!.apply {
                    setDisplayShowTitleEnabled(true)
                    setDisplayHomeAsUpEnabled(true)
                    title = newTitle
                }
            }

    private fun goToDetail(superHero: SuperHero) =
            Toast.makeText(context, "Click", Toast.LENGTH_LONG).show()
//            load<SkillDetailActivity>(SKILL to superHero.id, TITLE to superHero.name)

}