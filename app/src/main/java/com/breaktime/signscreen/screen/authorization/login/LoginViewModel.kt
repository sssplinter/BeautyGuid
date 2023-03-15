package com.breaktime.signscreen.screen.authorization.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.domain.pref.SetIsAuthorizedUseCase
import com.breaktime.signscreen.domain.pref.SetUserTokenUseCase
import com.breaktime.signscreen.screen.authorization.common.AuthorizationContract.*
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.utils.isValidPassword
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setUserTokenUseCase: SetUserTokenUseCase,
    private val setIsAuthorizedUseCase: SetIsAuthorizedUseCase
) :
    BaseViewModel<AuthEvent, AuthState, AuthEffect>() {


    var login by mutableStateOf("")
    var isValidLogin by mutableStateOf(true)

    var password by mutableStateOf("")
    var isValidPassword by mutableStateOf(true)

    var token by mutableStateOf("")

    fun onLoginValueChange(value: String) {
        login = value
        isValidLogin = true
    }

    fun onPasswordValueChange(value: String) {
        password = value
        isValidPassword = true
    }

    private fun loginUser() {
        if (login.isNotEmpty() && login.isNotBlank() && isValidPassword(password)) {
            viewModelScope.launch {
                isValidLogin = true
                isValidPassword = true

                setState { AuthState.Loading }
                token = loginUseCase(login, password)?.token ?: ""

                setUserTokenUseCase(token)

                setState { AuthState.Default }

                if (token.isNotBlank() && token.isNotEmpty()) {
                    setIsAuthorizedUseCase.invoke(true)
                    setEffect { AuthEffect.SuccessfulAuthorization(token) }
                } else {
                    val errorMessage = "Not correct credentials. Check and try again"
                    setEffect { AuthEffect.ShowErrorMessage(errorMessage) }
                }
            }
        } else {
            if (login.isEmpty() || login.isBlank()) {
                isValidLogin = false
                val errorMessage = "Login field must be filled"
                setEffect { AuthEffect.ShowErrorMessage(errorMessage) }
            }
            if (!isValidPassword(password)) {
                isValidPassword = false
                val errorMessage = "Password must contain at least 8 characters"
                setEffect { AuthEffect.ShowErrorMessage(errorMessage) }
            }
        }
    }

    override fun createInitialState(): AuthState {
        return AuthState.Default
    }

    override fun handleEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.OnAuthClick -> {
                loginUser()
            }
            AuthEvent.OnAnotherAuthTypeClick -> {
                setEffect { AuthEffect.NavigateToAnotherAuthType }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val loginUseCase: LoginUseCase,
        private val setUserTokenUseCase: SetUserTokenUseCase,
        private val setIsAuthorizedUseCase: SetIsAuthorizedUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(loginUseCase, setUserTokenUseCase, setIsAuthorizedUseCase) as T
        }
    }
}