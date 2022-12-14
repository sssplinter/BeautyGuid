package com.breaktime.signscreen.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.uiItems.inputFields.*

@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        ImagePickerFromGallery(
            lastSelectedImage = viewModel.avatarUri,
            onSelection = { uri -> viewModel.onImageChange(uri) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        ChapterDeliverText(
            stringResource(R.string.personal_data)
        )
        NameInputField(
            nameValue = viewModel.surname,
            label = stringResource(R.string.surname_placeholder),
            onValueChange = { value ->
                viewModel.onSurnameValueChange(value)
            })
        NameInputField(
            nameValue = viewModel.name,
            label = stringResource(R.string.name_placeholder),
            onValueChange = { value ->
                viewModel.onNameValueChange(value)
            })
        NameInputField(
            nameValue = viewModel.patronymic,
            label = stringResource(R.string.patronymic_placeholder),
            onValueChange = { value ->
                viewModel.onPatronymicValueChange(value)
            })
        DataPickerTextField(viewModel)

        ChapterDeliverText(stringResource(R.string.contacts))
        MobileNumberField(viewModel)
        EmailField(
            viewModel.email,
            { value -> viewModel.onEmailValueChange(value) })

        Button(
            onClick = { },
            modifier = modifier
                .padding(top = 16.dp, bottom = 32.dp)
                .fillMaxWidth()
                .height(45.dp),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            // border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                stringResource(R.string.save_btn),
                style = MaterialTheme.typography.button.copy(fontSize = 16.sp)
            )
        }
    }
}

@Composable
fun ChapterDeliverText(chapter: String, modifier: Modifier = Modifier) {
    Text(
        text = chapter,
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
        modifier = modifier.padding(top = 16.dp, bottom = 4.dp)
    )
}

@Preview
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}

