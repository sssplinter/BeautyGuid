package com.breaktime.signscreen.uiItems.inputFields

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.Country
import com.breaktime.signscreen.screen.profile.EditProfileViewModel
import com.breaktime.signscreen.ui.theme.ItemAllRoundedShape50

@Composable
fun MobileNumberField(
    viewModel: EditProfileViewModel
) {
    Column {
        val focusManager = LocalFocusManager.current

        AppTextField(
            text = viewModel.mobileNumber,
            label = { Text(stringResource(R.string.mobile_number)) },
            onChange = {
                viewModel.onMobileNumberValueChange(it)
            },
            imeAction = ImeAction.Next,//Show next as IME button
            leadingIcon = {
                viewModel.mobileCountry?.let {
                    CountryPickerView(
                        countries = viewModel.countriesList,
                        selectedCountry = it,
                        onSelection = { country ->
                            viewModel.mobileCountry = country
                        },
                    )
                }
            },
            visualTransformation = VisualTransformation.None,
            keyboardType = KeyboardType.Phone,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
    }
}

@Composable
fun CountryPickerView(
    selectedCountry: Country,
    onSelection: (Country) -> Unit,
    countries: List<Country>,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Row(modifier = modifier.clickable {
        showDialog = true
    }, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .border(0.5.dp, Color.Gray),
            painter = painterResource(getFlagEmojiFor(selectedCountry)),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp, end = 8.dp),
            text = selectedCountry.numberCode
        )
    }


    if (showDialog)
        CountryCodePickerDialog(countries, onSelection) {
            showDialog = false
        }
}

@Composable
fun CountryCodePickerDialog(
    countries: List<Country>,
    onSelection: (Country) -> Unit,
    dismiss: () -> Unit,
) {
    Dialog(onDismissRequest = dismiss) {
        Box {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 40.dp)
                    .background(shape = RoundedCornerShape(20.dp), color = Color.White)
            ) {
                for (country in countries) {
                    item {
                        CountryPickerItem(country, onSelection, dismiss)
                    }
                }
            }
        }
    }
}

@Composable
fun CountryPickerItem(
    country: Country, onSelection: (Country) -> Unit,
    dismiss: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .border(1.dp, Color.Gray, ItemAllRoundedShape50),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .border(0.5.dp, Color.Gray),
            painter = painterResource(getFlagEmojiFor(country)),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .clickable {
                    onSelection(country)
                    dismiss()
                }
                .fillMaxWidth()
                .padding(10.dp)
                .weight(1f),
            text = country.name,
            // TODO apply text style
        )
    }
}

fun getFlagEmojiFor(country: Country): Int {
    return R.drawable.icon_bg_flag
}
