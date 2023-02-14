package com.breaktime.signscreen.data.network.models

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface APIService {
    @POST("login")
    suspend fun login(@Body user: UserLoginRequestInfo): Response<TokenResponse>

    @POST("register")
    suspend fun register(@Body user: UserLoginRequestInfo): Response<TokenResponse>
}

object RetrofitHelper {
    // for https use other ip
    private const val baseUrl = "http://10.0.2.2:8080/"

    fun getInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}