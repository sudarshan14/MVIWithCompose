package com.amlavati.mviwithcompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amlavati.mviwithcompose.api.AnimalApi
import com.amlavati.mviwithcompose.api.AnimalRepo
import java.lang.IllegalStateException

class ViewModelFactory(private val api: AnimalApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((MainViewModel::class.java))) {
            return MainViewModel(AnimalRepo(api)) as T
        }
        throw IllegalStateException("Unknown class name")
    }
}