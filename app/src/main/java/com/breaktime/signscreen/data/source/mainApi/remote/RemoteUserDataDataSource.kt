package com.breaktime.signscreen.data.source.mainApi.remote

import com.breaktime.signscreen.data.network.models.UserRequestInfo
import com.breaktime.signscreen.data.source.mainApi.UserDataDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteUserDataDataSource(private val userDataApi: UserDataService) : UserDataDataSource {
    override suspend fun updatePersonalData(userRequestInfo: UserRequestInfo) {
        withContext(Dispatchers.IO) {
            userDataApi.updateUserData(userRequestInfo)
        }
    }

    override suspend fun getPersonalData(): UserRequestInfo? =
        withContext(Dispatchers.IO) {
            val result = userDataApi.getUserData()
            return@withContext result.body()
        }
}