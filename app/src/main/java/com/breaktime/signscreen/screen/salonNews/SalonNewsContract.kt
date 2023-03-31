package com.breaktime.signscreen.screen.salonNews

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class SalonNewsContract {
    sealed class SalonNewsState : UiState {
        object Default : SalonNewsState()
        object Loading : SalonNewsState()
    }

    sealed class SalonNewsEvent : UiEvent {
        data class OnSpecialistClick(val specialistId: Int) : SalonNewsEvent()
        data class OnSalonClick(val salonId: Int) : SalonNewsEvent()
        object OnNavigateBackClick : SalonNewsEvent()
    }

    sealed class SalonNewsEffect : UiEffect {
        data class OpenSalonPortfolio(val salonId: Int) : SalonNewsEffect()
        data class OpenSpecialistProfile(val specialistId: Int) : SalonNewsEffect()
        data class NavigateBack(val salonId: Int) : SalonNewsEffect()
    }
}