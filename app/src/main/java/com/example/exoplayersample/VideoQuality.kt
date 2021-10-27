package com.example.exoplayersample

import androidx.databinding.InverseMethod

enum class VideoQuality(val int: Int) {
    AUTO(1463),
    HIGH(2102),
    LOW(303);

    companion object {
        @JvmStatic
        @InverseMethod("buttonIdToQualityType")
        fun qualityTypeToButtonId(radioType: VideoQuality): Int {
            return when (radioType) {
                AUTO -> R.id.rbAuto
                HIGH -> R.id.rbHigh
                LOW -> R.id.rbLow
            }
        }

        @JvmStatic
        fun buttonIdToQualityType(selectedButtonId: Int): VideoQuality? {
            return when (selectedButtonId) {
                R.id.rbAuto -> AUTO
                R.id.rbHigh -> HIGH
                R.id.rbLow -> LOW
                else -> null
            }
        }
    }
}