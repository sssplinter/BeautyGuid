package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SalonInfo(
    val salonId: Int,
    val address: String,
    val phoneNumber: String? = null,
    val email: String? = null,
    val webLink: String? = null,
    val telegramLink: String? = null,
    val instagramLink: String? = null,
    val vkLink: String? = null
) : Parcelable