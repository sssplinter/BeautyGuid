package com.breaktime.signscreen.screen.portfolio

data class SalonDetailsInfo (
    val salonId: Int,
    val addressGeo: String,
    val address: String?,
    val phoneNumber: String? = null,
    val email: String? = null,
    val webLink: String? = null,
    val telegramLink: String? = null,
    val instagramLink: String? = null,
    val vkLink: String? = null
)