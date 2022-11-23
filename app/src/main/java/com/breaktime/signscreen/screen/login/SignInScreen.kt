package com.breaktime.signscreen.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.ButtonShape25
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun SignInScreen(
    onSuccessfullyRegistration: () -> Unit,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = viewModel()
) {
    val email = signInViewModel.email
    val isValidEmail = signInViewModel.isValidEmail

    val password = signInViewModel.password
    val isValidPassword = signInViewModel.isValidPassword

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 64.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInText()

        SingInField(
            input = email,
            isValid = isValidEmail,
            label = R.string.email_address,
            onValueChange = { value ->
                signInViewModel.onEmailValueChange(value)
            })

        SingInField(
            input = password,
            isValid = isValidPassword,
            label = R.string.password,
            onValueChange = { value ->
                signInViewModel.onPasswordValueChange(value)
            })

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
fun SignInText(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.sign_in_title),
            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp, bottom = 32.dp)
                .padding(horizontal = 12.dp),
            text = stringResource(id = R.string.sign_in_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
        )
    }
}


@Composable
fun SignButton(
    isRegistration: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = ButtonShape25,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
    ) {
        Text(
            text = stringResource(if (isRegistration) R.string.register else R.string.sign_in),
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
        )
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    SignScreenTheme {
        SignInScreen({})
    }
}




