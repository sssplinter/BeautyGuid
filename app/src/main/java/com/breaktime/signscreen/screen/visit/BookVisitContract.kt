package com.breaktime.signscreen.screen.visit

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
    }

    sealed class BookVisitEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : BookVisitEffect()
        object NavigateBack : BookVisitEffect()
    }
}