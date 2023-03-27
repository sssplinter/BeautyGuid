package com.breaktime.signscreen.domain.salon

import com.breaktime.signscreen.data.source.salonApi.SalonRepository
import com.breaktime.signscreen.data.source.salonApi.remote.SalonPreviewResponse

class GetSalonPreviewByIdUseCase(private val salonRepository: SalonRepository) {

    suspend operator fun invoke(salonId: Int): SalonPreviewResponse? {
        return salonRepository.getSalonPreviewById(salonId)
    }
}