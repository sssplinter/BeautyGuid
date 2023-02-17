package com.breaktime.signscreen.screen.authorization.common

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class AuthorizationContract {
    sealed class AuthState : UiState {
        object Default : AuthState()
        object Loading : AuthState()
    }

    sealed class AuthEvent : UiEvent {
        object OnAuthClick : AuthEvent()
        object OnAnotherAuthTypeClick : AuthEvent()
    }

    sealed class AuthEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : AuthEffect()
        data class SuccessfulAuthorization(val token: String) : AuthEffect()
        object NavigateToAnotherAuthType : AuthEffect()
    }
}