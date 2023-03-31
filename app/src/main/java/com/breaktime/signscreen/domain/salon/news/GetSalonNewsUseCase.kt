package com.breaktime.signscreen.domain.salon.news

import com.breaktime.signscreen.data.entities.SalonNewsInfo
import com.breaktime.signscreen.data.source.salonApi.SalonRepository
import com.breaktime.signscreen.utils.Constants

class GetSalonNewsUseCase(private val salonRepository: SalonRepository) {
    suspend operator fun invoke(salonId: Int): List<SalonNewsInfo> {
        val salon = salonRepository.getSalonPreviewById(salonId)
        val salonNews = salonRepository.getSalonNews(salonId)

        return salonNews.map {
            SalonNewsInfo(
                id = it.newsId,
                salonId = salonId,
                salonName = salon?.salonName ?: "",
                salonDescription = salon?.salonDescription ?: "",
                salonPhotoUrl = "${Constants.salonPhotoPathPrefix}${salon?.salonPhotoUrl}",
                specialistId = it.specialistId,
                specialistName = "${it.specialistFirstName} ${it.specialistLastName}",
                newsPhotoUrl = "${Constants.salonNewsPhotoPrefix}${it.photoUrl}",
                newsDescription = it.description
            )
        }
    }
}