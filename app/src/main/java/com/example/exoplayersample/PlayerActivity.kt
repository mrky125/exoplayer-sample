package com.example.exoplayersample

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.WindowInsets
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.exoplayersample.databinding.ActivityPlayerBinding
import android.content.pm.ActivityInfo

class PlayerActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    private val viewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "viewModel: $viewModel")
        val orientationEventListener: OrientationEventListener =
            object : OrientationEventListener(this) {
                private var lastOrientation: Int = 0

                private fun epsilonCheck(a: Int, b: Int, epsilon: Int = 10): Boolean {
                    return a > b - epsilon && a < b + epsilon
                }

                override fun onOrientationChanged(orientation: Int) {
                    Log.d(TAG, "$orientation")
                    val leftLandscape = 90
                    val rightLandscape = 270
                    lastOrientation = orientation
                    if ((epsilonCheck(orientation, leftLandscape) ||
                                epsilonCheck(orientation, rightLandscape))
                    ) {
                        if (viewModel.orientationSensor.value == true) {
                            Log.d(TAG, "landscape")
                            this@PlayerActivity.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            viewModel.updateScreenOrientation(true)
                        } else {
                            if (viewModel.fullScreen.value == true) {
                                Log.d(TAG, "turn on sensor")
                                viewModel.updateOrientationSensor(true)
                            } else {
                                Log.d(TAG, "keep off sensor")
                            }
                        }
                    } else if (orientation in 0..10 || orientation in 355..359) {
                        if (viewModel.orientationSensor.value == true) {
                            Log.d(TAG, "portrait")
                            this@PlayerActivity.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            viewModel.updateScreenOrientation(false)
                        } else {
                            if (viewModel.fullScreen.value == true) {
                                Log.d(TAG, "keep off sensor")
                            } else {
                                Log.d(TAG, "turn on sensor")
                                viewModel.updateOrientationSensor(true)
                            }
                        }
                    } else if (orientation == -1) {
                        // これだけだと縦の時にボタンで全画面にしたに-1にするだけでセンサー復活してしまう
                        // 回転角度を計算して、↑の場合（縦成分への傾きの場合）は無視するようにする
                        if (viewModel.fullScreen.value == true) {
                            if (epsilonCheck(lastOrientation, leftLandscape) ||
                                epsilonCheck(lastOrientation, rightLandscape)
                            ) {
                                Log.d(TAG, "-1, turn on sensor")
                                viewModel.updateOrientationSensor(true)
                            } else {
                                Log.d(TAG, "-1, nothing")
                            }
                        }
                    }
                }
            }
        orientationEventListener.enable()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            supportActionBar?.hide()
            binding.root.windowInsetsController?.hide(WindowInsets.Type.statusBars())
            viewModel.updateScreenOrientation(true)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
            supportActionBar?.show()
            binding.root.windowInsetsController?.show(WindowInsets.Type.statusBars())
            viewModel.updateScreenOrientation(false)
        }
    }

    companion object {
        private const val TAG = "PlayerActivity"
    }
}