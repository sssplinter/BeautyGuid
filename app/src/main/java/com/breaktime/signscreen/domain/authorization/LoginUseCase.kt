package com.breaktime.signscreen.domain.authorization

import com.breaktime.signscreen.data.network.models.TokenResponse
import com.breaktime.signscreen.data.network.models.UserLoginRequestInfo
import com.breaktime.signscreen.data.source.authorization.AuthorizationRepository
import com.breaktime.signscreen.data.source.authorization.AuthorizationRepositoryImpl

class LoginUseCase {
    // TODO replace by injection
    private val authorizationRepository: AuthorizationRepository = AuthorizationRepositoryImpl()

    suspend operator fun invoke(login: String, password: String): TokenResponse? {
        return authorizationRepository.login(UserLoginRequestInfo(login, password))
    }
}