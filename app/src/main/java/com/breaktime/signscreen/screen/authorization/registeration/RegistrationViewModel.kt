package com.breaktime.signscreen.screen.authorization.registeration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.domain.authorization.RegistrationUseCase
import com.breaktime.signscreen.screen.authorization.common.AuthorizationContract
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.utils.isValidEmail
import com.breaktime.signscreen.utils.isValidPassword
import kotlinx.coroutines.launch

class RegistrationViewModel :
    BaseViewModel<AuthorizationContract.AuthEvent, AuthorizationContract.AuthState, AuthorizationContract.AuthEffect>() {

    // TODO replace by injection
    private val registrationUseCase: RegistrationUseCase = RegistrationUseCase()

    var login by mutableStateOf("")
//    var isValidLogin by mutableStateOf(true)

    var password by mutableStateOf("")

    var confirmPassword by mutableStateOf("")
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

    fun onConfirmPasswordValueChange(value: String) {
        confirmPassword = value
//        isValidPassword = password.isNotBlank()
    }

    private fun registerUser() {
        when {
            !isValidPassword(password) -> {
                val errorMessage = "Password must contain at least 8 characters"
                setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
            }
            !isValidEmail(login) -> {
                val errorMessage = "Incorrect email format"
                setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
            }
            password != confirmPassword -> {
                val errorMessage = "Passwords do not match. Check and try again"
                setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
            }
            else -> {
                viewModelScope.launch {
                    setState { AuthorizationContract.AuthState.Loading }

                    token = registrationUseCase(login, password)?.token ?: ""

                    setState { AuthorizationContract.AuthState.Default }

                    if (token.isNotBlank() && token.isNotEmpty()) {
                        setEffect { AuthorizationContract.AuthEffect.SuccessfulAuthorization(token) }
                    } else {
                        val errorMessage = "Something went wrong. Try again later."
                        setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
                    }
                }
            }
        }
    }

    override fun createInitialState(): AuthorizationContract.AuthState {
        return AuthorizationContract.AuthState.Default
    }

    override fun handleEvent(event: AuthorizationContract.AuthEvent) {
        when (event) {
            AuthorizationContract.AuthEvent.OnAuthClick -> {
                registerUser()
            }
            AuthorizationContract.AuthEvent.OnAnotherAuthTypeClick -> {
                setEffect { AuthorizationContract.AuthEffect.NavigateToAnotherAuthType }
            }
        }
    }
}