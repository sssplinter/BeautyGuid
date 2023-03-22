package com.breaktime.signscreen.data.source.mainApi

import com.breaktime.signscreen.data.network.models.UserRequestInfo

class UserDataRepositoryImpl(private val remoteDataSource: UserDataDataSource) :
    UserDataRepository {
    override suspend fun updatePersonalData(userRequestInfo: UserRequestInfo) {
        remoteDataSource.updatePersonalData(userRequestInfo)
    }

    override suspend fun getPersonalData(): UserRequestInfo? {
        return remoteDataSource.getPersonalData()
    }
}