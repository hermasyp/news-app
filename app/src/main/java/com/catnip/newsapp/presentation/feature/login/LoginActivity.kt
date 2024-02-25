package com.catnip.newsapp.presentation.feature.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.catnip.newsapp.R
import com.catnip.newsapp.databinding.ActivityLoginBinding
import com.catnip.newsapp.presentation.feature.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.login_page)
        setClickButtonLogin()
    }

    private fun setClickButtonLogin() {
        binding.btnLoginAccount.setOnClickListener {
            if (isFormLoginValid()) {
                checkLogin()
            }
        }
    }

    private fun isFormLoginValid(): Boolean {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        if (username.isEmpty()) {
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.text_error_username_empty)
        } else {
            binding.tilUsername.isErrorEnabled = false
        }

        if (password.isEmpty()) {
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = getString(R.string.text_error_password_empty)
        } else {
            binding.tilPassword.isErrorEnabled = false
        }

        return isFormValid
    }

    private fun checkLogin() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.doLogin(username, password).observe(this) { isSuccess ->
            if (isSuccess) {
                navigateToHomepage()
            } else {
                Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToHomepage() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}