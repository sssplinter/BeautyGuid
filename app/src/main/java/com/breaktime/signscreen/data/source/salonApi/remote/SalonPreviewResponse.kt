package com.breaktime.signscreen.data.source.salonApi.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalonPreviewResponse(
    val salonId: Int,
    val salonName: String,
    val salonPhotoUrl: String,
    val salonDescription: String,
    val rating: Double,
    val location: String,
    val categories: List<String>
) : Parcelable