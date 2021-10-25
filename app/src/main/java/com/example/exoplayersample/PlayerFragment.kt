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
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player

class PlayerFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentPlayerBinding.inflate(layoutInflater)
    }

    private val viewModel: PlayerViewModel by viewModels({ requireActivity() })

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    private val playbackStateListener = playbackStateListener()

    private var videoType = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d(TAG, "viewModel: $viewModel")
        setupFullScreenButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoType = requireArguments().getString(ARGS_VIDEO_TYPE) ?: ""
        setupPlaybackSpeedButton()
        setupPlaybackSpeedChanging()
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
                    .createMediaSource(MediaItem.fromUri(getString(R.string.media_url_mp3)))
                exoPlayer.addMediaSource(mediaSource)
                if (videoType == "full") {
                    val mediaSource2 = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(getString(R.string.media_url_mp4)))
                    exoPlayer.addMediaSource(mediaSource2)
                }
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                viewModel.updateCurrentWindowIndex(currentWindow)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }
    }

    private fun setupPlaybackSpeedChanging() {
        viewModel.radioTypePlaybackSpeed.observe(viewLifecycleOwner) {
            Log.d(TAG, "selected speed: $it")
            player?.setPlaybackSpeed(it.float)
        }
    }

    private fun setupPlaybackSpeedButton() {
        val playbackSpeedBtn = binding.videoView.findViewById<ImageButton>(R.id.exo_playback_speed)
        playbackSpeedBtn.setOnClickListener {
            val bottomSheet = PlayerSettingModalBottomSheet()
            bottomSheet.show(childFragmentManager, "")
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

    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> {
                    if (videoType == "short") {
                        showPurchaseView()
                    }
                    "ExoPlayer.STATE_ENDED     -"
                }
                else -> "UNKNOWN_STATE             -"
            }
            Log.d(TAG, "changed state to $stateString")
        }

        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            super.onPositionDiscontinuity(oldPosition, newPosition, reason)
            Log.d(TAG, "reason: $reason, old window index: ${oldPosition.windowIndex}, " +
                    "new window index: ${newPosition.windowIndex}")
            viewModel.updateCurrentWindowIndex(newPosition.windowIndex)
            if (reason == Player.DISCONTINUITY_REASON_AUTO_TRANSITION) {
                Toast.makeText(
                    requireActivity(),
                    "playlist auto transited! ${oldPosition.windowIndex} to ${newPosition.windowIndex}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showPurchaseView() {
        binding.videoView.apply {
            findViewById<LinearLayout>(R.id.ll_playback_control_view).visibility = View.GONE
            findViewById<LinearLayout>(R.id.ll_purchase_view).visibility = View.VISIBLE
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
            it.removeListener(playbackStateListener)
            it.release()
        }
        player = null
    }

    companion object {
        private const val TAG = "PlayerFragment"
        private const val ARGS_VIDEO_TYPE = "args_video_type"

        fun newInstance(strVideoType: String): PlayerFragment {
            return PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGS_VIDEO_TYPE, strVideoType)
                }
            }
        }
    }
}