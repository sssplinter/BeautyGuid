package com.breaktime.signscreen.screen.appointments.specialists

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class SpecialistsContract {
    sealed class SpecialistsState : UiState {
        object Default : SpecialistsState()
        object Loading : SpecialistsState()
    }

    sealed class SpecialistsEvent : UiEvent {
        data class OnSalonClick(val specialistId: String) : SpecialistsEvent()
        data class OnBookVisitClick(val specialistId: String) : SpecialistsEvent()
        data class OnSpecialistInfoClick(val specialistId: String) : SpecialistsEvent()
        object OnNavigateBackClick : SpecialistsEvent()
    }

    sealed class SpecialistsEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : SpecialistsEffect()
        data class OpenSalonPage(val salonId: String) : SpecialistsEffect()
        data class OpenSpecialistInfoPage(val specialistId: String) : SpecialistsEffect()
        data class BookVisit(val specialistId: String) : SpecialistsEffect()
        object NavigateBack : SpecialistsEffect()
    }
}