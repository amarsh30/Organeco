package com.organeco.model.remote

import com.organeco.model.remote.response.CalculatorResponse
import com.organeco.model.remote.response.LoginResponse
import com.organeco.model.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("predict")
    suspend fun postCalculator(
        @Field("temperature") temperature: Number,
        @Field("humidity") humidity: Number,
        @Field("moisture") moisture: Number,
        @Field("soil_type") soil_type: String,
        @Field("crop_type") crop_type: String,
        @Field("nitrogen") nitrogen: Number,
        @Field("potassium") potassium: Number,
        @Field("phosphorous") phosphorous: Number
    ): CalculatorResponse
}