package com.amlavati.mviwithcompose.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DemoViewModel : ViewModel() {

    private val _sendData = MutableStateFlow("")
    val sendData:StateFlow<String> = _sendData

    fun getData() {
        _sendData.value = " Updated Value"
    }

}