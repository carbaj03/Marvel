package com.acv.marvel.domain


data class SuperHero(
        val id: String,
        val name: String,
        val photo: String? = null,
        val isAvenger: Boolean,
        val description: String
)