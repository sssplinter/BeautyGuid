package com.breaktime.signscreen.uiItems.inputFields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.breaktime.signscreen.R

@Composable
fun EmailField(
    emailValue: String,
    isValid: Boolean,
    errorText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val focusManager = LocalFocusManager.current

        AppTextField(
            text = emailValue,
            label = { Text(stringResource(R.string.email_address)) },
            isValid = isValid,
            errorText = errorText,
            onChange = {
                onValueChange(it)
            },
            imeAction = ImeAction.Next,//Show next as IME button
            visualTransformation = VisualTransformation.None,
            keyboardType = KeyboardType.Text, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
        )
    }
}