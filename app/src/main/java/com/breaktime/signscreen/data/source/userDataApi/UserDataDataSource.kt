package com.breaktime.signscreen.data.source.userDataApi

import com.breaktime.signscreen.data.network.models.UserRequestInfo

interface UserDataDataSource {
    suspend fun updatePersonalData(userRequestInfo: UserRequestInfo)
    suspend fun getPersonalData(): UserRequestInfo?
}