package com.breaktime.signscreen.screen.visit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.screen.visit.items.SelectDateTimeSection
import com.breaktime.signscreen.screen.visit.items.specialist.SelectSpecialistSection
import com.breaktime.signscreen.screen.visit.items.service.SelectServiceSection
import com.breaktime.signscreen.ui.theme.BackgroundGray
import com.breaktime.signscreen.ui.theme.ButtonShape25
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar

@Composable
fun BookVisitMainScreen(
    viewModel: BookVisitViewModel = viewModel()
) {
    // TODO something not correct
    val isBookingAvailable by viewModel.isBookingAvailable.collectAsState(false)
    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.book_visit),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack, onClick = {
                    viewModel.setEvent(BookVisitContract.BookVisitEvent.OnBackClick)
                })
            )
        }, backgroundColor = MaterialTheme.colors.BackgroundGray
    ) { paddingValues ->
        BookVisitMain(
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel,
            isBookingAvailable = isBookingAvailable
        )
    }
}

@Composable
fun BookVisitMain(
    viewModel: BookVisitViewModel,
    modifier: Modifier = Modifier,
    isBookingAvailable: Boolean = false
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .background(MaterialTheme.colors.BackgroundGray),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SelectServiceSection(
                modifier = Modifier.padding(vertical = 4.dp),
                selectedServices = viewModel.selectedServices, onSelectClick = {},
                onDeselectService = { value -> viewModel.deselectService(value) })

            SelectSpecialistSection(
                modifier = Modifier.padding(vertical = 4.dp),
                specialistInfo = viewModel.selectedSpecialist,
                onClick = {},
                onDeselect = { viewModel.selectedSpecialist = null }
            )

            SelectDateTimeSection(
                modifier = Modifier.padding(vertical = 4.dp),
                selectedDateTimeInfo = viewModel.selectedDateTimeInfo,
                onClick = {},
                onDeselect = { viewModel.selectedDateTimeInfo = null }
            )
        }

        if (isBookingAvailable) {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .fillMaxWidth()
                    .height(55.dp),
                shape = ButtonShape25
            ) {
                Text(
                    text = stringResource(id = R.string.book_visit),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold, fontSize = 19.sp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview2() {
    SignScreenTheme {
        BookVisitMainScreen()
    }
}