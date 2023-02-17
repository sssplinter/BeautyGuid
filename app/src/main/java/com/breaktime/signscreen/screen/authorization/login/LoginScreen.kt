package com.breaktime.signscreen.screen.authorization.login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.authorization.register.SingInField
import com.breaktime.signscreen.screen.authorization.views.AuthorizationButton
import com.breaktime.signscreen.screen.authorization.views.AuthorizationLogo
import com.breaktime.signscreen.screen.authorization.views.AuthorizationRedirect
import com.breaktime.signscreen.screen.authorization.views.AuthorizationText
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.button.GradientBordersButton
import com.breaktime.signscreen.uiItems.divider.DividerWithText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Login(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel(),
    onRedirectToRegistration: () -> Unit,
    onSuccessfullyRegistration: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        context,
        loginViewModel,
        onRedirectToRegistration,
        onSuccessfullyRegistration,
        showLoadingDialog
    )

    val login = loginViewModel.login
    val password = loginViewModel.password

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        LoginScreen(
            login = login,
            password = password,
            onLoginValueChange = { login -> loginViewModel.onLoginValueChange(login) },
            onPasswordValueChange = { password ->
                loginViewModel.onPasswordValueChange(
                    password
                )
            },
            onLoginClick = {
                loginViewModel.setEvent(LoginContract.LoginEvent.OnLoginClick)
            },
            onRedirectToRegistration = {
                loginViewModel.setEvent(LoginContract.LoginEvent.NavigateToRegistration)
            }, showLoadingDialog
        )
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    loginViewModel: LoginViewModel,
    onRedirectToRegistration: () -> Unit,
    onSuccessfullyRegistration: () -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        loginViewModel.uiState.collect { state ->
            when (state) {
                is LoginContract.LoginState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        loginViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                is LoginContract.LoginEffect.ShowErrorMessage -> {
                    Toast.makeText(
                        context, effect.errorMsg, Toast.LENGTH_SHORT
                    ).show()
                }
                is LoginContract.LoginEffect.NavigateToRegistration -> {
                    onRedirectToRegistration()
                }
                is LoginContract.LoginEffect.SuccessfulAuthorization -> {
                    onSuccessfullyRegistration()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    login: String,
    password: String,
    onLoginValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRedirectToRegistration: () -> Unit,
    showLoadingDialog: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthorizationLogo(Modifier.size(120.dp))

            AuthorizationText(isRegistration = false)

            Column(
                modifier = modifier.padding(bottom = 30.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                SingInField(
                    input = login,
                    isValid = true,
                    label = R.string.login,
                    onValueChange = { value ->
                        onLoginValueChange(value)
                    })

                SingInField(input = password,
                    isValid = true,
                    label = R.string.password,
                    onValueChange = { value ->
                        onPasswordValueChange(value)
                    })
            }

            AuthorizationButton(isRegistration = false, onClick = {
                onLoginClick()
            })

            AuthorizationRedirect(isRegistration = false,
                onRedirectToRegistration = { onRedirectToRegistration() })

            DividerWithText(R.string.or)

            GradientBordersButton(
                onClick = { },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
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
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    SignScreenTheme {
        Login(onSuccessfullyRegistration = {}, onRedirectToRegistration = {})
    }
}




