package com.example.submission_awal_intermediate_androidview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission_awal_intermediate_androidview.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profile"
    }
}