package com.example.goodplanet

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goodplanet.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


/**
 * tttt
 */
class MenuActivity : AppCompatActivity() {
    val VIDEO_ID = "wW8XlEmblQM"
    val YOUTUBE_API_KEY = "AIzaSyDFCpjgL65c50WOcpwOOHxIE-t_F47Le1c"
    private var binding: ActivityMenuBinding? = null

    private var youtubePlayer: YouTubePlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        //botones
        binding!!.btnEduc.setOnClickListener {
            Toast.makeText(this, "Te Explíco", Toast.LENGTH_LONG).show()
            val intento = Intent(this, MainActivity::class.java)
            startActivity(intento)
        }

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)

        val videoId = intent.getStringExtra("VIDEO_ID")
        val videoTitle = intent.getStringExtra("video_title")
        val videoDescription = intent.getStringExtra("video_description")




        binding!!.youtubePlayer.initialize(YOUTUBE_API_KEY,object:YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                youtubePlayer = p1
                youtubePlayer?.loadVideo(videoId)
                youtubePlayer?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Snackbar.make(binding!!.root, "Failed to initialize youtube player", Snackbar.LENGTH_SHORT).show()
            }
        })

    }







    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val REQUEST_IMAGE_CAPTURE = null
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            data?.extras?.let { bundle ->
                val imageBitmap = bundle.get("data") as Bitmap
                binding!!.shapeableImageView1.setImageBitmap(imageBitmap)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_inicio_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.salir -> {
            finish()
            true
        }
        R.id.action_search -> {
            Toast.makeText(this, R.string.txt_action_search, Toast.LENGTH_LONG).show()
            true
        }
        R.id.action_phone -> {
            Toast.makeText(this, R.string.txt_action_phone, Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun onPhone(item: android.view.MenuItem) {
        val intento = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "3168105790"))
        startActivity(intento)
    }


}
