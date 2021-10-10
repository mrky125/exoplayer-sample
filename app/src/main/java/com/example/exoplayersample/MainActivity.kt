package com.example.exoplayersample

import android.content.Intent
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
        binding.btnNavigateToPlayer.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }
}