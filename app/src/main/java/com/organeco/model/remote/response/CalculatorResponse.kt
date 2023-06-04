package com.organeco.model.remote.response

import com.google.gson.annotations.SerializedName

data class CalculatorResponse(

    @field:SerializedName("prediction")
    val prediction: String,
)
