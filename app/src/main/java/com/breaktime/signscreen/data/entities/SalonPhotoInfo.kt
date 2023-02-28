package com.breaktime.signscreen.data.entities

data class SalonPhotoInfo(
    val photoId: String,
    val salonName: String,
    // TODO replace by real photo
    val salonPhoto: Int,
    // TODO replace by real photo
    val photo: Int,
    val masterName: String,
    val masterId: String,
    val description: String
)