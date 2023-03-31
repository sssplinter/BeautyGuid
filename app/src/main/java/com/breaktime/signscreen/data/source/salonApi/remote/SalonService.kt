package com.breaktime.signscreen.data.source.salonApi.remote

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.network.models.SalonNews
import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SalonService {
    @GET("allSalons")
    suspend fun getAllSalons(): Response<ArrayList<SalonPreviewResponse>>

    @GET("salonPreview/{salonId}")
    suspend fun getSalonPreviewById(@Path("salonId") salonId: Int): Response<SalonPreviewResponse>

    @GET("salonInfo/{salonId}")
    suspend fun getSalonInfoById(@Path("salonId") salonId: Int): Response<SalonInfo>

    @GET("salonNewsPreviewList/{salonId}")
    suspend fun getSalonNewsPreviews(@Path("salonId") salonId: Int): Response<List<SalonNewsPreview>>

    @GET("salonNewsList/{salonId}")
    suspend fun getSalonNews(@Path("salonId") salonId: Int): Response<List<SalonNews>>
}