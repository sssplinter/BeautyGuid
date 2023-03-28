package com.breaktime.signscreen.data.source.specialistApi

import com.breaktime.signscreen.data.network.models.SpecialistPreview

class SpecialistRepositoryImpl(private val specialistDataSource: SpecialistDataSource) :
    SpecialistRepository {
    override suspend fun getAllSpecialists(): List<SpecialistPreview> {
        return specialistDataSource.getAllSpecialists()
    }
}