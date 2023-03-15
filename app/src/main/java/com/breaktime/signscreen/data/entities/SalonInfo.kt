package com.breaktime.signscreen.data.entities

data class SalonInfo(
    val salonId: String,
    val salonName: String,
    val salonDescription: String,
    val imageId: Int,
    val categories: List<String>
)