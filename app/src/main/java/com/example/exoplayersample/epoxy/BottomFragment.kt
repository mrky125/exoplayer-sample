package com.example.exoplayersample.epoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.example.exoplayersample.databinding.FragmentBottomBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        setupShowingBottomSheet()
    }

    private fun setupShowingBottomSheet() {
        binding.root.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .hide(this)
                .commit()
            lifecycleScope.launch {
                delay(500)
                setFragmentResult("showBottomSheet", bundleOf())
            }
        }
    }

    companion object {
        private const val TAG = "BottomFragment"
    }
}