package com.amlavati.mviwithcompose.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimalService {
    const val BASE_URL = "https://amlavati.com/sqltutorial/v1/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: AnimalApi = getRetrofit().create(AnimalApi::class.java)
}