package com.example.exoplayersample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exoplayersample.databinding.FragmentPlayerParentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PlayerParentFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPlayerParentBinding.inflate(layoutInflater)
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
        setupFragment()
        setupBottomSheet()
    }

    private fun setupFragment() {
        val strVideoType = requireArguments().getString(ARGS_VIDEO_TYPE) ?: ""
        childFragmentManager.beginTransaction()
            .replace(binding.containerPlayer.id, PlayerFragment.newInstance(strVideoType))
            .replace(binding.containerChild.id, ChildFragment())
            .commit()
    }

    private fun setupBottomSheet() {
        val behavior = BottomSheetBehavior.from(binding.bottomSheet)
        Log.d(TAG, "behavior: ${behavior.state}")
        binding.bottomSheet.setOnClickListener {
            Log.d(TAG, "tapped, hideable: ${behavior.isHideable}, state: ${behavior.state}")
            // 描画範囲のうちプレーヤーを除いた、つまりプレーヤー下部から画面末尾までの高さを取得し、モーダルボトムシートの高さに設定する
            val modalBottomSheetPeekHeight = binding.coordinator.height
            val bottomSheet = PlaylistModalBottomSheet.newInstance(modalBottomSheetPeekHeight)
            bottomSheet.show(childFragmentManager, "")
        }
    }

    companion object {
        private const val TAG = "PlayerParentFragment"
        private const val ARGS_VIDEO_TYPE = "args_video_type"

        fun newInstance(strVideoType: String): PlayerParentFragment {
            return PlayerParentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGS_VIDEO_TYPE, strVideoType)
                }
            }
        }
    }

}