package com.breaktime.signscreen.domain.salon

import com.breaktime.signscreen.data.network.models.SalonInfo
import com.breaktime.signscreen.data.source.salonApi.SalonRepository

class GetSalonInfoByIdUseCase(private val salonRepository: SalonRepository) {

    suspend operator fun invoke(salonId: Int): SalonInfo? {
        return salonRepository.getSalonInfoById(salonId)
    }
}