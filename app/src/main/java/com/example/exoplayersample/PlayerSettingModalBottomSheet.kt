package com.example.exoplayersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exoplayersample.databinding.ModalBottomSheetPlayerSettingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerSettingModalBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ModalBottomSheetPlayerSettingBinding.inflate(inflater, container, false).root
    }
}