package com.example.submission_awal_intermediate_androidview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.submission_awal_intermediate_androidview.authentication.AuthenticationManager

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1000 //1 detik
    private lateinit var authentication: AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        authentication = AuthenticationManager(this)
        supportActionBar?.hide()

        Handler().postDelayed({
            if (authentication.checkSession(AuthenticationManager.SESSION) == true) {
                val login = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(login)
                finishAffinity()
            } else {
                startActivity(Intent(this@SplashScreenActivity, OnBoardingActivity::class.java))
                finishAffinity()
            }
        }, SPLASH_TIME_OUT)
    }
}