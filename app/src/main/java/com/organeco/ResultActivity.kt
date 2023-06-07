package com.organeco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    companion object {
        const val EXTRA_RESULT = "key_result"
        const val EXTRA_INPUT = "key_input"
    }

}