package com.organeco.view.result

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.organeco.R
import com.organeco.databinding.ActivityResultBinding
import com.organeco.view.activity.calculator.CalculatorInput

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getStringExtra(EXTRA_RESULT)

        val input = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<CalculatorInput>(EXTRA_INPUT, CalculatorInput::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<CalculatorInput>(EXTRA_INPUT)
        }

        if (CalculatorInput != null) {
            val textInput = "temperature : ${input?.temperature.toString()} \nhumidity : ${input?.humidity.toString()} \nmoisture : ${input?.moisture.toString()} \nsoilType : ${input?.soilType.toString()} \ncropType : ${input?.cropType.toString()} \nnitrogen : ${input?.nitrogen.toString()} \npotassium : ${input?.potassium.toString()} \nphosphorous : ${input?.phosphorous.toString()} \n"
            binding.tvInput.text = textInput
        }

        binding.tvResult.text = result.toString()

    }

    companion object {
        const val EXTRA_RESULT = "key_result"
        const val EXTRA_INPUT = "key_input"
    }

}