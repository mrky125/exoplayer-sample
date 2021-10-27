package com.example.exoplayersample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exoplayersample.databinding.ModalBottomSheetPlayerSettingBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerSettingModalBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ModalBottomSheetPlayerSettingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.also {
            it.tvQualitySetting.setOnClickListener {
                Log.d(TAG, "tapped quality setting")
            }
            it.tvPlaybackSpeed.setOnClickListener {
                Log.d(TAG, "tapped playback speed")
                val bottomSheet = PlayerPlaybackSpeedModalBottomSheet()
                bottomSheet.show(parentFragmentManager, "")
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    companion object {
        private const val TAG = "PlayerSettingModalBottomSheet"
    }
}