package com.breaktime.signscreen.uiItems.inputFields

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun NameInputField(
    nameValue: String,
    isValid: Boolean,
    @StringRes label: Int,
    @StringRes errorText: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val focusManager = LocalFocusManager.current
        val context = LocalContext.current

        AppTextField(
            text = nameValue,
            label = { Text(text = stringResource(id = label)) },
            onChange = {
                onValueChange(it)
            },
            isValid = isValid,
            imeAction = ImeAction.Next,//Show next as IME button
            visualTransformation = VisualTransformation.None,
            keyboardType = KeyboardType.Text, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ), errorText = stringResource(id = errorText)
        )
    }
}