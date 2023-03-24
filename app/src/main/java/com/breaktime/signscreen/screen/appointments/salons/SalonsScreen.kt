package com.breaktime.signscreen.screen.appointments.salons

import android.content.Context
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
fun SalonsScreen(
    onNavigateBack: () -> Unit,
    onOpenSalonPortfolio: (Int) -> Unit,
    salonsListViewModel: SalonsListViewModel = viewModel(factory = LocalContext.current.appComponent.salonsListViewModel()),
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val showLoadingDialog = remember { mutableStateOf(false) }

    initObservable(
        scope,
        context,
        salonsListViewModel,
        onNavigateBack,
        onOpenSalonPortfolio,
        showLoadingDialog
    )

    Scaffold(
        topBar = {
            CommonTopAppBar(
                stringResource(R.string.my_salons),
                modifier = Modifier,
                navigationButton = IconButtonData(imageVector = Icons.Default.ArrowBack,
                    onClick = { salonsListViewModel.setEvent(SalonsListContract.SalonsListEvent.OnNavigateBackClick) }),
            )
        }
    ) { paddingValues ->
        SalonsList(
            modifier = Modifier.padding(paddingValues),
            salonsListViewModel,

            onSalonClick = { salonId ->
                salonsListViewModel.setEvent(
                    SalonsListContract.SalonsListEvent.OnOpenSalonPortfolio(
                        salonId
                    )
                )
            }
        )
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    context: Context,
    salonsListViewModel: SalonsListViewModel,
    onNavigateBack: () -> Unit,
    onOpenSalonPortfolio: (Int) -> Unit,
    showLoadingDialog: MutableState<Boolean>
) {

    composableScope.launch {
        salonsListViewModel.uiState.collect { state ->
            when (state) {
                SalonsListContract.SalonsListState.Loading -> {
                    showLoadingDialog.value = true
                }
                else -> {
                    showLoadingDialog.value = false
                }
            }
        }
    }

    composableScope.launch {
        salonsListViewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                SalonsListContract.SalonsListEffect.NavigateBack -> {
                    onNavigateBack()
                }
                is SalonsListContract.SalonsListEffect.OpenSalonPortfolio -> {
                    onOpenSalonPortfolio(effect.salonId)
                }
            }
        }
    }
}