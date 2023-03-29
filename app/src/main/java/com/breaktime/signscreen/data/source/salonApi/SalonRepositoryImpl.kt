package com.breaktime.signscreen.data.source.salonApi

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

class SalonRepositoryImpl(private val salonDataSource: SalonDataSource) : SalonRepository {
    override suspend fun getAllSalons(): List<SalonPreviewResponse> {
        return salonDataSource.getAllSalons()
    }

    override suspend fun getSalonPreviewById(salonId: Int): SalonPreviewResponse? {
        return salonDataSource.getSalonPreviewById(salonId)
    }

    override suspend fun getSalonInfoById(salonId: Int): SalonInfo? {
        return salonDataSource.getSalonInfoById(salonId)
    }

    override suspend fun getSalonNewsPreview(salonId: Int): List<SalonNewsPreview> {
        return salonDataSource.getSalonNewsPreview(salonId)
    }

}