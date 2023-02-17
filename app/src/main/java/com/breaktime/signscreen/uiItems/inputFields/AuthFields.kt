package com.breaktime.signscreen.uiItems.inputFields

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.breaktime.signscreen.R

@Composable
fun PasswordField(
    password: String,
    isValid: Boolean = true,
    label: Int,
    onPasswordValueChange: (String) -> Unit,
) {
    Column {
        val focusManager = LocalFocusManager.current
        var isPasswordVisible by remember { mutableStateOf(false) }

        AppTextField(
            text = password,
            isValid = isValid,
            errorText = stringResource(if (password.isBlank()) R.string.input_password else R.string.invalid_password),
            label = { Text(text = stringResource(id = label)) },
            onChange = {
                onPasswordValueChange(it)
            },
            imeAction = ImeAction.Next,
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation =
            if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }
}

@Composable
fun ConfirmPasswordField(
    password: String,
    isValid: Boolean = true,
    label: Int,
    onPasswordValueChange: (String) -> Unit,
) {
    Column {
        val focusManager = LocalFocusManager.current
        var isPasswordVisible by remember { mutableStateOf(false) }

        AppTextField(
            text = password,
            isValid = isValid,
            errorText = stringResource(R.string.repeat_password),
            label = { Text(text = stringResource(id = label)) },
            onChange = {
                onPasswordValueChange(it)
            },
            imeAction = ImeAction.Next,
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation =
            if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }
}

@Composable
fun LoginField(
    login: String,
    isValid: Boolean,
    @StringRes label: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AppTextField(
        modifier = modifier,
        onChange = { onValueChange(it) },
        text = login,
        label = { Text(text = stringResource(id = label)) },
        isValid = isValid,
        errorText = stringResource(if (login.isBlank()) R.string.input_login else R.string.invalid_login),
        visualTransformation = VisualTransformation.None
    )
}