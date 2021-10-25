package com.example.exoplayersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exoplayersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActivityNavigation()
    }

    private fun setupActivityNavigation() {
        binding.also {
            it.btnNavigateToFullVideo.setOnClickListener {
                startActivity(PlayerActivity.newIntent(this, "full"))
            }
            it.btnNavigateToShortVideo.setOnClickListener {
                startActivity(PlayerActivity.newIntent(this, "short"))
            }
        }
    }
}