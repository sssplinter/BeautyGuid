package com.breaktime.signscreen.screen.appointments.services

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.data.entities.ServiceInfo

class ServicesViewModel : ViewModel() {
    private val _specialists = getTestdata().toMutableStateList()
    val services: List<ServiceInfo>
        get() = _specialists

    fun selectService(item: ServiceInfo) {
        services.find { it.serviceId == item.serviceId }?.let { serviceInfo ->
            serviceInfo.isChecked = !serviceInfo.isChecked
        }
    }
}

// TODO replace by real data
fun getTestdata() = List(20) { i ->
    ServiceInfo(
        i.toString(),
        "Manicure",
        "Removal, coating",
        55.5,
        "BYN",
        false
    )
}
