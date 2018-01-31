package com.acv.marvel.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.acv.marvel.R

class MarvelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load<MarvelsFragment>()
    }
}
