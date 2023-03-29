package com.breaktime.signscreen.data.source.salonApi.remote

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import com.breaktime.signscreen.data.source.salonApi.SalonDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSalonDataSource(private val salonService: SalonService) : SalonDataSource {
    override suspend fun getAllSalons(): List<SalonPreviewResponse> =
        withContext(Dispatchers.IO) {
            return@withContext salonService.getAllSalons().body()?.toList() ?: listOf()
        }

    override suspend fun getSalonPreviewById(salonId: Int): SalonPreviewResponse? =
        withContext(Dispatchers.IO) {
            return@withContext salonService.getSalonPreviewById(salonId).body()
        }

    override suspend fun getSalonInfoById(salonId: Int): SalonInfo? =
        withContext(Dispatchers.IO) {
            return@withContext salonService.getSalonInfoById(salonId).body()
        }

    override suspend fun getSalonNewsPreview(salonId: Int): List<SalonNewsPreview> =
        withContext(Dispatchers.IO) {
            return@withContext salonService.getSalonNewsPreviews(salonId).body() ?: listOf()
        }


}