package com.breaktime.signscreen.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.ButtonShape25
import com.breaktime.signscreen.ui.theme.SignScreenTheme

@Composable
fun LoginScreen(
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
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
        )
        SignInText()
        Column(
            modifier = modifier.padding(bottom = 30.dp),
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
                input = password,
                isValid = isValidPassword,
                label = R.string.password,
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

        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.no_account))
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable(enabled = true, onClick = {}),
                text = stringResource(id = R.string.register),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 0.9.dp,
                modifier = Modifier.weight(0.4f)
            )//, modifier = Modifier.width(120.dp))
            Text(
                text = "OR",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .weight(0.1f),
                textAlign = TextAlign.Center
            )
            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 0.9.dp,
                modifier = Modifier.weight(0.4f)
            )
        }

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
                .paddingFromBaseline(top = 24.dp, bottom = 24.dp)
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
        shape = ButtonShape25
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
fun LoginScreenPreview() {
    SignScreenTheme {
        LoginScreen({})
    }
}




