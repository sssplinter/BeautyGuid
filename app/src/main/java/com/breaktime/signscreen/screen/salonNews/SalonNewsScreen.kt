package com.breaktime.signscreen.screen.salonNews

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.appComponent
import com.breaktime.signscreen.uiItems.dialogs.IconButtonData
import com.breaktime.signscreen.uiItems.topBar.CommonTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

@Composable
fun SalonNewsScreen(
    salonNewsViewModel: SalonNewsViewModel = viewModel(
        factory = LocalContext.current.appComponent.salonNewsViewModel()
    ),
    onNavigateBack: (Int) -> Unit,
    onSalonClick: (Int) -> Unit,
    onSpecialistClick: (Int) -> Unit,
    salonId: Int,
    scrollToIndex: Int = 0
) {
    val scope = rememberCoroutineScope()
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        salonNewsViewModel,
        onNavigateBack,
        onSalonClick,
        onSpecialistClick,
        showLoadingDialog
    )

    // TODO resolve by injection
    LaunchedEffect(salonId) {
        salonNewsViewModel.setSalonId(salonId)
    }

    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.publications),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack,
                    onClick = {
                        salonNewsViewModel.setEvent(SalonNewsContract.SalonNewsEvent.OnNavigateBackClick)
                    })
            )
        }
    )
    { paddingValues ->
        SalonPhotosList(
            modifier = Modifier.padding((paddingValues)),
            salonPhotoList = salonNewsViewModel.salonNewsList,
            scrollToIndex = scrollToIndex,
            onSalonClick = { salonId ->
                salonNewsViewModel.setEvent(
                    SalonNewsContract.SalonNewsEvent.OnSalonClick(
                        salonId
                    )
                )
            },
            onSpecialistClick = { specialistId ->
                salonNewsViewModel.setEvent(
                    SalonNewsContract.SalonNewsEvent.OnSpecialistClick(
                        specialistId
                    )
                )
            }
        )
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    salonNewsViewModel: SalonNewsViewModel,
    onNavigateBack: (Int) -> Unit,
    onSalonClick: (Int) -> Unit,
    onSpecialistClick: (Int) -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        salonNewsViewModel.uiState.collect { state ->
            when (state) {
                is SalonNewsContract.SalonNewsState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        salonNewsViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                is SalonNewsContract.SalonNewsEffect.NavigateBack -> {
                    onNavigateBack(effect.salonId)
                }
                is SalonNewsContract.SalonNewsEffect.OpenSalonPortfolio -> {
                    onSalonClick(effect.salonId)
                }
                is SalonNewsContract.SalonNewsEffect.OpenSpecialistProfile -> {
                    onSpecialistClick(effect.specialistId)
                }
            }
        }
    }
}