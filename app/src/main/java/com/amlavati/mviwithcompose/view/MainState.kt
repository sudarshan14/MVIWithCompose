package com.amlavati.mviwithcompose.view

import com.amlavati.mviwithcompose.model.JokesList

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Animals(val jokes: JokesList) : MainState()

    data class Error(val error: String?) : MainState()

}