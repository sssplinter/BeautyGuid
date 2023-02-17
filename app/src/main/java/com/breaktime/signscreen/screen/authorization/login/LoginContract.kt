package com.breaktime.signscreen.screen.authorization.login

import com.breaktime.signscreen.screen.base.UiEffect
import com.breaktime.signscreen.screen.base.UiEvent
import com.breaktime.signscreen.screen.base.UiState

class LoginContract {
    sealed class LoginState : UiState {
        object Default : LoginState()
        object Loading : LoginState()
    }

    sealed class LoginEvent : UiEvent {
        object OnLoginClick : LoginEvent()
        object NavigateToRegistration : LoginEvent()
    }

    sealed class LoginEffect : UiEffect {
        data class ShowErrorMessage(val errorMsg: String) : LoginEffect()
        data class SuccessfulAuthorization(val token: String) : LoginEffect()
        object NavigateToRegistration : LoginEffect()
    }
}