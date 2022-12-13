package com.breaktime.signscreen.uiItems.inputFields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.profile.EditProfileViewModel


@Composable
fun DataPickerTextField(
    viewModel: EditProfileViewModel
) {
    Column {
        val focusManager = LocalFocusManager.current
        val context = LocalContext.current

        AppTextField(
            modifier = Modifier.clickable {
                viewModel.showDatePickerDialog(context)
            },
            text = viewModel.dateOfBirth,
            label = { Text(stringResource(id = R.string.birthday)) },
            onChange = {
                viewModel.onBirthdayValueChange(it)
            },
            isEnabled = false,
            imeAction = ImeAction.Next,//Show next as IME button
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            visualTransformation =
            VisualTransformation.None,
            keyboardType = KeyboardType.Text, //Plain text keyboard
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
fun DataPickerTextFieldPreview() {
    //DataPickerTextField()
}