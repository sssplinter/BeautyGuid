package com.breaktime.signscreen.screen.visit.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.screen.visit.items.datetime.SelectedDateTimeInfo
import com.breaktime.signscreen.screen.visit.items.service.SelectedService
import com.breaktime.signscreen.screen.visit.items.specialist.SelectedSpecialistInfo
import com.breaktime.signscreen.screen.visit.mainScreen.BookVisitContract.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class BookVisitViewModel : BaseViewModel<BookVisitEvent, BookVisitState, BookVisitEffect>() {
    val _selectedSpecialist = MutableStateFlow<SelectedSpecialistInfo?>(null)
    val _selectedDateTimeInfo = MutableStateFlow<SelectedDateTimeInfo?>(null)
    val _selectedServices = MutableStateFlow<List<SelectedService>>(listOf())

    private val _isBookingAvailable = combine(
        _selectedSpecialist,
        _selectedDateTimeInfo,
        _selectedServices
    ) { _selectedSpecialist, _selectedDateTimeInfo, _selectedServices ->
        _selectedSpecialist != null && _selectedDateTimeInfo != null && _selectedServices.isNotEmpty()
    }
    val isBookingAvailable: Flow<Boolean> get() = _isBookingAvailable


    var selectedSpecialist by mutableStateOf<SelectedSpecialistInfo?>(
        SelectedSpecialistInfo(
            1,
            2,
            "Kristina Sementsova",
            "Specialist of MASSAGE",
            "",
            5.0,
            123
        )
    )

    var selectedDateTimeInfo by mutableStateOf<SelectedDateTimeInfo?>(
        SelectedDateTimeInfo(
            "Friday, 7 April",
            "14:40"
        )
    )

    var selectedServices by mutableStateOf<SelectedService?>(
        SelectedService(
            1,
            "Nude make up",
            price = 55.0,
            40.0
        )
    )

    fun deselectService() {
        selectedServices = null
    }

    override fun createInitialState(): BookVisitState {
        return BookVisitState.Default
    }

    override fun handleEvent(event: BookVisitEvent) {
//        when (event) {
//
//        }
    }

}