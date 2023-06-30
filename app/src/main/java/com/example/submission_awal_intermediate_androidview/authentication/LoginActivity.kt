package com.example.submission_awal_intermediate_androidview.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.submission_awal_intermediate_androidview.MainActivity
import com.example.submission_awal_intermediate_androidview.R
import com.example.submission_awal_intermediate_androidview.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var authentication: AuthenticationManager
    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        supportActionBar?.hide()
        authentication = AuthenticationManager(this)

        binding.btnRegister.setOnClickListener {
            val register = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(register)
        }

        binding.btnLogin.setOnClickListener {
            showLoading(true)
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            authenticationViewModel.login(email, password,
                onSuccess = { token, name ->
                authentication.setSession(AuthenticationManager.SESSION, true)
                authentication.login(AuthenticationManager.TOKEN, token)
                authentication.login(AuthenticationManager.NAME, name)
                authentication.login(AuthenticationManager.EMAIL, email)

                Toast.makeText(this@LoginActivity, getString(R.string.success_login), Toast.LENGTH_SHORT).show()

                showLoading(false)
                val login = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(login)
                finishAffinity()
            }, onError =  { errorMessage ->
                showLoading(false)
                Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onStart() {
        super.onStart()
        if (authentication.checkSession(AuthenticationManager.SESSION) == true) {
            val login = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(login)
            finishAffinity()
        }
    }
}