package com.breaktime.signscreen.data.source.userDataApi.remote

import com.breaktime.signscreen.data.network.models.UserRequestInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserDataService {
    @POST("updatePersonalData")
    suspend fun updateUserData(@Body userRequestInfo: UserRequestInfo): Response<Unit>

    @GET("getPersonalData")
    suspend fun getUserData(): Response<UserRequestInfo>
}