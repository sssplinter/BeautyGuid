package com.breaktime.signscreen.data.source.salonApi.remote

import retrofit2.Response
import retrofit2.http.GET

interface SalonService {
    @GET("allSalons")
    suspend fun getAllSalons(): Response<ArrayList<SalonPreviewResponse>>
}