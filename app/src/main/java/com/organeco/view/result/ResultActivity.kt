package com.organeco.view.result

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.organeco.R
import com.organeco.Recommendation
import com.organeco.RecommendationViewModel
import com.organeco.databinding.ActivityResultBinding
import com.organeco.view.activity.calculator.CalculatorInput
import com.organeco.viewmodel.RecommendationViewModelFactory

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var recommendationViewModel: RecommendationViewModel

    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recommendationViewModel = obtainViewModel(this@ResultActivity)

        val input = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Recommendation>(EXTRA_INPUT, Recommendation::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Recommendation>(EXTRA_INPUT)
        }

        val textInput =
            "temperature : ${input?.temperature.toString()} \nhumidity : ${input?.humidity.toString()} \nmoisture : ${input?.moisture.toString()} \nsoilType : ${input?.soil_type.toString()} \ncropType : ${input?.crop_type.toString()} \nnitrogen : ${input?.nitrogen.toString()} \npotassium : ${input?.potassium.toString()} \nphosphorous : ${input?.phosphorous.toString()} \n"
        binding.tvInput.text = textInput
        if (input != null) {
            binding.tvResult.text = input.result.toString()
        }

        binding.btnSave.setOnClickListener {
            input?.let {
                recommendationViewModel.insert(it)
            }
        }

    }

    private fun obtainViewModel(resultActivity: AppCompatActivity): RecommendationViewModel {
        val factory = RecommendationViewModelFactory.getInstance(resultActivity.application)
        return ViewModelProvider(resultActivity, factory).get(RecommendationViewModel::class.java)
    }

    companion object {
        const val EXTRA_INPUT = "key_input"
    }

}