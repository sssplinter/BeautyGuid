package com.breaktime.signscreen.data.source.specialistApi.remote

import com.breaktime.signscreen.data.network.models.SpecialistPreview
import com.breaktime.signscreen.data.source.specialistApi.SpecialistDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteSpecialistDataSource(private val specialistService: SpecialistService) :
    SpecialistDataSource {

    override suspend fun getAllSpecialists(): List<SpecialistPreview> =
        withContext(Dispatchers.IO) {
            return@withContext specialistService.getAllSpecialists().body()?.toList() ?: listOf()
        }

    override suspend fun getSpecialistsBySalonId(salonId: Int): List<SpecialistPreview> =
        withContext(Dispatchers.IO) {
            return@withContext specialistService.getSpecialistsBySalonId(salonId).body()?.toList() ?: listOf()
        }
}
