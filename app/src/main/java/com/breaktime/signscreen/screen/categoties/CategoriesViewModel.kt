package com.breaktime.signscreen.screen.categoties

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.data.Categories
import com.breaktime.signscreen.data.entities.ServiceInfo

class CategoriesViewModel : ViewModel() {

    private var selectedCategory by mutableStateOf(Categories.HAIRS)

    private var _services = getTestdata(selectedCategory).toMutableStateList()
    val services: List<ServiceInfo>
        get() = _services

    fun selectService(item: ServiceInfo) {
        services.find { it.serviceId == item.serviceId }?.let { serviceInfo ->
            serviceInfo.isChecked = !serviceInfo.isChecked
        }
    }

    fun onPageChange(page: Int) {
        selectedCategory = when(page) {
            Categories.HAIRS.ordinal -> {
                Categories.HAIRS
            }
            Categories.NAILS.ordinal -> {
                Categories.NAILS
            }
            else -> {
                Categories.MASSAGE
            }
        }
        _services = getTestdata(selectedCategory).toMutableStateList()
    }
}

val hairsList = List(7) { i ->
    ServiceInfo(
        i.toString(),
        "Haircut",
        "Fashionable from the stylist",
        125.3,
        "BYN",
        false
    )
}

val nailsList = List(7) { i ->
    ServiceInfo(
        i.toString(),
        "Manicure",
        "Removal, coating",
        55.5,
        "BYN",
        false
    )
}

val massageList = List(4) { i ->
    ServiceInfo(
        i.toString(),
        "Massage",
        "Classic",
        70.44,
        "BYN",
        false
    )
}

// TODO replace by real data
fun getTestdata(category: Categories) =
    when (category) {
        Categories.HAIRS -> {
            hairsList
        }
        Categories.NAILS -> {
            nailsList
        }
        else -> {
            massageList
        }
    }