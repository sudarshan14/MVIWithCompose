package com.amlavati.mviwithcompose.model

data class Jokes(
    val title: String = "",
    val message: String = "",
    val image: String = ""
)

data class JokesList(
    val jokes: List<Jokes>
)
