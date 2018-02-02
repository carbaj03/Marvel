package com.acv.marvel.data

import com.acv.marvel.domain.SuperHero
import com.google.gson.annotations.SerializedName



data class ApiMarvelResponse(
        @SerializedName("superheroes")
        var superheroes: List<MarvelResponse>
)

data class MarvelResponse(
        @SerializedName("name") val name: String,
        @SerializedName("photo") val photo: String,
        @SerializedName("realName") val realName: String,
        @SerializedName("height") val height: String,
        @SerializedName("power") val power: String,
        @SerializedName("abilities") val abilities: String,
        @SerializedName("groups") val groups: String
)

fun MarvelResponse.map() = SuperHero(name, photo, realName, height, power, abilities, groups)