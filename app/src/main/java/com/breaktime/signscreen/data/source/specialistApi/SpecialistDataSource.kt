package com.breaktime.signscreen.data.source.specialistApi

import com.breaktime.signscreen.data.network.models.SpecialistPreview

interface SpecialistDataSource {
    suspend fun getAllSpecialists(): List<SpecialistPreview>
}