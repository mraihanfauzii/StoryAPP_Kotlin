package com.example.submission_awal_intermediate_androidview.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.submission_awal_intermediate_androidview.databinding.ActivityDetailBinding
import com.jakewharton.threetenabp.AndroidThreeTen

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AndroidThreeTen.init(this)

        val createdAt = intent.getStringExtra(CREATEDAT)
        val photoUrl = intent.getStringExtra(PHOTOURL)
        val name = intent.getStringExtra(NAME)
        val storyDescription = intent.getStringExtra(STORYDESCRIPTION)

        supportActionBar?.title = "StoryDetail"
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(photoUrl)
                .centerCrop()
                .into(ivPhotoUrl)
            tvStoryCreatedAt.text = DateFormatter.formatDate(createdAt, "Asia/Jakarta")
            tvUserName.text = name
            tvStoryDescription.text = storyDescription
        }
    }
    companion object {
        const val CREATEDAT = "createdAt"
        const val PHOTOURL = "photoUrl"
        const val NAME = "name"
        const val STORYDESCRIPTION = "storyDescription"
    }
}