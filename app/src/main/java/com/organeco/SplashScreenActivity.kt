package com.organeco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.organeco.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private var binding: ActivitySplashScreenBinding? = null
    private val time = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        val background = object : Thread() {
            override fun run() {
                try {
                    sleep(time)
                    val intent = Intent(this@SplashScreenActivity, OnboardingActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    override fun onPause() {
        super.onPause()
        finishAffinity()
    }
}