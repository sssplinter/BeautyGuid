package com.breaktime.signscreen.screen.authorization.registeration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.domain.authorization.RegistrationUseCase
import com.breaktime.signscreen.screen.authorization.common.AuthorizationContract
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.utils.isValidPassword
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registrationUseCase: RegistrationUseCase) :
    BaseViewModel<AuthorizationContract.AuthEvent, AuthorizationContract.AuthState, AuthorizationContract.AuthEffect>() {

    var login by mutableStateOf("")
    var isValidLogin by mutableStateOf(true)

    var password by mutableStateOf("")
    var isValidPassword by mutableStateOf(true)

    var confirmPassword by mutableStateOf("")
    var isValidConfirmPassword by mutableStateOf(true)

    var token by mutableStateOf("")

    fun onLoginValueChange(value: String) {
        login = value
        isValidLogin = true
    }

    fun onPasswordValueChange(value: String) {
        password = value
        isValidPassword = true
    }

    fun onConfirmPasswordValueChange(value: String) {
        confirmPassword = value
        isValidConfirmPassword = true
    }

    private fun registerUser() {
        if (login.isNotEmpty() && login.isNotBlank() && isValidPassword(password) && password == confirmPassword) {
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
        } else {
            if (login.isEmpty() || login.isBlank()) {
                isValidLogin = false
                val errorMessage = "Incorrect email format"
                setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
            }
            if (!isValidPassword(password)) {
                isValidPassword = false
                val errorMessage = "Password must contain at least 8 characters"
                setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
            }
            if (password != confirmPassword) {
                isValidConfirmPassword = false
                val errorMessage = "Passwords do not match. Check and try again"
                setEffect { AuthorizationContract.AuthEffect.ShowErrorMessage(errorMessage) }
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

    @Suppress("UNCHECKED_CAST")
    class Factory(private val registrationUseCase: RegistrationUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegistrationViewModel(registrationUseCase) as T
        }
    }
}