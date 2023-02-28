package com.breaktime.signscreen.screen.appointments.specialists

import androidx.compose.runtime.toMutableStateList
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.screen.base.BaseViewModel

class SpecialistsViewModel :
    BaseViewModel<SpecialistsContract.SpecialistsEvent, SpecialistsContract.SpecialistsState, SpecialistsContract.SpecialistsEffect>() {
    private val _specialists = getTestdata().toMutableStateList()
    val specialists: List<SpecialistInfo>
        get() = _specialists

    fun selectSpecialists(item: SpecialistInfo) {
        specialists.map {
            it.isChecked = if (it.specialistId == item.specialistId) !it.isChecked else false
        }
    }

    override fun createInitialState(): SpecialistsContract.SpecialistsState {
        return SpecialistsContract.SpecialistsState.Default
    }

    override fun handleEvent(event: SpecialistsContract.SpecialistsEvent) {
        when (event) {
            is SpecialistsContract.SpecialistsEvent.OnBookVisitClick -> {

            }
            SpecialistsContract.SpecialistsEvent.OnNavigateBackClick -> {
                setEffect { SpecialistsContract.SpecialistsEffect.NavigateBack }
            }
            is SpecialistsContract.SpecialistsEvent.OnSalonClick -> {

            }
            is SpecialistsContract.SpecialistsEvent.OnSpecialistInfoClick -> {

            }
        }
    }
}

// TODO replace by real data
fun getTestdata() = List(6) { i ->
    SpecialistInfo(
        i.toString(),
        "Kristina Sementsova",
        "Eyebrow stylist, makeup artist",
        R.drawable.ab1_inversions,
        "12123",
        3.3,
        127
    )
}
