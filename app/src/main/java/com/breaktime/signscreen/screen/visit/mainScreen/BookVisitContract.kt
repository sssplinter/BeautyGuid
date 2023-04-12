package com.breaktime.signscreen.screen.visit.mainScreen

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class BookVisitContract {
    sealed class BookVisitState : UiState {
        object Default : BookVisitState()
        object Loading : BookVisitState()
    }

    sealed class BookVisitEvent : UiEvent {
        object OnBackClick : BookVisitEvent()
        data class OnChooseServiceClick(val selectedServicesList: Int? = null) : BookVisitEvent()
        data class OnChooseSpecialistClick(val selectedSpecialistId: Int? = null) : BookVisitEvent()

        // TODO replace by correct data format
        data class OnChooseDateTimeClick(val selectedDateTime: String? = null) : BookVisitEvent()
        object OnDeselectServiceClick : BookVisitEvent()
        object OnDeselectSpecialistClick : BookVisitEvent()
        object OnDeselectDateTimeClick : BookVisitEvent()
    }

    sealed class BookVisitEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : BookVisitEffect()
        object NavigateBack : BookVisitEffect()
        data class OnChooseService(val selectedServiceId: Int? = null) : BookVisitEffect()
        data class OnChooseSpecialist(val selectedSpecialistId: Int? = null) : BookVisitEffect()

        // TODO replace by correct data format
        data class OnChooseDateTime(val selectedDateTime: String? = null) : BookVisitEffect()

        object OnDeselectService : BookVisitEffect()
        object OnDeselectSpecialist : BookVisitEffect()
        object OnDeselectDateTime : BookVisitEffect()
    }
}