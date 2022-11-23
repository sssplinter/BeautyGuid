package com.breaktime.signscreen.screen.login

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R

@Composable
fun RegistrationScreen(
    onSuccessfullyRegistration: () -> Unit,
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
            .padding(top = 64.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegistrationText()

        Column(
            modifier = modifier.padding(bottom = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            SingInField(
                input = email,
                isValid = isValidEmail,
                label = R.string.email_address,
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

        SignButton(
            isRegistration = true,
            onClick = {
                if (signInViewModel.onSignInClick()) {
                    onSuccessfullyRegistration()
                }
            })
    }
}

@Composable
fun RegistrationText(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.registration_title),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 32.dp)
                .padding(horizontal = 12.dp),
            text = stringResource(id = R.string.registration_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
        )
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
