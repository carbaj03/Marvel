package com.acv.marvel.data


import retrofit2.Call
import retrofit2.http.GET


interface MarvelClient {
    @GET("/bins/bvyob")
    fun getMarvel(): Call<ApiMarvelResponse>
}