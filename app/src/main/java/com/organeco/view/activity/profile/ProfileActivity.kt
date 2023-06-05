package com.organeco.view.activity.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.organeco.R
import com.organeco.databinding.ActivityProfileBinding
import com.organeco.view.activity.auth.login.LoginActivity
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val prefViewModel: UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }

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

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun logout() {
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