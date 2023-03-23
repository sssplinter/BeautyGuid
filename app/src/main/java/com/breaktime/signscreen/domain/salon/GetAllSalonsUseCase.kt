package com.breaktime.signscreen.domain.salon

import com.breaktime.signscreen.data.source.salonApi.SalonRepository
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

class GetAllSalonsUseCase(private val salonRepository: SalonRepository) {

    suspend operator fun invoke(): List<SalonPreviewResponse> {
        return salonRepository.getAllSalons()
    }
}