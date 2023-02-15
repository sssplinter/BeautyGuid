package com.breaktime.signscreen.screen.authorization.register

import androidx.annotation.StringRes
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.authorization.views.fields.FlexibleInputField
import com.breaktime.signscreen.screen.authorization.SignInViewModel
import com.breaktime.signscreen.screen.authorization.views.AuthorizationButton
import com.breaktime.signscreen.screen.authorization.views.AuthorizationLogo
import com.breaktime.signscreen.screen.authorization.views.AuthorizationRedirect
import com.breaktime.signscreen.screen.authorization.views.AuthorizationText
import com.breaktime.signscreen.uiItems.button.GradientBordersButton
import com.breaktime.signscreen.uiItems.divider.DividerWithText

@Composable
fun RegistrationScreen(
    onSuccessfullyRegistration: () -> Unit,
    onRedirectToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = viewModel()
) {
    val email = signInViewModel.email
    val isValidEmail = signInViewModel.isValidEmail

    val newPassword = signInViewModel.password
    val isValidNewPassword = signInViewModel.isValidPassword

    // TODO
    val confirmPassword = signInViewModel.password
    val isValidConfirmPassword = signInViewModel.isValidPassword

    Column(
        modifier = modifier
            .fillMaxSize()
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
            SingInField(
                input = email,
                isValid = isValidEmail,
                label = R.string.login,
                onValueChange = { value ->
                    signInViewModel.onEmailValueChange(value)
                })

            SingInField(
                input = newPassword,
                isValid = isValidNewPassword,
                label = R.string.password,
                onValueChange = { value ->
                    signInViewModel.onPasswordValueChange(value)
                })

            SingInField(
                input = confirmPassword,
                isValid = isValidConfirmPassword,
                label = R.string.confirm_password,
                onValueChange = { value ->
                    signInViewModel.onPasswordValueChange(value)
                })
        }

        AuthorizationButton(isRegistration = true, onClick = {
            if (signInViewModel.onSignInClick()) {
                onSuccessfullyRegistration()
            }
        })

        AuthorizationRedirect(
            isRegistration = true,
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
}

@Composable
fun SingInField(
    input: String,
    isValid: Boolean,
    @StringRes label: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlexibleInputField(
        value = input, onValueChange = onValueChange, isValid = isValid,
        stringResource(if (input.isBlank()) R.string.input_password else R.string.invalid_password),
        label = { Text(text = stringResource(id = label)) }, modifier = modifier
    )
}
