package com.breaktime.signscreen.data.source.specialistApi.remote

import com.breaktime.signscreen.data.network.models.SpecialistPreview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecialistService {
    @GET("allSpecialists")
    suspend fun getAllSpecialists(): Response<ArrayList<SpecialistPreview>>

    @GET("specialists/{salonId}")
    suspend fun getSpecialistsBySalonId(@Path("salonId") salonId: Int): Response<ArrayList<SpecialistPreview>>
}