package com.example.exoplayersample

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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
        // ボトムシートの外側の背景透過をただの透明にする
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        setupCustomDragBehavior(behavior)
        // 画面内表示のプレーヤーの下部にフィットするよう`peekHeight`を設定する
        val peekHeight = requireArguments().getInt(ARGS_PEEK_HEIGHT)
        Log.d(TAG, "height: $peekHeight")
        behavior.peekHeight = peekHeight
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

    override fun onDismiss(dialog: DialogInterface) {
        setFragmentResult("showAgainBottomFragment", bundleOf())
        super.onDismiss(dialog)
    }

    companion object {
        private const val TAG = "PlaylistModalBottomSheet"
        private const val ARGS_PEEK_HEIGHT = "args_peek_height"

        fun newInstance(peekHeight: Int): PlaylistModalBottomSheet {
            return PlaylistModalBottomSheet().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_PEEK_HEIGHT, peekHeight)
                }
            }
        }
    }
}