package com.breaktime.signscreen.screen.authorization.views.fields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.breaktime.signscreen.ui.theme.InputFieldRoundedShape25

@Composable
fun FlexibleInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    errorText: String,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = label,
            shape = InputFieldRoundedShape25,
            isError = !isValid
        )
        if (!isValid) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error
            )
        }
    }
}


