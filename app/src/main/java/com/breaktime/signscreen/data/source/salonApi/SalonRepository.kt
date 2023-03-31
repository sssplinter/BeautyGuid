package com.breaktime.signscreen.data.source.salonApi

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.network.models.SalonNews
import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

interface SalonRepository {
    suspend fun getAllSalons(): List<SalonPreviewResponse>
    suspend fun getSalonPreviewById(salonId: Int): SalonPreviewResponse?
    suspend fun getSalonInfoById(salonId: Int): SalonInfo?
    suspend fun getSalonNewsPreview(salonId: Int): List<SalonNewsPreview>
    suspend fun getSalonNews(salonId: Int): List<SalonNews>

}