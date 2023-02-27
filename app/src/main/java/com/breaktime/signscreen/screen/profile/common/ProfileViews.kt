package com.breaktime.signscreen.screen.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.ui.theme.ButtonShape25
import com.breaktime.signscreen.uiItems.inputFields.EmailField
import com.breaktime.signscreen.uiItems.inputFields.MobileNumberField
import com.breaktime.signscreen.uiItems.inputFields.NameInputField

@Composable
fun ChapterDeliverText(chapter: String, modifier: Modifier = Modifier) {
    Text(
        text = chapter,
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
        modifier = modifier.padding(top = 16.dp, bottom = 4.dp, start = 8.dp)
    )
}

@Composable
fun BottomDrawerItem(
    imageVector: ImageVector, title: String, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Button(onClick = { onClick() }, modifier = modifier.fillMaxWidth(), shape = ButtonShape25) {
        Icon(
            imageVector,
            modifier = Modifier.padding(end = 8.dp),
            contentDescription = stringResource(R.string.choose_from_gallery)
        )
        Text(text = title)
    }
}

@Composable
fun ProfileSlotBasedSection(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        ChapterDeliverText(
            stringResource(titleRes)
        )
        content()
    }
}

@Composable
fun ContactsSection(
    email: String,
    mobileNumber: String,
    isEmailValid: Boolean,
    isMobileNumberValid: Boolean,
    onEmailValueChange: (String) -> Unit,
    onMobileNumberValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MobileNumberField(
            mobileNumber = mobileNumber,
            isValid = isMobileNumberValid,
            onMobileNumberValueChange = onMobileNumberValueChange
        )
        EmailField(
            emailValue = email,
            isValid = isEmailValid,
            errorText = "Incorrect email",
            onEmailValueChange)
    }
}

@Composable
fun PersonalDataSection(
    surname: String,
    name: String,
    isSurnameValid: Boolean,
    isNameValid: Boolean,
    onSurnameValueChange: (String) -> Unit,
    onNameValueChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        NameInputField(nameValue = surname,
            label = R.string.surname_placeholder,
            errorText = R.string.input_field_error,
            isValid = isSurnameValid,
            onValueChange = { value ->
                onSurnameValueChange(value)
            })
        NameInputField(nameValue = name,
            label = R.string.name_placeholder,
            errorText = R.string.input_field_error,
            isValid = isNameValid,
            onValueChange = { value ->
                onNameValueChange(value)
            })
    }
}