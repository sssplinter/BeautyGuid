package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import com.breaktime.signscreen.screen.portfolio.SalonDetailsInfo
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

fun SalonInfo.toSalonDetailsInfo(addressAsText: String?): SalonDetailsInfo {
    return SalonDetailsInfo(
        salonId = this.salonId,
        addressGeo = this.address,
        address = addressAsText,
        phoneNumber = this.phoneNumber,
        email = this.email,
        webLink = this.webLink,
        telegramLink = this.telegramLink,
        instagramLink = this.instagramLink,
        vkLink = this.vkLink
    )
}