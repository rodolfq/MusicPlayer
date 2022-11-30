package com.rquintanilha.tutorial.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.rquintanilha.tutorial.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mediaplayer: MediaPlayer = MediaPlayer.create(this, R.raw.music)
        binding.seekbar.progress = 0
        binding.seekbar.max = mediaplayer.duration


        binding.playBtn.setOnClickListener() {
            if (!mediaplayer.isPlaying) {
                mediaplayer.start()

                binding.playBtn.setImageResource(R.drawable.ic_pause)
            } else {
                mediaplayer.pause()
                binding.playBtn.setImageResource(R.drawable.ic_play)
            }
        }
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed) {
                    mediaplayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        runnable = Runnable {
            binding.seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaplayer.setOnCompletionListener {
            binding.playBtn.setImageResource(R.drawable.ic_play)
            binding.seekbar.progress = 0
        }
    }

    override fun onClick(v: View?) {

    }
}