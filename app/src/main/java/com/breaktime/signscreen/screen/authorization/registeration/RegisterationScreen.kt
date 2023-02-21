package com.breaktime.signscreen.screen.authorization.registeration

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.authorization.common.AuthorizationContract
import com.breaktime.signscreen.screen.authorization.common.views.AuthorizationButton
import com.breaktime.signscreen.screen.authorization.common.views.AuthorizationLogo
import com.breaktime.signscreen.screen.authorization.common.views.AuthorizationRedirect
import com.breaktime.signscreen.screen.authorization.common.views.AuthorizationText
import com.breaktime.signscreen.uiItems.button.GradientBordersButton
import com.breaktime.signscreen.uiItems.divider.DividerWithText
import com.breaktime.signscreen.uiItems.inputFields.ConfirmPasswordField
import com.breaktime.signscreen.uiItems.inputFields.LoginField
import com.breaktime.signscreen.uiItems.inputFields.PasswordField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun Registration(
    modifier: Modifier = Modifier,
    registrationViewModel: RegistrationViewModel = viewModel(),
    onRedirectToLogin: () -> Unit,
    onSuccessfullyRegistration: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        context,
        registrationViewModel,
        onRedirectToLogin,
        onSuccessfullyRegistration,
        showLoadingDialog
    )

    val login = registrationViewModel.login
    val password = registrationViewModel.password
    val confirmPassword = registrationViewModel.confirmPassword
    val isValidLogin = registrationViewModel.isValidLogin
    val isValidPassword = registrationViewModel.isValidPassword
    val isValidConfirmPassword = registrationViewModel.isValidConfirmPassword

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        RegistrationScreen(
            login = login,
            password = password,
            confirmPassword = confirmPassword,
            isValidLogin = isValidLogin,
            isValidPassword = isValidPassword,
            isValidConfirmPassword = isValidConfirmPassword,
            showLoadingDialog = showLoadingDialog,
            onLoginValueChange = { login -> registrationViewModel.onLoginValueChange(login) },
            onPasswordValueChange = { password ->
                registrationViewModel.onPasswordValueChange(
                    password
                )
            },
            onConfirmPasswordValueChange = { confirmPassword ->
                registrationViewModel.onConfirmPasswordValueChange(confirmPassword)
            },
            onRegistrationClick = {
                registrationViewModel.setEvent(AuthorizationContract.AuthEvent.OnAuthClick)
            },
            onRedirectToLogin = {
                registrationViewModel.setEvent(AuthorizationContract.AuthEvent.OnAnotherAuthTypeClick)
            },
        )
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    registrationViewModel: RegistrationViewModel,
    onRedirectToRegistration: () -> Unit,
    onSuccessfullyRegistration: () -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        registrationViewModel.uiState.collect { state ->
            when (state) {
                is AuthorizationContract.AuthState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        registrationViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                is AuthorizationContract.AuthEffect.ShowErrorMessage -> {
                    Toast.makeText(
                        context, effect.errorMsg, Toast.LENGTH_SHORT
                    ).show()
                }
                is AuthorizationContract.AuthEffect.NavigateToAnotherAuthType -> {
                    onRedirectToRegistration()
                }
                is AuthorizationContract.AuthEffect.SuccessfulAuthorization -> {
                    onSuccessfullyRegistration()
                }
            }
        }
    }
}

@Composable
fun RegistrationScreen(
    login: String,
    password: String,
    confirmPassword: String,
    onLoginValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onConfirmPasswordValueChange: (String) -> Unit,
    onRegistrationClick: () -> Unit,
    onRedirectToLogin: () -> Unit,
    showLoadingDialog: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    isValidLogin: Boolean = true,
    isValidPassword: Boolean = true,
    isValidConfirmPassword: Boolean = true
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthorizationLogo(Modifier.size(120.dp))

            AuthorizationText(isRegistration = true)

            Column(
                modifier = modifier.padding(bottom = 30.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                LoginField(login = login,
                    isValid = isValidLogin,
                    label = R.string.login,
                    onValueChange = { value ->
                        onLoginValueChange(value)
                    })

                PasswordField(
                    password = password,
                    isValid = isValidPassword,
                    label = R.string.password,
                    onPasswordValueChange = { value ->
                        onPasswordValueChange(value)
                    })

                ConfirmPasswordField(
                    password = confirmPassword,
                    isValid = isValidConfirmPassword,
                    label = R.string.confirm_password,
                    onPasswordValueChange = { value ->
                        onConfirmPasswordValueChange(value)
                    })
            }

            AuthorizationButton(isRegistration = true, onClick = {
                onRegistrationClick()
            })

            AuthorizationRedirect(isRegistration = true,
                onRedirectToRegistration = { onRedirectToLogin() })

            DividerWithText(R.string.or)

            GradientBordersButton(onClick = { }, modifier = Modifier.padding(vertical = 8.dp)) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = ""
                )
                Text(
                    text = stringResource(R.string.continue_with_google),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .weight(0.8f),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Medium, fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
        if (showLoadingDialog.value) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }
        }
    }
}



