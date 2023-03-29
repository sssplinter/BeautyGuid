package com.breaktime.signscreen.domain.salon.news

import com.breaktime.signscreen.data.network.models.SalonNewsPreview
import com.breaktime.signscreen.data.source.salonApi.SalonRepository

class GetSalonNewsPreviewsUseCase(private val salonRepository: SalonRepository) {
    suspend operator fun invoke(salonId: Int): List<SalonNewsPreview> {
        return salonRepository.getSalonNewsPreview(salonId)
    }
}