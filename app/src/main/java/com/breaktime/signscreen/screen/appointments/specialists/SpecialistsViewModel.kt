package com.breaktime.signscreen.screen.appointments.specialists

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo

class SpecialistsViewModel : ViewModel() {
    private val _specialists = getTestdata().toMutableStateList()
    val specialists: List<SpecialistInfo>
        get() = _specialists

    fun selectSpecialists(item: SpecialistInfo) {
        specialists.map {
            it.isChecked =
                if (it.specialistId == item.specialistId) !it.isChecked else false
        }
    }

    fun openMoreInfo(item: SpecialistInfo) {

    }
}

// TODO replace by real data
fun getTestdata() = List(10) { i ->
    SpecialistInfo(
        i.toString(),
        "Kristina Sementsova",
        "Eyebrow stylist, makeup artist",
        R.drawable.ab1_inversions,
        3.3,
        127
    )
}
