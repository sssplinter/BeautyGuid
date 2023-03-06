package com.breaktime.signscreen.data.source.authorization

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo

open class AuthorizationRepositoryImpl(private val remoteDataSource: AuthorizationDataSource) :
    AuthorizationRepository {

    override suspend fun login(user: UserLoginRequestInfo): TokenResponse? =
        remoteDataSource.login(user)


    override suspend fun register(user: UserLoginRequestInfo): TokenResponse? =
        remoteDataSource.register(user)
}