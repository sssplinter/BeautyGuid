package com.breaktime.signscreen.screen.authorization.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.screen.authorization.common.AuthorizationContract.*
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.utils.isValidEmail
import com.breaktime.signscreen.utils.isValidPassword
import kotlinx.coroutines.launch

class LoginViewModel :
    BaseViewModel<AuthEvent, AuthState, AuthEffect>() {

    // TODO replace by injection
    private val loginUseCase: LoginUseCase = LoginUseCase()

    var login by mutableStateOf("")
//    var isValidLogin by mutableStateOf(true)

    var password by mutableStateOf("")
//    var isValidPassword by mutableStateOf(true)

    var token by mutableStateOf("")

    fun onLoginValueChange(value: String) {
        login = value
//        isValidLogin = login.isNotBlank()
    }

    fun onPasswordValueChange(value: String) {
        password = value
//        isValidPassword = password.isNotBlank()
    }

    private fun loginUser() {
        when {
            !isValidPassword(password) -> {
                val errorMessage = "Password must contain at least 8 characters"
                setEffect { AuthEffect.ShowErrorMessage(errorMessage) }
            }
            !isValidEmail(login) -> {
                val errorMessage = "Incorrect email format"
                setEffect { AuthEffect.ShowErrorMessage(errorMessage) }
            }
            else -> {
                viewModelScope.launch {
                    setState { AuthState.Loading }
                    token = loginUseCase(login, password)?.token ?: ""
                    setState { AuthState.Default }
                    if (token.isNotBlank() && token.isNotEmpty()) {
                        setEffect { AuthEffect.SuccessfulAuthorization(token) }
                    } else {
                        val errorMessage = "Not correct credentials. Check and try again"
                        setEffect { AuthEffect.ShowErrorMessage(errorMessage) }
                    }
                }
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
}