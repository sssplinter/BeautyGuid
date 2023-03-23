package com.breaktime.signscreen.domain.user

import com.breaktime.signscreen.data.network.models.UserRequestInfo
import com.breaktime.signscreen.data.source.userDataApi.UserDataRepository

class GetUserPersonalDataUseCase(private val userDataRepository: UserDataRepository) {

    suspend operator fun invoke(): UserRequestInfo? {
        return userDataRepository.getPersonalData()
    }
}