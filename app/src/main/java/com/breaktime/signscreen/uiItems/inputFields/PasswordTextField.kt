package com.breaktime.signscreen.uiItems.inputFields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.authorization.SignInViewModel

@Composable
fun PasswordField(
    viewModel: SignInViewModel = viewModel()
) {
    Column {
        val focusManager = LocalFocusManager.current
        var isPasswordVisible by remember { mutableStateOf(false) }

        AppTextField(
            text = viewModel.password,
            label = { Text(text = stringResource(id = R.string.password)) },
            onChange = {
                viewModel.onPasswordValueChange(it)
            },
            imeAction = ImeAction.Next,//Show next as IME button
            leadingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        // TODO
                        contentDescription = "Password Visibility"
                    )
                }
            },
            visualTransformation =
            if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    PasswordField()
}