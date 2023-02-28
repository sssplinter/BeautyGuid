package com.breaktime.signscreen.data.entities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// TODO entity for UI layer
class SpecialistInfo(
    val specialistId: String,
    val fullName: String,
    val specialization: String,
    // TODO replace with real photos from internet
    val imageId: Int,
    val salonId: String,
    val rating: Double,
    val marksCount: Int,
    initialChecked: Boolean = false
) {
    var isChecked by mutableStateOf(initialChecked)
}