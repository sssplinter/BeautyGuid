package com.breaktime.signscreen.data.source.specialistApi

import com.breaktime.signscreen.data.network.models.SpecialistPreview

interface SpecialistRepository {
    suspend fun getAllSpecialists(): List<SpecialistPreview>
    suspend fun getSpecialistsBySalonId(salonId: Int): List<SpecialistPreview>
}