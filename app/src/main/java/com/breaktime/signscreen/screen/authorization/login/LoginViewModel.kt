package com.breaktime.signscreen.screen.authorization.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.screen.authorization.login.LoginContract.*
import com.breaktime.signscreen.screen.base.BaseViewModel
import com.breaktime.signscreen.utils.isValidEmail
import com.breaktime.signscreen.utils.isValidPassword
import kotlinx.coroutines.launch

class LoginViewModel :
    BaseViewModel<LoginEvent, LoginState, LoginEffect>() {

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
                setEffect { LoginEffect.ShowErrorMessage(errorMessage) }
            }
            !isValidEmail(login) -> {
                val errorMessage = "Incorrect email format"
                setEffect { LoginEffect.ShowErrorMessage(errorMessage) }
            }
            else -> {
                viewModelScope.launch {
                    setState { LoginState.Loading }
                    token = loginUseCase(login, password)?.token ?: ""
                    setState { LoginState.Default }
                    if (token.isNotBlank() && token.isNotEmpty()) {
                        setEffect { LoginEffect.SuccessfulAuthorization(token) }
                    } else {
                        val errorMessage = "Not correct credentials. Check and try again"
                        setEffect { LoginEffect.ShowErrorMessage(errorMessage) }
                    }
                }
            }
        }
    }

    override fun createInitialState(): LoginState {
        return LoginState.Default
    }

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnLoginClick -> {
                loginUser()
            }
            LoginEvent.NavigateToRegistration -> {
                setEffect { LoginEffect.NavigateToRegistration }
            }
        }
    }
}