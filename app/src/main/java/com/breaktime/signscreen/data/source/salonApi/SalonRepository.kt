package com.breaktime.signscreen.data.source.salonApi

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

interface SalonRepository {
    suspend fun getAllSalons(): List<SalonPreviewResponse>
    suspend fun getSalonPreviewById(salonId: Int): SalonPreviewResponse?
    suspend fun getSalonInfoById(salonId: Int): SalonInfo?
}