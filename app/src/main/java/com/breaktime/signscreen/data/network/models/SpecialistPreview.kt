package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.screen.appointments.salons.CategoryEnum
import com.breaktime.signscreen.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpecialistPreview(
    val id: Int,
    val salonId: Int,
    val salonName: String,
    val firstName: String,
    val lastName: String,
    val category: String,
    val photoUrl: String,
    val rating: Double,
    val marksCount: Int,
) : Parcelable

fun SpecialistPreview.toSpecialistPreviewInfo(): SpecialistInfo {
    return SpecialistInfo(
        id = this.id,
        salonId = this.salonId,
        salonName = this.salonName,
        fullName = "${this.firstName} ${this.lastName}",
        specialisation = "Specialist of ${CategoryEnum.valueOf(this.category)}",
        photoUrl = "${Constants.specialistPhotoPathPrefix}${this.photoUrl}",
        rating = this.rating,
        marksCount = this.marksCount
    )
}