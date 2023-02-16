package com.breaktime.signscreen.data.source.authorization

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo
import com.breaktime.signscreen.data.source.authorization.remote.RemoteAuthorizationDataSource

open class AuthorizationRepositoryImpl() : AuthorizationRepository {
    // TODO replace by injection
    private val remoteDataSource: AuthorizationDataSource =
        RemoteAuthorizationDataSource()

    override suspend fun login(user: UserLoginRequestInfo): TokenResponse? =
        remoteDataSource.login(user)


    override suspend fun register(user: UserLoginRequestInfo): TokenResponse? =
        remoteDataSource.register(user)
}