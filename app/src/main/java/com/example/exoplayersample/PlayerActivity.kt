package com.example.exoplayersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exoplayersample.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}