package com.breaktime.signscreen.data.source.authorization

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo

interface AuthorizationDataSource {
    suspend fun login(user: UserLoginRequestInfo): TokenResponse?
    suspend fun register(user: UserLoginRequestInfo): TokenResponse?
}