package com.breaktime.signscreen.data.source.authorizationApi.remote

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthorizationService {
    @POST("login")
    @Headers("No-Authentication: true")
    suspend fun login(@Body user: UserLoginRequestInfo): Response<TokenResponse>

    @POST("register")
    @Headers("No-Authentication: true")
    suspend fun register(@Body user: UserLoginRequestInfo): Response<TokenResponse>
}