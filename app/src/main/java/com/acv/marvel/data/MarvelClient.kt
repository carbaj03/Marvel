package com.acv.marvel.data


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET


interface MarvelClient {
    @GET("/bins/bvyob")
    fun getMarvel(): Call<Superhero>
}

class Superhero(
        @SerializedName("name") val name: String,
        @SerializedName("photo") val photo: String,
        @SerializedName("realName") val realName: String,
        @SerializedName("height") val height: String,
        @SerializedName("power") val power: String,
        @SerializedName("abilities") val abilities: String,
        @SerializedName("groups") val groups: String
)