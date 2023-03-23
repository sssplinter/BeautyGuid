package com.breaktime.signscreen.data.entities

data class SalonInfo(
    val salonId: Int,
    val salonName: String,
    val salonDescription: String,
    val imageId: Int,
    val categories: List<String>
)