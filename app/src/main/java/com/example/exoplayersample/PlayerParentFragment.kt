package com.example.exoplayersample

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.exoplayersample.databinding.FragmentPlayerParentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.view.Gravity
import com.example.exoplayersample.epoxy.BottomFragment

class PlayerParentFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPlayerParentBinding.inflate(layoutInflater)
    }

    private var bottomFragment: BottomFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.also { fragmentManager ->
            fragmentManager.setFragmentResultListener("showBottomSheet", this) { _, _ ->
                // 描画範囲のうちプレーヤーを除いた、つまりプレーヤー下部から画面末尾までの高さを取得し、モーダルボトムシートの高さに設定する
                val modalBottomSheetPeekHeight = binding.coordinator.height
                val bottomSheet = PlaylistModalBottomSheet.newInstance(modalBottomSheetPeekHeight)
                bottomSheet.show(fragmentManager, "")
            }
            fragmentManager.setFragmentResultListener("showAgainBottomFragment", this) { _, _ ->
                // ボトムシートが閉じられたら、ボトムのFragmentを再表示する
                bottomFragment?.let {
                    fragmentManager.beginTransaction()
                        .show(it)
                        .commit()
                }
            }
        }
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
        setupBottomFragment()
    }

    private fun setupFragment() {
        val strVideoType = requireArguments().getString(ARGS_VIDEO_TYPE) ?: ""
        childFragmentManager.beginTransaction()
            .replace(binding.containerPlayer.id, PlayerFragment.newInstance(strVideoType))
            .replace(binding.containerChild.id, ChildFragment())
            .commit()
    }

    private fun setupBottomFragment() {
        lifecycleScope.launch {
            delay(2000)
            val slide = Slide().apply { slideEdge = Gravity.BOTTOM }
            val transitionSet = TransitionSet().apply { addTransition(slide) }
            bottomFragment = BottomFragment().apply {
                enterTransition = transitionSet
                exitTransition = transitionSet
            }
            childFragmentManager.beginTransaction()
                .replace(binding.containerBottom.id, bottomFragment!!)
                .commit()
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