package com.breaktime.signscreen.data.source.salonApi.remote

import com.breaktime.signscreen.data.source.salonApi.SalonDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSalonDataSource(private val salonService: SalonService) : SalonDataSource {
    override suspend fun getAllSalons(): List<SalonPreviewResponse> =
        withContext(Dispatchers.IO) {
            return@withContext salonService.getAllSalons().body()?.toList() ?: listOf()
        }
}