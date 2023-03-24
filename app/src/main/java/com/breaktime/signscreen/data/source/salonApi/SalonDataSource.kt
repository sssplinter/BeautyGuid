package com.breaktime.signscreen.data.source.salonApi

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

interface SalonDataSource {
    suspend fun getAllSalons(): List<SalonPreviewResponse>
    suspend fun getSalonInfoById(salonId: Int): SalonInfo?
}