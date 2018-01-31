package com.acv.marvel.app.view

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.acv.marvel.R
import com.acv.marvel.app.load
import dagger.android.AndroidInjection


class MarvelActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        load<MarvelsFragment>()
    }
}
