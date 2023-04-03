package com.breaktime.signscreen.domain.specialist

import com.breaktime.signscreen.data.network.models.SpecialistPreview
import com.breaktime.signscreen.data.source.specialistApi.SpecialistRepository

class GetSpecialistsBySalonIdUseCase(private val specialistRepository: SpecialistRepository) {
    suspend operator fun invoke(salonId: Int): List<SpecialistPreview> {
        return specialistRepository.getSpecialistsBySalonId(salonId)
    }
}