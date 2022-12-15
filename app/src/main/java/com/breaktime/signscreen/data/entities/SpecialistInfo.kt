package com.breaktime.signscreen.data.entities

data class SpecialistInfo(
    val specialistId: String,
    val fullName: String,
    val specialization: String,
    // TODO replace with real photos from internet
    val imageId: Int,
    val rating: Double,
    val marksCount: Int
)