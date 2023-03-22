package com.breaktime.signscreen.data.source.mainApi

import com.breaktime.signscreen.data.network.models.UserRequestInfo

interface UserDataRepository {
    suspend fun updatePersonalData(userRequestInfo: UserRequestInfo)
    suspend fun getPersonalData(): UserRequestInfo?
}