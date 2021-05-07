package com.cahstudio.tesapotekk24.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import com.cahstudio.tesapotekk24.R
import com.cahstudio.tesapotekk24.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val mViewModel by viewModel<LoginViewModel>()

    private lateinit var btnLogin: AppCompatButton
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    fun initView() {
        btnLogin = findViewById(R.id.btnLogin)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        progressBar = findViewById(R.id.progressBar)

        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnLogin -> {
                login()
            }
        }
    }

    fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username.trim().isEmpty() && password.trim().isEmpty()) {
            Toast.makeText(this, "Username or passwort must be fill", Toast.LENGTH_SHORT).show()
        } else {
            btnLogin.text = ""
            progressBar.visibility = View.VISIBLE

            mViewModel.login(username, password).observe(this, {
                btnLogin.text = "Login"
                progressBar.visibility = View.GONE
                if (it.status == "true") {
                    startActivity(Intent(this, MainActivity::class.java)
                        .putExtra("partnerID", it.user?.partnerID)
                        .putExtra("partnerCode", it.user?.partnerCode))
                } else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}