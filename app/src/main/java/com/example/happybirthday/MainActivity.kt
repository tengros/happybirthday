package com.example.happybirthday

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val glitterAnimation = findViewById<ImageView>(R.id.glitterAnimation)

        imageView.setOnClickListener {
            // Gör originalbilden osynlig
            imageView.visibility = View.INVISIBLE

            // Visa glitteranimationen
            glitterAnimation.visibility = View.VISIBLE

            // Ladda glitter-animationsbilderna
            glitterAnimation.setBackgroundResource(R.drawable.glitter_animation)
            val animation = glitterAnimation.background as AnimationDrawable

            // Starta glitteranimationen
            animation.start()

            // Spela ljudet
            mediaPlayer = MediaPlayer.create(this, R.raw.happy)
            mediaPlayer.start()

            // Återställ vyn efter 15 sekunder (15000 ms)
            Handler().postDelayed({
                resetView()
            }, 15000)
        }

        glitterAnimation.setOnClickListener {
            resetView()
        }
    }

    private fun resetView() {
        val imageView = findViewById<ImageView>(R.id.imageView)
        val glitterAnimation = findViewById<ImageView>(R.id.glitterAnimation)
        val textView = findViewById<TextView>(R.id.textView)

        // Återställ texten
        textView.text = "Grattis på födelsedagen Hugo!"

        // Återställ glitteranimationen
        glitterAnimation.visibility = View.INVISIBLE
        val animation = glitterAnimation.background as AnimationDrawable
        animation.stop()

        // Återställ bilden
        imageView.visibility = View.VISIBLE

        // Stoppa ljudet
        mediaPlayer.stop()
    }
}