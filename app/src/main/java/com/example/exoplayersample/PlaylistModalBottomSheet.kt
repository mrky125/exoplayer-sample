package com.example.exoplayersample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.exoplayersample.databinding.ModalBottomSheetPlaylistBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlaylistModalBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ModalBottomSheetPlaylistBinding.inflate(layoutInflater)
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
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        setupCustomDragBehavior(behavior)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupCustomDragBehavior(behavior: BottomSheetBehavior<View>) {
        binding.scrollView.setOnTouchListener { _, motionEvent ->
            // スクロール動作は以下のアクションの順番
            // ACTION_DOWN, ACTION_MOVE, ACTION_UP
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    // 指を離したときはボトムシートをドラッグ可能に戻す
                    behavior.isDraggable = true
                }
                else -> {
                    // スクロールビューに触れている間はドラッグ不可にする
                    behavior.isDraggable = false
                }
            }
            // タッチイベントを消化せず、上位クラスにてスクロールイベントを行う（こうしないとスクロールエリアがスクロールしない）
            false
        }
    }
}