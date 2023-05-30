package com.organeco.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.organeco.R
import com.organeco.databinding.ActivityLoginBinding
import com.organeco.model.remote.utils.MediatorResult
import com.organeco.view.customview.PasswordCustom
import com.organeco.view.utils.IdlingConfig
import com.organeco.viewmodel.AuthViewModel
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val authViewModel : UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }
    private val viewModel : AuthViewModel by viewModels { ViewModelFactory.getInstance(this) }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        IdlingConfig.decrement()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setEditTextPassword()

        binding.btnLogin.setOnClickListener {
            sessionChecker()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun checkData(): Boolean{
        val email = binding.edEmail.text.toString().trim()
        val password = binding.edPassword.text.toString()

        when {
            email.isEmpty() -> {
                showMessage("need email")
                return true
            }
            password.isEmpty() -> {
                showMessage("need password")
                return true
            }
            else -> (password.isNotEmpty() && email.isNotEmpty())
        }
        return false
    }

    private fun sessionChecker(){
        authViewModel.getTokenKey().observe(this){ respon ->
            if (respon.isNullOrEmpty()){
                if (checkData())
                    showMessage(getString(R.string.Login_error))
                else
                    lifecycleScope.launch{ login() }
            } else {
                startActivity(Intent(this,MainActivity::class.java))
                finishAffinity()
            }
        }
    }

    private suspend fun login() {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()

        viewModel.postLogin(email, password).observe(this){
            when(it) {
                is MediatorResult.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MediatorResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    saveUserLogin(
                        it.data.data.name,
                        it.data.data.token,
                        it.data.data.userId,
                        onBoard = true,

                    )
                    showMessage("Welcome ${it.data.data.name}")
                    pageSuccess()
                }
                is MediatorResult.Error ->{
                    binding.progressBar.visibility = View.GONE
                    if (it.error == invalid) {
                        showMessage(getString(R.string.Login_form_error))
                    } else {
                        showMessage(it.error)
                    }
                    Log.d(tag, it.error)
                }
            }
        }
    }

    private fun saveUserLogin(userName: String, tokenKey: String, userId: String, onBoard: Boolean){
        authViewModel.saveUserPreferences(
            onBoard,
            userName,
            "Bearer $tokenKey",
            userId
        )
    }

    private fun pageSuccess(){
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    private fun showMessage(message : String){
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setEditTextPassword(){
        binding.edPassword.apply {
            transformationMethod = PasswordTransformationMethod.getInstance()
            onItemClickDetail(object : PasswordCustom.SetHideCallBack {
                override fun setHideCallback(status: Boolean) {
                    if (status) {
                        binding.edPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    } else {
                        binding.edPassword.transformationMethod = null
                    }
                }
            })
        }
    }

    companion object{
        const val tag = "LoginActivity"
        const val invalid = "HTTP 400 Bad Request"
    }
}