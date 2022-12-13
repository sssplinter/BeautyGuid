package com.breaktime.signscreen.screen.profile

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.uiItems.inputFields.*

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        val builder = Uri.Builder()
        val myUrl = builder.build()

        val lastSelectedImage = rememberSaveable {
            mutableStateOf(myUrl)
        }
        ImagePickerView(
            lastSelectedImage = lastSelectedImage.value,
            onSelection = { lastSelectedImage.value = it },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        ChapterDeliverText(
            stringResource(R.string.personal_data)
        )

        NameInputField(nameValue = "Sementsova",
            label = stringResource(R.string.surname_placeholder),
            onValueChange = { })
        NameInputField(nameValue = "Kristina",
            label = stringResource(R.string.name_placeholder),
            onValueChange = { })
        NameInputField(nameValue = "",
            label = stringResource(R.string.patronymic_placeholder),
            onValueChange = { })
        DataPickerTextField()

        ChapterDeliverText(stringResource(R.string.contacts))
        MobileNumberField()
        EmailField("kristsem@zdf.dsf", true, stringResource(R.string.invalid_email), {})

        Button(modifier = Modifier.fillMaxWidth(), onClick = { }) {
            Text(stringResource(R.string.save_btn))
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

