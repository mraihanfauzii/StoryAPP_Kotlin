package com.example.submission_awal_intermediate_androidview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission_awal_intermediate_androidview.authentication.LoginActivity
import com.example.submission_awal_intermediate_androidview.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.button.setOnClickListener {
            val login = Intent(this@OnBoardingActivity, LoginActivity::class.java)
            startActivity(login)
            finish()
        }
    }
}