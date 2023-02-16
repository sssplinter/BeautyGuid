package com.breaktime.signscreen.data.source.authorization.remote

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthorizationService {
    @POST("login")
    suspend fun login(@Body user: UserLoginRequestInfo): Response<TokenResponse>

    @POST("register")
    suspend fun register(@Body user: UserLoginRequestInfo): Response<TokenResponse>
}