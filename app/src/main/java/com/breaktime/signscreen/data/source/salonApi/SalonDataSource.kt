package com.breaktime.signscreen.data.source.salonApi

import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

interface SalonDataSource {
    suspend fun getAllSalons(): List<SalonPreviewResponse>
}