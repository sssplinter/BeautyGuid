package com.breaktime.signscreen.data.source.authorization.remote

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo
import com.breaktime.signscreen.data.source.authorization.AuthorizationDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteAuthorizationDataSource(private val authorizationApi: AuthorizationService) :
    AuthorizationDataSource {
    override suspend fun login(user: UserLoginRequestInfo): TokenResponse? =
        withContext(Dispatchers.IO) {
            val result = authorizationApi.login(user)
            return@withContext result.body()
        }

    override suspend fun register(user: UserLoginRequestInfo): TokenResponse? =
        withContext(Dispatchers.IO) {
            val result = authorizationApi.register(user)
            return@withContext result.body()
        }
}