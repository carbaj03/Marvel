package com.acv.marvel.presentation;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.acv.marvel.R

class MarvelViewModel : ViewModel() {
    private lateinit var superheroes: MutableLiveData<List<SuperHero>>

    fun getSuperheroes(): LiveData<List<SuperHero>> = MutableLiveData()
}

data class SuperHero(
        val id: String,
        val name: String,
        val photo: String? = null,
        val isAvenger: Boolean,
        val description: String
) : ItemVisitable {
    override fun type() = R.layout.item_superhero
}

interface ItemVisitable {
    fun type(): Int
}