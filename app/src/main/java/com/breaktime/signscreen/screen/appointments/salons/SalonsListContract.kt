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
        data class OnOpenSalonPortfolio(val salonId: Int) : SalonsListEvent()
        object OnNavigateBackClick : SalonsListEvent()
    }

    sealed class SalonsListEffect : UiEffect {
        data class OpenSalonPortfolio(val salonId: Int) : SalonsListEffect()
        object NavigateBack : SalonsListEffect()
    }
}