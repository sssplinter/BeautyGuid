package com.breaktime.signscreen.domain.user

import com.breaktime.signscreen.data.network.models.UserRequestInfo
import com.breaktime.signscreen.data.source.userDataApi.UserDataRepository

class UpdateUserPersonalDataUseCase(private val userDataRepository: UserDataRepository) {

    suspend operator fun invoke(userRequestInfo: UserRequestInfo) {
        userDataRepository.updatePersonalData(userRequestInfo)
    }
}