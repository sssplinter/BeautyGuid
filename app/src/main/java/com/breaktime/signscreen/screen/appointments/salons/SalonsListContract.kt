package com.breaktime.signscreen.screen.appointments.salons

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class SalonsListContract {
    sealed class SalonsListState : UiState {
        object Default : SalonsListState()
        object Loading : SalonsListState()
    }

    sealed class SalonsListEvent : UiEvent {
        //        data class OnSalonClick(val specialistId: String) : SpecialistsEvent()
//        data class OnBookVisitClick(val specialistId: String) : SpecialistsEvent()
//        data class OnSpecialistInfoClick(val specialistId: String) : SpecialistsEvent()
        object OnNavigateBackClick : SalonsListEvent()
    }

    sealed class SalonsListEffect : UiEffect {
        //        data class ShowErrorMessage(val errorMsg: String) : SpecialistsEffect()
//        data class OpenSalonPage(val salonId: String) : SpecialistsEffect()
//        data class OpenSpecialistInfoPage(val specialistId: String) : SpecialistsEffect()
//        data class BookVisit(val specialistId: String) : SpecialistsEffect()
        object NavigateBack : SalonsListEffect()
    }
}