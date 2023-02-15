package com.breaktime.signscreen.screen.authorization.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun LoginScreen(
    onSuccessfullyRegistration: () -> Unit,
    onRedirectToRegistration: () -> Unit,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
) {
    val login = loginViewModel.login
    val isValidLogin = loginViewModel.isValidLogin

    val password = loginViewModel.password
    val isValidPassword = loginViewModel.isValidPassword

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
            SingInField(input = login,
                isValid = isValidLogin,
                label = R.string.login,
                onValueChange = { value ->
                    loginViewModel.onLoginValueChange(value)
                })

            SingInField(input = password,
                isValid = isValidPassword,
                label = R.string.password,
                onValueChange = { value ->
                    loginViewModel.onPasswordValueChange(value)
                })
        }

        AuthorizationButton(isRegistration = false, onClick = {
            loginViewModel.onLoginClick()
        })

        AuthorizationRedirect(
            isRegistration = false,
            onRedirectToRegistration = { onRedirectToRegistration() })

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
}

@Preview
@Composable
fun LoginScreenPreview() {
    SignScreenTheme {
        LoginScreen({}, {})
    }
}




