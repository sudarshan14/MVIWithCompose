package com.amlavati.mviwithcompose.api

class AnimalRepo(private val api: AnimalApi) {
    suspend fun getAnimals() = api.getJokes()
}