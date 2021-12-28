package com.example.exoplayersample

import androidx.databinding.InverseMethod

enum class PlaybackSpeed(val float: Float) {
    X0_25(0.25f),
    X0_50(0.5f),
    X0_75(0.75f),
    NORMAL(1.0f),
    X1_25(1.25f),
    X1_50(1.5f),
    X1_75(1.75f),
    X2_00(2.0f);

    companion object {
        @JvmStatic
        @InverseMethod("buttonIdToType")
        fun typeToButtonId(radioType: PlaybackSpeed): Int {
            return when (radioType) {
                X0_25 -> R.id.rbSpeed0_25
                X0_50 -> R.id.rbSpeed0_50
                X0_75 -> R.id.rbSpeed0_75
                NORMAL -> R.id.rbSpeedNormal
                X1_25 -> R.id.rbSpeed1_25
                X1_50 -> R.id.rbSpeed1_50
                X1_75 -> R.id.rbSpeed1_75
                X2_00 -> R.id.rbSpeed2_00
            }
        }

        @JvmStatic
        fun buttonIdToType(selectedButtonId: Int): PlaybackSpeed? {
            return when (selectedButtonId) {
                R.id.rbSpeed0_25 -> X0_25
                R.id.rbSpeed0_50 -> X0_50
                R.id.rbSpeed0_75 -> X0_75
                R.id.rbSpeedNormal -> NORMAL
                R.id.rbSpeed1_25 -> X1_25
                R.id.rbSpeed1_50 -> X1_50
                R.id.rbSpeed1_75 -> X1_75
                R.id.rbSpeed2_00 -> X2_00
                else -> null
            }
        }
    }
}