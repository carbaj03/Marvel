package com.acv.marvel.app.view

import android.support.v7.app.AppCompatActivity
import com.acv.marvel.R
import com.acv.marvel.app.viewModelProviders
import com.acv.marvel.presentation.MarvelViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class MarvelsFragment : BaseFragment() {

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
}