package com.amlavati.mviwithcompose.api

import com.amlavati.mviwithcompose.model.JokesList
import retrofit2.http.GET

interface AnimalApi {

    @GET("jokes")
    suspend fun getJokes(): JokesList
}