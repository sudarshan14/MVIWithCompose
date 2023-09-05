package com.amlavati.mviwithcompose.presenter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DemoPresenter {
    private val _sendData = MutableStateFlow("")
    val sendData: StateFlow<String> = _sendData

    fun getData() {
        _sendData.value = " Updated Value"
    }
}