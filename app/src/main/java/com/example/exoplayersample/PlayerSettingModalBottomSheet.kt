package com.example.exoplayersample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.exoplayersample.databinding.ModalBottomSheetPlayerSettingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerSettingModalBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ModalBottomSheetPlayerSettingBinding.inflate(layoutInflater)
    }
    private val viewModel: PlayerViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "viewModel: $viewModel")
        binding.viewModel = viewModel
    }

    companion object {
        private const val TAG = "PlayerSettingModalBottomSheet"
    }
}