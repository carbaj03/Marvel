package com.acv.marvel.presentation;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MarvelViewModel : ViewModel() {
    private lateinit var superheroes: MutableLiveData<List<Superhero>>

    fun getSuperheroes(): LiveData<List<Superhero>> = MutableLiveData()
}

data class Superhero(
        val name: String,
        val photo: String,
        val realName: String,
        val height: String,
        val power: String,
        val abilities: String,
        val groups: String
)