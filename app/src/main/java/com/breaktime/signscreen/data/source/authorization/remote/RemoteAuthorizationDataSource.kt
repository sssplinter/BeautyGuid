package com.breaktime.signscreen.data.source.authorization.remote

import com.breaktime.signscreen.data.network.models.RetrofitHelper
import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo
import com.breaktime.signscreen.data.source.authorization.AuthorizationDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteAuthorizationDataSource : AuthorizationDataSource {
    // TODO replace by injection
    private val authorizationApi: AuthorizationService =
        RetrofitHelper.getInstance().create(AuthorizationService::class.java)

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