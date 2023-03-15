package com.breaktime.signscreen.data.source.authorizationApi

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo

interface AuthorizationRepository {
    suspend fun login(user: UserLoginRequestInfo): TokenResponse?
    suspend fun register(user: UserLoginRequestInfo): TokenResponse?
}