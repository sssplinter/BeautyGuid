package com.breaktime.signscreen.data.source.specialistApi.remote

import com.breaktime.signscreen.data.network.models.SpecialistPreview
import retrofit2.Response
import retrofit2.http.GET

interface SpecialistService {
    @GET("allSpecialists")
    suspend fun getAllSpecialists(): Response<ArrayList<SpecialistPreview>>
}