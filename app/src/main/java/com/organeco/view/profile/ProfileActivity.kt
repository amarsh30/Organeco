package com.organeco.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.organeco.R
import com.organeco.databinding.ActivityProfileBinding
import com.organeco.view.login.LoginActivity
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private val prefViewModel : UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback = supportActionBar
        callback?.title = getString(R.string.profile)
        callback?.setDisplayHomeAsUpEnabled(true)

        prefViewModel.getUserName().observe(this) { name ->
            binding.tvProfileName.text = name
        }

        prefViewModel.getEmail().observe(this){ email ->
            binding.tvProfileEmail.text = email
            Log.d("TAG", "Email: $email")
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun logout(){
        prefViewModel.saveUserPreferences(
            true,
            "",
            "",
            "",
        )
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }
}