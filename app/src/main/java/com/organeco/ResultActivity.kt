package com.organeco

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.organeco.databinding.ActivityLoginBinding
import com.organeco.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getStringExtra(EXTRA_RESULT)

        val input = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<CalculatorInput>(EXTRA_INPUT, CalculatorInput::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<CalculatorInput>(EXTRA_INPUT)
        }

        if (CalculatorInput != null) {
            val textInput = "temperature : ${input?.temperature.toString()} \nhumidity : ${input?.humidity.toString()} \nmoisture : ${input?.moisture.toString()} \nsoilType : ${input?.soilType.toString()} \ncropType : ${input?.cropType.toString()} \nnitrogen : ${input?.nitrogen.toString()} \npotassium : ${input?.potassium.toString()} \nphosphorous : ${input?.temperature.toString()} \n"
            binding.tvInput.text = textInput
        }

        binding.tvResult.text = result.toString()

    }

    companion object {
        const val EXTRA_RESULT = "key_result"
        const val EXTRA_INPUT = "key_input"
    }

}