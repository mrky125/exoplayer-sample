package com.example.exoplayersample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {

    private val _fullScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val fullScreen = _fullScreen.asLiveData()

    // ex) 縦の時にボタンで全画面にしたら、fullScreen=true, orientationSensor=false （この時は縦のまま全画面で表示する）
    //  →画面を横にしたら、fullScreen=true, orientationSensor=true
    //  →縦に戻したら、fullScreen=false, orientationSensor=true
    private val _orientationSensor: MutableLiveData<Boolean> = MutableLiveData(true)
    val orientationSensor = _orientationSensor.asLiveData()

    private val _playbackParamSpeed: MutableLiveData<Float> = MutableLiveData(1f)
    val playbackParamSpeed = _playbackParamSpeed.asLiveData()

    fun updateScreenOrientation(isFullScreen: Boolean) {
        _fullScreen.value = isFullScreen
    }

    fun updateOrientationSensor(orientationSensor: Boolean) {
        _orientationSensor.value = orientationSensor
    }
}