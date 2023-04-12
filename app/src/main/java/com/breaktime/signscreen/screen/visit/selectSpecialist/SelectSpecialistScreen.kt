package com.breaktime.signscreen.screen.visit.selectSpecialist

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SpecialistInfo
import com.breaktime.signscreen.screen.appointments.listItems.SpecialistListItem
import com.breaktime.signscreen.ui.theme.BackgroundGray
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun SelectSpecialistScreen(
    modifier: Modifier = Modifier,
    onMoreInfoClick: (Int) -> Unit,
    onSelectSpecialist: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.choose_specialist),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack, onClick = {
                })
            )
        }, backgroundColor = MaterialTheme.colors.BackgroundGray
    ) { paddingValues ->
        SelectSpecialistList(
            modifier = Modifier.padding(paddingValues),
            specialists = listOf(
                SpecialistInfo(
                    id = 1,
                    salonId = 1,
                    salonName = "Marcel",
                    fullName = "Kristina Sementsova",
                    specialisation = "Specialist of MASSAGE",
                    photoUrl = "1_special_profile_photo.jpg",
                    rating = 5.0,
                    marksCount = 123
                ), SpecialistInfo(
                    id = 1,
                    salonId = 1,
                    salonName = "Marcel",
                    fullName = "Kristina Sementsova",
                    specialisation = "Specialist of MASSAGE",
                    photoUrl = "1_special_profile_photo.jpg",
                    rating = 5.0,
                    marksCount = 123
                )
            ),
            {},
            {})
    }
}

@Composable
fun SelectSpecialistList(
    modifier: Modifier = Modifier,
    specialists: List<SpecialistInfo>,
    onMoreInfoClick: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colors.BackgroundGray),
    ) {
        items(specialists) { specialist ->
            SpecialistListItem(
                modifier = Modifier.padding(vertical = 3.dp, horizontal = 4.dp),
                specialistInfo = specialist,
                onItemClick = { onClick(specialist.id) },
                onMoreInfoClick = { onMoreInfoClick(specialist.id) },
                onSalonClick = {},
                onBookVisitClick = {},
                isPreview = true
            )
        }
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    selectSpecialistViewModel: SelectSpecialistViewModel,
    onNavigateBack: () -> Unit,
    onSalonClick: (Int) -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        selectSpecialistViewModel.uiState.collect { state ->
            when (state) {
                SelectSpecialistContract.SelectSpecialistState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        selectSpecialistViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                SelectSpecialistContract.SelectSpecialistEffect.NavigateBack -> {}
                is SelectSpecialistContract.SelectSpecialistEffect.SelectSpecialist -> {}
                is SelectSpecialistContract.SelectSpecialistEffect.OpenMoreInfo -> {}
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SignScreenTheme {
        SelectSpecialistScreen(onSelectSpecialist = {}, onMoreInfoClick = {})
    }
}