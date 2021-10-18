package com.example.exoplayersample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.exoplayersample.databinding.FragmentPlayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import android.content.pm.ActivityInfo
import android.widget.ImageButton
import androidx.appcompat.content.res.AppCompatResources

class PlayerFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPlayerBinding.inflate(layoutInflater)
    }

    private val viewModel: PlayerViewModel by viewModels({ requireActivity() })

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d(TAG, "viewModel: $viewModel")
        setupFullScreenButton()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    private fun initializePlayer() {
        val trackSelector = DefaultTrackSelector(requireActivity()).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = SimpleExoPlayer.Builder(requireActivity())
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer

                // Using ProgressiveMediaSource
                // https://exoplayer.dev/progressive.html
                val dataSourceFactory = DefaultHttpDataSource.Factory()
                val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(getString(R.string.media_url_mp4)))
                exoPlayer.setMediaSource(mediaSource)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()
            }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun setupFullScreenButton() {
        val fullScreenBtn = binding.videoView.findViewById<ImageButton>(R.id.exo_fullscreen)
        viewModel.fullScreen.observe(viewLifecycleOwner) {
            Log.d(TAG, "fullScreen: $it")
            if (it) {
                fullScreenBtn.setImageDrawable(AppCompatResources.getDrawable(
                    requireActivity(),
                    R.drawable.exo_styled_controls_fullscreen_exit)
                )
            } else {
                fullScreenBtn.setImageDrawable(AppCompatResources.getDrawable(
                    requireActivity(),
                    R.drawable.exo_controls_fullscreen_enter)
                )
            }
        }
        fullScreenBtn.setOnClickListener {
            if (viewModel.fullScreen.value == true) {
                if (requireActivity().requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                    requireActivity().requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                ) {
                    Log.d(TAG, "sensor off")
                    viewModel.updateOrientationSensor(false)
                } else {
                    Log.d(TAG, "sensor on")
                    viewModel.updateOrientationSensor(true)
                }
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                viewModel.updateScreenOrientation(false)
                Log.d(TAG, "portrait")
            } else {
                if (requireActivity().requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    Log.d(TAG, "sensor off")
                    viewModel.updateOrientationSensor(false)
                } else {
                    Log.d(TAG, "sensor on")
                    viewModel.updateOrientationSensor(true)
                }
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                viewModel.updateScreenOrientation(true)
                Log.d(TAG, "landscape")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        player?.also {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
        }
        player = null
    }

    companion object {
        private const val TAG = "PlayerFragment"
    }
}