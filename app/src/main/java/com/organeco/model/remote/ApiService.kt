package com.organeco.model.remote

import com.organeco.model.remote.response.LoginResponse
import com.organeco.model.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun postLogin(
        @Field("email") email : String,
        @Field("password") password : String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("/auth/register")
    suspend fun postRegister(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String,
    ): RegisterResponse
}