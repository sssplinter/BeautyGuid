package com.breaktime.signscreen.data.entities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SpecialistInfo(
    val id: Int,
    val salonId: Int,
    val salonName: String,
    val fullName: String,
    val specialisation: String,
    val photoUrl: String,
    val rating: Double,
    val marksCount: Int,
    initialChecked: Boolean = false
) {
    var isChecked by mutableStateOf(initialChecked)
}