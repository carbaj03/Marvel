package com.acv.marvel.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.acv.marvel.R
import dagger.android.AndroidInjection

class MarvelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        load<MarvelsFragment>()
    }
}
