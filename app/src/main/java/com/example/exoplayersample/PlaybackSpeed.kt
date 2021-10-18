package com.example.exoplayersample

import androidx.databinding.InverseMethod

enum class PlaybackSpeed {
    X_05,
    NORMAL,
    X_15;

    companion object {
        @JvmStatic
        @InverseMethod("buttonIdToType")
        fun typeToButtonId(radioType: PlaybackSpeed): Int {
            return when (radioType) {
                X_05 -> R.id.rbSpeed05
                NORMAL -> R.id.rbSpeedNormal
                X_15 -> R.id.rbSpeed15
            }
        }

        @JvmStatic
        fun buttonIdToType(selectedButtonId: Int): PlaybackSpeed? {
            return when (selectedButtonId) {
                R.id.rbSpeed05 -> X_05
                R.id.rbSpeedNormal -> NORMAL
                R.id.rbSpeed15 -> X_15
                else -> null
            }
        }
    }
}