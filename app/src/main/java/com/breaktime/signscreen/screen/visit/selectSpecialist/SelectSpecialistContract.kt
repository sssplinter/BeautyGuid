package com.breaktime.signscreen.screen.visit.selectSpecialist

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class SelectSpecialistContract {
    sealed class SelectSpecialistState : UiState {
        object Default : SelectSpecialistState()
        object Loading : SelectSpecialistState()
    }

    sealed class SelectSpecialistEvent : UiEvent {
        object OnNavigateBackClick : SelectSpecialistEvent()
        data class OnSelectSpecialistClick(val specialistId: Int) : SelectSpecialistEvent()
        data class OnMoreInfoClick(val specialistId: Int) : SelectSpecialistEvent()
    }

    sealed class SelectSpecialistEffect : UiEffect {
        object NavigateBack : SelectSpecialistEffect()
        data class SelectSpecialist(val specialistId: Int) : SelectSpecialistEffect()
        data class OpenMoreInfo(val specialistId: Int) : SelectSpecialistEffect()
    }
}