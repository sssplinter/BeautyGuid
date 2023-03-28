package com.breaktime.signscreen.domain.specialist

import com.breaktime.signscreen.data.network.models.SpecialistPreview
import com.breaktime.signscreen.data.source.specialistApi.SpecialistRepository

class GetAllSpecialistsUseCase(private val specialistRepository: SpecialistRepository) {
    suspend operator fun invoke(): List<SpecialistPreview> {
        return specialistRepository.getAllSpecialists()
    }
}