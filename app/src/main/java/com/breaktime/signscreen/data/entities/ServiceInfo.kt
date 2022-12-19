package com.breaktime.signscreen.data.entities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ServiceInfo (
    val serviceId: String,
    val service: String,
    val info: String?,
    val price: Double,
    val currency: String,
    initialChecked: Boolean = false
) {
    var isChecked by mutableStateOf(initialChecked)
}