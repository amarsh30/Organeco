package com.organeco.viewmodel

import androidx.lifecycle.ViewModel
import com.organeco.model.remote.respository.ApiRepository

class CalculatorViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun postCalculate(
        temperature: Number,
        humidity: Number,
        moisture: Number,
        soil_type: String,
        crop_type: String,
        nitrogen: Number,
        potassium: Number,
        phosphorous: Number
    ) =
        apiRepository.postCalculator(
            temperature,
            humidity,
            moisture,
            soil_type,
            crop_type,
            nitrogen,
            potassium,
            phosphorous
        )


}