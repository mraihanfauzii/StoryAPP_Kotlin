package com.example.submission_awal_intermediate_androidview.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.submission_awal_intermediate_androidview.R
import com.example.submission_awal_intermediate_androidview.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        supportActionBar?.hide()
        showLoading(false)

        binding.btnLogin.setOnClickListener {
            val login = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(login)
        }

        binding.btnRegister.setOnClickListener {
            showLoading(true)
            binding.apply {
                val name = edtName.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val passconf = edtPassConf.text.toString()

                authenticationViewModel.register(name, email, password, passconf,
                onSuccess = {
                    showLoading(false)
                    Toast.makeText(this@RegisterActivity, getString(R.string.success_register), Toast.LENGTH_SHORT).show()
                    val login = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(login)
                    finishAffinity()
                },
                onError = { errorMessage ->
                    showLoading(false)
                    Toast.makeText(this@RegisterActivity, errorMessage, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}