package com.breaktime.signscreen.screen.visit.items.specialist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class SelectedSpecialistInfo(
    val id: Int,
    val salonId: Int,
    val fullName: String,
    val specialisation: String,
    val photoUrl: String,
    val rating: Double,
    val marksCount: Int,
    var initialChecked: Boolean = false
) {
    var isChecked by mutableStateOf(initialChecked)
}
