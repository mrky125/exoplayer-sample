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

    private val viewModel: PlayerViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "viewModel: $viewModel")
        return ModalBottomSheetPlayerSettingBinding.inflate(inflater, container, false).root
    }

    companion object {
        private const val TAG = "PlayerSettingModalBottomSheet"
    }
}