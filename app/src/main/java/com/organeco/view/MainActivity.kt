package com.organeco.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.organeco.R
import com.organeco.databinding.ActivityMainBinding
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val prefViewModel : UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            logout()
        }
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