package com.breaktime.signscreen.data.entities

data class SalonNewsInfo(
    val id: Int,
    val salonId: Int,
    val salonName: String,
    val salonDescription: String,
    val salonPhotoUrl: String,
    val specialistId: Int,
    val specialistName: String,
    val newsPhotoUrl: String,
    val newsDescription: String
)