package com.example.goodplanet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.goodplanet.databinding.ActivityImageDetailBinding



class ImageDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        if(intent.extras != null){
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(binding.photoview)
        }
    }
}