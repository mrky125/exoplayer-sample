package com.example.exoplayersample.epoxy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exoplayersample.databinding.FragmentBottomBinding

class BottomFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentBottomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomSheet()
    }

    private fun setupBottomSheet() {
        binding.root.setOnClickListener {
            Log.d(TAG, "tapped bottom!")
        }
    }

    companion object {
        private const val TAG = "BottomFragment"
    }
}