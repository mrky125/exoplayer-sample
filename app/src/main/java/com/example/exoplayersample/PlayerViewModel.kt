package com.example.exoplayersample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {

    private val _fullScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val fullScreen = _fullScreen.asLiveData()

    fun updateScreenOrientation(isFullScreen: Boolean) {
        _fullScreen.value = isFullScreen
    }
}