package com.breaktime.signscreen.data.source.salonApi

import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

class SalonRepositoryImpl(private val salonDataSource: SalonDataSource) : SalonRepository {
    override suspend fun getAllSalons(): List<SalonPreviewResponse> {
        return salonDataSource.getAllSalons()
    }
}